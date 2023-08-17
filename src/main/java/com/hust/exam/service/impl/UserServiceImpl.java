package com.hust.exam.service.impl;


import com.hust.exam.DTO.UserDto;
import com.hust.exam.config.PasswordEncoderConfig;
import com.hust.exam.mapper.UserMapper;
import com.hust.exam.models.Admin;
import com.hust.exam.models.Student;
import com.hust.exam.models.SystemUser;
import com.hust.exam.models.Teacher;
import com.hust.exam.repository.UserRepository;
import com.hust.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        return UserMapper.toDtoList(this.userRepo.findAll());
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
    public UserDto createUser(UserDto userDTO) {
        SystemUser found = userRepo.findById(userDTO.getUsername()).orElse(null);
        if (found != null) {
            throw new RuntimeException("Username đã tồn tại: "+ found.getUsername());
        }
        System.out.println("dto "+userDTO.toString());
        String username = userDTO.getUsername();
        String password = pwEncoder.passwordEncoder().encode(userDTO.getPassword());
        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();
        String authority = userDTO.getAuthority();
        LocalDate dob = userDTO.getDob();
        Boolean isActive = true;
        switch (authority) {
            case "STUDENT":
                Student student = new Student(username, password, firstName, lastName, dob, isActive);
                SystemUser resultStudent = userRepo.save(student);
                resultStudent.setPassword(userDTO.getPassword());
                System.out.println("result student "+resultStudent );
                return UserMapper.toDto(resultStudent);
            case "TEACHER":
                Teacher teacher = new Teacher(username, password, firstName, lastName, dob, isActive);
                SystemUser resultTeacher = userRepo.save(teacher);
                resultTeacher.setPassword(userDTO.getPassword());
                System.out.println("result teacher "+resultTeacher );
                return UserMapper.toDto(resultTeacher);
            case "ADMIN":
                Admin admin = new Admin(username, password, firstName, lastName, dob, isActive);
                SystemUser resultAdmin = userRepo.save(admin);
                resultAdmin.setPassword(userDTO.getPassword());
                System.out.println("result Admin "+resultAdmin);
                return UserMapper.toDto(resultAdmin);
            default:
                throw new RuntimeException("Người dùng không hợp lệ");
        }
    }

    public UserDto updateUser(String requestUsername, UserDto userDto) {
        SystemUser found = userRepo.findById(requestUsername).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        if (userDto.getFirstName() != null && !userDto.getFirstName().isEmpty()) {
            found.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null && !userDto.getLastName().isEmpty()) {
            found.setLastName(userDto.getLastName());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            String password = pwEncoder.passwordEncoder().encode(userDto.getPassword());
            found.setPassword(password);
        }
        SystemUser saved = userRepo.save(found);
        return UserMapper.toDto(saved);
    }

    public void inactive(List<String> listUsername) {
        changeStatus(listUsername, false);
    }

    public void active(List<String> listUsername) {
        changeStatus(listUsername, true);
    }

    private void changeStatus(List<String> listUsername, boolean isActive) {
        Set<SystemUser> founds = userRepo.findByUsernameIn(listUsername);
        for (SystemUser user : founds) {
            user.setActive(isActive);
        }
        userRepo.saveAll(founds);
    }

    public List<UserDto> searchByNameOrUsername(String queryString, List<String> authorities) {
        Set<SystemUser> founds = userRepo.searchByNameOrUsername(queryString, authorities);
        List<SystemUser> result = new ArrayList<>(founds);
        return UserMapper.toDtoList(result);
    }
}
