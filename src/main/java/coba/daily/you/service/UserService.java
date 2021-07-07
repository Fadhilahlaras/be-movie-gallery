package coba.daily.you.service;

import coba.daily.you.model.dto.RegisterDto;
import coba.daily.you.model.dto.UserDto;

import javax.transaction.Transactional;

@Transactional
public interface UserService {
    UserDto findByUsername(String username);
    public RegisterDto saveRegister(RegisterDto registerDto);
}
