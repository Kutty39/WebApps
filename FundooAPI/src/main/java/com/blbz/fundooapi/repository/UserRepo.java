package com.blbz.fundooapi.repository;

import com.blbz.fundooapi.model.UserInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Qualifier("repo")
@Repository
@Transactional
public interface UserRepo extends JpaRepository<UserInfo, Integer> {
    UserInfo findByEid(String email);
}
