package com.superdevs.HealthOMeter.service;

import com.superdevs.HealthOMeter.entity.Authority;
import com.superdevs.HealthOMeter.entity.User;
import com.superdevs.HealthOMeter.repository.AuthorityRepo;
import com.superdevs.HealthOMeter.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addNewUser(User user) {
        if (ifUserNotExist(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Set<Authority> userAuthorities = new HashSet<>();
            userAuthorities.add(authorityRepo.findByAuthority("ROLE_USER").orElseThrow());
            user.setAuthorities(userAuthorities);
            userRepo.save(user);
        } else {
            System.out.println("User " + user.getUsername() + " already exists");
        }
    }

    private boolean ifUserNotExist(User user) {
        return userRepo.findByUsername(user.getUsername()).isEmpty();
    }

}
