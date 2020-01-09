package com.blbz.fundooapi.repository;

import com.blbz.fundooapi.entiry.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<UserInfo, Integer> {
    UserInfo findByEid(String email);
    @Query("from UserInfo where eid=:email")
    UserInfo findByUniqKey(String  email);
}
