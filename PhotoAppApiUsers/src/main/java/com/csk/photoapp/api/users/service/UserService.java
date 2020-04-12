package com.csk.photoapp.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.csk.photoapp.api.users.shared.UserDto;

@Service
public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	UserDto getUserDetailsByEmail(String email);
}
