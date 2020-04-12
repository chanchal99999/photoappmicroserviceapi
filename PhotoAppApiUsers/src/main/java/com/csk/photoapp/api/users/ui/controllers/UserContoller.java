package com.csk.photoapp.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csk.photoapp.api.users.service.UserService;
import com.csk.photoapp.api.users.shared.UserDto;
import com.csk.photoapp.api.users.ui.model.request.UserRequestModel;
import com.csk.photoapp.api.users.ui.model.response.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UserContoller {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working users microservice on port no. " + env.getProperty("local.server.port");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {	
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

		UserResponseModel restUser = modelMapper.map(userService.createUser(userDto), UserResponseModel.class);

		return new ResponseEntity<>(restUser, HttpStatus.CREATED);
	}

}
