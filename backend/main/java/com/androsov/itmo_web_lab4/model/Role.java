package com.androsov.itmo_web_lab4.model;

import com.androsov.itmo_web_lab4.configuration.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "s309629")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public Role(UserRole role) {
        userRole = role;
    }

}
