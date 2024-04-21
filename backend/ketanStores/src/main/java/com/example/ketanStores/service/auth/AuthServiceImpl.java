package com.example.ketanStores.service.auth;

import com.example.ketanStores.dto.SignUpReq;
import com.example.ketanStores.dto.UserDto;
import com.example.ketanStores.entity.UserEntity;
import com.example.ketanStores.repository.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createuser(SignUpReq sign) {
        UserEntity user=new UserEntity();
        user.setEmailId(sign.getEmail());
        user.setPassword(passwordEncoder.encode(sign.getPassword()));
        UserDto userDto=new UserDto();
        UserEntity userEntity=userRepo.save(user);
        userDto.setId(userEntity.getId());
        return userDto;

    }
}
