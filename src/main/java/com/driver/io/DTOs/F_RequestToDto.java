package com.driver.io.DTOs;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;

public class F_RequestToDto {
    public FoodDto converter(FoodDetailsRequestModel foodDetailsRequestModel){
        FoodDto foodDto=new FoodDto();
        foodDto.setFoodName(foodDetailsRequestModel.getFoodName());
        foodDto.setFoodCategory(foodDetailsRequestModel.getFoodCategory());
        foodDto.setFoodPrice(foodDetailsRequestModel.getFoodPrice());
        return foodDto;
    }

    public UserDto userConverter(UserDetailsRequestModel userDetailsRequestModel){
        UserDto userDto=new UserDto();
        userDto.setFirstName(userDetailsRequestModel.getFirstName());
        userDto.setLastName(userDetailsRequestModel.getLastName());
        userDto.setEmail(userDetailsRequestModel.getEmail());
        return userDto;
    }

    public OrderDto orderConverter(OrderDetailsRequestModel orderDetailsRequestModel){
        OrderDto orderDto=new OrderDto();
        orderDto.setItems(orderDetailsRequestModel.getItems());
        orderDto.setCost(orderDetailsRequestModel.getCost());
        orderDto.setUserId(orderDetailsRequestModel.getUserId());
        return orderDto;
    }
}
