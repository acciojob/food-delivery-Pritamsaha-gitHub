package com.driver.io.DTOs;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;

public class F_EntityToDto {
    public FoodDto converter(FoodEntity foodEntity){
        FoodDto foodDto=new FoodDto();
        foodDto.setId(foodEntity.getId());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        return foodDto;
    }

    public UserDto userConverter(UserEntity userEntity){
        UserDto userDto=new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }

    public OrderDto orderConverter(OrderEntity orderEntity){
        OrderDto orderDto=new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setStatus(orderEntity.isStatus());
        return orderDto;
    }
}
