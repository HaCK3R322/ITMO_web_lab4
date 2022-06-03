package com.androsov.itmo_web_lab4.service;

import com.androsov.itmo_web_lab4.configuration.security.UserRole;
import com.androsov.itmo_web_lab4.model.Role;
import com.androsov.itmo_web_lab4.model.User;
import com.androsov.itmo_web_lab4.payload.request.UserRequest;
import com.androsov.itmo_web_lab4.repository.RoleRepository;
import com.androsov.itmo_web_lab4.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadCredentialsException("User exists");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByUserRole(UserRole.ROLE_REGULAR));

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), roles);
        repository.save(user);
    }
}