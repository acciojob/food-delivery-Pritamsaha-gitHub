package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.io.DTOs.F_DtoToResponse;
import com.driver.io.DTOs.F_RequestToDto;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	F_DtoToResponse change=new F_DtoToResponse();
	F_RequestToDto change1=new F_RequestToDto();
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{

		UserDto userDto= userService.getUserByUserId(id);
		//userDto To UserResponse
		UserResponse userResponse=change.userConverter(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		//userDetailsRequestModel To userDto
		UserDto userDto=change1.userConverter(userDetails);

		UserDto userReturn=userService.createUser(userDto);

		//userDto to UserResponse
		UserResponse userResponse=change.userConverter(userReturn);
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		//userDetailsRequestModel to userDto
		UserDto userDto=change1.userConverter(userDetails);
		UserDto userReturn=userService.updateUser(id,userDto);
		//userDto to UserResponse
		UserResponse userResponse=change.userConverter(userReturn);
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{

		UserDto userDto= userService.getUserByUserId(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		if (userDto.equals(null)){
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.toString());
		}else{
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		}
		userService.deleteUser(id);
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){

		List<UserDto>List=userService.getUsers();
		List<UserResponse>anslist=new ArrayList<>();

		for(UserDto u : List){
			UserResponse userResponse=change.userConverter(u);
			anslist.add(userResponse);
		}
		return anslist;
	}
	
}
