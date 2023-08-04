package com.hust.exam.service.impl;

import com.hust.exam.models.SystemUser;
import com.hust.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    private static final String USER_DEFAULT = "refresher";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
        SystemUser user = findUserByUsername(username);
        UserBuilder builder = null;
        if (user == null ) {
            throw new UsernameNotFoundException("Người dùng không tồn tại " + username);
        }
        if (!user.isActive()) {
            throw new DisabledException("Tài khoản bị vô hiệu hóa");
        }
            builder = User.withUsername(username);
            builder.password(user.getPassword());
            builder.authorities(user.getAuthority());
            return builder.build();
    }

    public SystemUser findUserByUsername(String username) {
        if (username != null) {
            SystemUser user = userService.findWithUsername(username);
            return user;
        }
        return null;
    }
}
