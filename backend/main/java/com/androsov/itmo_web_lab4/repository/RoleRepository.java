package com.androsov.itmo_web_lab4.repository;

import com.androsov.itmo_web_lab4.configuration.security.UserRole;
import com.androsov.itmo_web_lab4.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByUserRole(UserRole role);
}
