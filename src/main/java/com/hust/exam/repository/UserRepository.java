package com.hust.exam.repository;

import com.hust.exam.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<SystemUser, String> {

    Set<SystemUser> findByUsernameIn(List<String> listUsername);


    @Query("Select u from SystemUser u " +
            "where (concat(firstName,' ',lastName) like %?1% or concat(lastName,' ',firstName) like %?1% or username like %?1%)" +
            "and authority in ?2")
    Set<SystemUser> searchByNameOrUsername (String queryString, List<String> authority);
}
