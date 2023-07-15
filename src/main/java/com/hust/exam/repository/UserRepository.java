package com.hust.exam.repository;

import com.hust.exam.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SystemUser, String> {
}
