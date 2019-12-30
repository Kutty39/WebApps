package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.dto.RegisterDto;
import com.blbz.fundooapi.model.UserInfo;
import com.blbz.fundooapi.repository.UserRepo;
import com.blbz.fundooapi.service.UserService;
import com.blbz.fundooapi.utility.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper mapper = new ModelMapper();
    private UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public void registerUser(RegisterDto registerDto) {
        registerDto.setPas(Util.encoder(registerDto.getPas()));
        repo.save(mapper.map(registerDto, UserInfo.class));
    }

    @Override
    public boolean checkEmail(String email) {
        return repo.findByEid(email)!=null;
    }

}
