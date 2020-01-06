package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.dto.BlockedJwt;
import com.blbz.fundooapi.dto.LoginDto;
import com.blbz.fundooapi.dto.MsgDto;
import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.entiry.UserInfo;
import com.blbz.fundooapi.entiry.UserStatus;
import com.blbz.fundooapi.repository.UserRepo;
import com.blbz.fundooapi.service.JwtUtil;
import com.blbz.fundooapi.service.Publisher;
import com.blbz.fundooapi.service.UserService;
import com.blbz.fundooapi.service.UserStatusService;
import com.blbz.fundooapi.utility.Util;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private ModelMapper mapper = new ModelMapper();
    private UserRepo userRepo;
    private UserStatusService userStatusService;
    private Util util;
    private Publisher publisher;
    private JwtUtil jwtUtil;
    private MsgDto msgDto;
    private BlockedJwt blockedJwt;
    @Value("${jwt.expiry.time.sec.day}")
    private int expireForDay;

    private String msgBody;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserStatusService userStatusService
            , Util util
            , Publisher publisher
            , JwtUtil jwtUtil
            , MsgDto msgDto
            , BlockedJwt blockedJwt) {
        this.userRepo = userRepo;
        this.userStatusService = userStatusService;
        this.util = util;
        this.publisher = publisher;
        this.jwtUtil = jwtUtil;
        this.msgDto = msgDto;
        this.blockedJwt = blockedJwt;
    }


    @Override
    public String registerUser(RegisterDto registerDto) {
        registerDto.setPas(util.encoder(registerDto.getPas()));
        UserInfo userInfo = mapper.map(registerDto, UserInfo.class);
        UserStatus status = userStatusService.getByStatus("Inactive");
        userInfo.setUserStatus(status);
        userInfo.setUserCreatedOn(LocalDate.now());
        log.info(status.toString());
        log.info(userInfo.toString());
        userRepo.save(userInfo);
        return sendActivationMail(registerDto.getEid(), registerDto.getFname() + " " + registerDto.getLname());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkEmail(String email) {
        return userRepo.findByEid(email) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUser(String useremail) {
        return userRepo.findByEid(useremail);
    }

    @Override
    public boolean passwordMatcher(LoginDto loginDto) {
        UserInfo userInfo = userRepo.findByEid(loginDto.getUsername());
        if (userInfo != null) {
            return util.passwordMatcher(loginDto.getPassword(), userInfo.getPas());
        } else {
            return false;
        }
    }

    @Override
    public String sendActivationMail(String email, String fullname) {
        msgDto.setEmail(email);
        msgDto.setJwt(jwtUtil.generateJwt(email, expireForDay, "activate"));
        msgDto.setName(fullname);
        msgDto.setSubject("Account Activation");

        msgBody = util.getMsg();
        System.out.println(msgBody);
        if (msgBody != null) {
            msgBody = msgBody.replace("{name}", msgDto.getName());
            msgBody = msgBody.replace("{jwt}", msgDto.getJwt());
            msgDto.setMsg(msgBody);
            publisher.produceMsg(msgDto);
            return "Thanks for registering with us. please check your email and activate your account";
        }
        return "Something went wrong";
    }

    @Override
    @Transactional(readOnly = true)
    public String userActivate(String jwt) {
        try {
            jwtUtil.loadJwt(jwt);
            if (jwtUtil.isValid()) {
                if (jwtUtil.getClaims().get("url").equals("activate")) {
                    UserInfo userInfo = userRepo.findByEid(jwtUtil.userName());
                    if (userInfo.getUserStatus().getStatusText().equals("Active")) {
                        return "Account already activated";
                    } else {
                        userInfo.setUserStatus(userStatusService.getByStatus("Active"));
                        userRepo.save(userInfo);
                        return "Your account has been activated";
                    }
                } else {
                    return "Invalid token. please try login and activate your account";
                }
            } else {
                return "Your request has expired. please try login and activate your account";
            }
        } catch (Exception e) {
            return "Something went wrong";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String loginUser(String userEmail) {
        UserInfo userInfo = userRepo.findByEid(userEmail);
        String status = userInfo.getUserStatus().getStatusText();
        if (status.equals("Active")) {
            return (jwtUtil.generateJwt(userEmail, "api"));
        } else if (status.equals("Clossed")) {
            return "You are trying to access closed account. Please register again";
        } else {
            return sendActivationMail(userEmail, userInfo.getFname() + " " + userInfo.getLname());
        }
    }

    @Override
    public void blockedJwt(String jwt) {
        blockedJwt.getBJwt().add(jwt);
        util.writeBlockJwt(blockedJwt);
    }

}
