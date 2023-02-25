package com.driver.service.impl;

import com.driver.io.DTOs.F_DtoToEntity;
import com.driver.io.DTOs.F_EntityToDto;
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
    F_DtoToEntity change=new F_DtoToEntity();
    F_EntityToDto change1=new F_EntityToDto();
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        //userDto to UserEntity
        UserEntity userEntity=change.userConverter(user);
        userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return  user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        //userEntity to userDto
        UserDto userDto=change1.userConverter(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);

        //userEntity to userDto
        UserDto userDto=change1.userConverter(userEntity);
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
            UserDto userDto=change1.userConverter(u);
            ansList.add(userDto);
        }
        return ansList;
    }
}