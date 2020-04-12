package com.csk.photoapp.api.users.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csk.photoapp.api.users.data.UserEntity;
import com.csk.photoapp.api.users.repository.UserRepository;
import com.csk.photoapp.api.users.service.UserService;
import com.csk.photoapp.api.users.shared.UserDto;
import com.csk.photoapp.api.users.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Utils utils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		String userId = utils.generateUserId(10);

		userDto.setUserId(userId);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		UserEntity storedUserEntity = userRepository.save(userEntity);

		UserDto returnUserDto = modelMapper.map(storedUserEntity, UserDto.class);

		return returnUserDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity storedUserEntity = userRepository.findByEmail(username);

		if (storedUserEntity == null) {
			throw new UsernameNotFoundException("User with email: " + username + " not found");
		}

		return new User(storedUserEntity.getEmail(), storedUserEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity storedUserEntity = userRepository.findByEmail(email);
		if (storedUserEntity == null)
			throw new UsernameNotFoundException("User with email: " + email + " not found");
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserEntity, returnValue);
		return returnValue;
	}

}
