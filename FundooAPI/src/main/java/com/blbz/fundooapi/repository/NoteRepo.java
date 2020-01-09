package com.blbz.fundooapi.repository;

import com.blbz.fundooapi.entiry.NoteInfo;
import com.blbz.fundooapi.entiry.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NoteRepo extends JpaRepository<NoteInfo,Integer> {
    @Query("from NoteInfo where noteId=:id")
    NoteInfo findByUniqKey(int id);
    List<NoteInfo> findByCollaborator(UserInfo collaborator);
}
