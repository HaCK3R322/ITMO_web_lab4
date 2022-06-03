package com.androsov.itmo_web_lab4.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;
}
