package com.driver.io.DTOs;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;

import java.util.UUID;

public class F_DtoToEntity {
    public FoodEntity converter (FoodDto foodDto){
        FoodEntity foodEntity=new FoodEntity();
        foodEntity.setFoodId(UUID.randomUUID().toString());
        foodEntity.setFoodName(foodDto.getFoodName());
        foodEntity.setFoodCategory(foodDto.getFoodCategory());
        foodEntity.setFoodPrice(foodDto.getFoodPrice());
        return foodEntity;
    }

    public UserEntity userConverter(UserDto userDto){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }
    public OrderEntity orderConverter(OrderDto orderDto){
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderId(UUID.randomUUID().toString());
        orderEntity.setCost(orderDto.getCost());
        orderEntity.setItems(orderDto.getItems());
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setStatus(orderDto.isStatus());
        return orderEntity;
    }
}
