package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{

		UserDto userDto= userService.getUserByUserId(id);
		//userDto To UserResponse
		UserResponse userResponse=new UserResponse();
		userResponse.setUserId(userDto.getUserId());
		userResponse.setEmail(userDto.getEmail());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		//userDetailsRequestModel To userDto
		UserDto userDto=new UserDto();
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userDto.setEmail(userDetails.getEmail());

		UserDto userReturn=userService.createUser(userDto);

		//userDto to UserResponse
		UserResponse userResponse=new UserResponse();
		userResponse.setUserId(userReturn.getUserId());
		userResponse.setEmail(userReturn.getEmail());
		userResponse.setFirstName(userReturn.getFirstName());
		userResponse.setLastName(userReturn.getLastName());
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		//userDetailsRequestModel to userDto
		UserDto userDto=new UserDto();
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userDto.setEmail(userDetails.getEmail());
		UserDto userReturn=userService.updateUser(id,userDto);
		//userDto to UserResponse
		UserResponse userResponse=new UserResponse();
		userResponse.setUserId(userReturn.getUserId());
		userResponse.setEmail(userReturn.getEmail());
		userResponse.setFirstName(userReturn.getFirstName());
		userResponse.setLastName(userReturn.getLastName());
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
			UserResponse userResponse=new UserResponse();
			userResponse.setUserId(u.getUserId());
			userResponse.setFirstName(u.getFirstName());
			userResponse.setLastName(u.getLastName());
			userResponse.setEmail(u.getEmail());
			anslist.add(userResponse);
		}
		return anslist;
	}
	
}
