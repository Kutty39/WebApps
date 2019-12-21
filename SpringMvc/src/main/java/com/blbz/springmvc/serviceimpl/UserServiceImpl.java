package com.blbz.springmvc.serviceimpl;

import com.blbz.springmvc.entity.UserInfo;
import com.blbz.springmvc.model.LoginDetail;
import com.blbz.springmvc.repository.UserRepository;
import com.blbz.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;

    @Override
    public boolean validater(String email) {
        return repo.findByEid(email) != null;
    }

    @Override
    public boolean register(UserInfo userInfo) {
        try {
            repo.save(userInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String encript(String pas) {
        if (!pas.equals("")) {
            try {
                byte[] m = MessageDigest.getInstance("SHA1").digest(pas.getBytes());
                BigInteger mInt = new BigInteger(1, m);
                StringBuilder hexString = new StringBuilder(mInt.toString(16));
                if (hexString.length() < 32) {
                    for (int i = 0; i < (32 - hexString.length()); i++) {
                        hexString.insert(0, "0");
                    }
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return "";
        }

    }

    @Override
    public UserInfo login(LoginDetail loginDetail) {
        return repo.findByEidAndPas(loginDetail.getEmail(), loginDetail.getPas());
    }

    @Override
    public boolean update(UserInfo user) {
        UserInfo userInfo = repo.findByEid(user.getEid());
        if (userInfo.getPas().equals(user.getPas())) {
            user.setUser_id(userInfo.getUser_id());
            repo.save(user);
            return true;
        } else {
            return false;
        }

    }
}
