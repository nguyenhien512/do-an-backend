package com.hust.exam.service.impl;


import com.hust.exam.DTO.UserDto;
import com.hust.exam.config.PasswordEncoderConfig;
import com.hust.exam.mapper.UserMapper;
import com.hust.exam.models.Student;
import com.hust.exam.models.SystemUser;
import com.hust.exam.models.Teacher;
import com.hust.exam.repository.UserRepository;
import com.hust.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoderConfig pwEncoder;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = Objects.requireNonNull(userRepo);
    }

    /**
     * Get the number of user in database to determine new user's staffCode
     * @see #findAll()
     * @return {@link Integer}
     */
    @Override
    public int getDataNumber() {
        return userRepo.findAll().size();
    }


    /**
     * Get the list of all active user (User with state 1 in database)
     *
     * @return {@link List< UserDto >}
     * @see #findAll()
     */
    @Override
    public List<UserDto> findAll() {
        return UserMapper.toDtoList(new ArrayList<>(this.userRepo.findAll()));
    }

    /**
     * Find active user with given username, return dto
     * @param username username used to find user
     * @see #findByUsername(String)
     * @return {@link UserDto}
     */
    @Override
    public UserDto findByUsername(String username) {
        SystemUser found = this.userRepo.findById(username).orElse(null);
        if (found == null) {
            return null;
        }
        return UserMapper.toDto(found);
    }

    /**
     * Find user with given username, return entity
     * @param username username to find user
     * @see #findByUsername(String)
     * @return {@link SystemUser}
     */
    @Override
    public SystemUser findWithUsername(String username) {
        SystemUser found = this.userRepo.findById(username).orElse(null);
        return found;
    }

    @Override
    public SystemUser createUser(UserDto userDTO) {
        SystemUser found = userRepo.findById(userDTO.getUsername()).orElse(null);
        if (found != null) {
            return found;
        }
        String username = userDTO.getUsername();
        String password = pwEncoder.passwordEncoder().encode(userDTO.getPassword());
        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();
        String authority = userDTO.getAuthority();
        switch (authority) {
            case "STUDENT":
                Student student = new Student(username, password, firstName, lastName);
                return userRepo.save(student);
            case "TEACHER":
                Teacher teacher = new Teacher(username, password, firstName, lastName);
                return userRepo.save(teacher);
            default:
                return null;
        }
    }

}
