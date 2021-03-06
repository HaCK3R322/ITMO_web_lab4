package com.androsov.itmo_web_lab4.controller;

import com.androsov.itmo_web_lab4.configuration.security.JwtTokenProvider;
import com.androsov.itmo_web_lab4.model.User;
import com.androsov.itmo_web_lab4.payload.request.UserRequest;
import com.androsov.itmo_web_lab4.payload.response.JwtResponse;
import com.androsov.itmo_web_lab4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    UserService userService;

    public UserController (AuthenticationManager manager, JwtTokenProvider provider, UserService service) {
        authenticationManager = manager;
        jwtTokenProvider = provider;
        userService = service;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        if (request.getUsername().equals("") || request.getPassword().equals("")) {
            throw new BadCredentialsException("No username or password");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();

            String token = jwtTokenProvider.createJwtToken(user);

            return ResponseEntity.ok()
                    .body(new JwtResponse(token, user.getId(), user.getUsername()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password\n");
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        if (request.getUsername().equals("") || request.getPassword().equals("")) {
            throw new BadCredentialsException("No username or password");
        }
        userService.saveUser(request);

        return ResponseEntity.ok().build();
    }

}