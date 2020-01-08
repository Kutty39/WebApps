package com.blbz.fundooapi.repository;

import com.blbz.fundooapi.entiry.NoteInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Scope("prototype")
public interface NoteRepo extends JpaRepository<NoteInfo,Integer> {
    @Query("from NoteInfo where noteId=:id")
    NoteInfo findByUniqKey(int id);

}
