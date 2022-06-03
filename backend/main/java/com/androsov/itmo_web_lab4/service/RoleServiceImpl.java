package com.androsov.itmo_web_lab4.service;

import com.androsov.itmo_web_lab4.model.Role;
import com.androsov.itmo_web_lab4.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}