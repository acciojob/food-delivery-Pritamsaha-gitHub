package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        //userDto to UserEntity
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return  user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        //userEntity to userDto
        UserDto userDto=new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);

        //userEntity to userDto
        UserDto userDto=new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(!userEntity.equals(null)){
            userRepository.deleteById(userEntity.getId());
        }
    }

    @Override
    public List<UserDto> getUsers() {

        List<UserEntity> userlist =new ArrayList<>();
        Iterable<UserEntity>iter= userRepository.findAll();
        iter.forEach(userlist::add);
        List<UserDto>ansList=new ArrayList<>();

        for(UserEntity u:userlist){
            UserDto userDto=new UserDto();
            userDto.setId(u.getId());
            userDto.setUserId(u.getUserId());
            userDto.setFirstName(u.getFirstName());
            userDto.setLastName(u.getLastName());
            userDto.setEmail(u.getEmail());
            ansList.add(userDto);
        }
        return ansList;
    }
}