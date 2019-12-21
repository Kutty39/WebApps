package com.blbz.springmvc.repository;

import com.blbz.springmvc.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByEid(String email);
    UserInfo findByEidAndPas(String email, String pas);
}
