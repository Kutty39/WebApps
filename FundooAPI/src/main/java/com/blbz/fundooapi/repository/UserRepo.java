package com.blbz.fundooapi.repository;

import com.blbz.fundooapi.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<UserInfo, Integer> {
    UserInfo findByEid(String email);
}
