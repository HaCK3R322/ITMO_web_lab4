package com.androsov.itmo_web_lab4.service;

import com.androsov.itmo_web_lab4.payload.request.UserRequest;

public interface UserService {
    void saveUser(UserRequest request);
}
