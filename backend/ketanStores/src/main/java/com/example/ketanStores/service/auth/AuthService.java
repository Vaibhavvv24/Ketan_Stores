package com.example.ketanStores.service.auth;

import com.example.ketanStores.dto.SignUpReq;
import com.example.ketanStores.dto.UserDto;

public interface AuthService {
    UserDto createuser(SignUpReq sign);
}
