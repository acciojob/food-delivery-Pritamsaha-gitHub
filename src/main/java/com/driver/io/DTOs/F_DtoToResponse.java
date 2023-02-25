package com.driver.io.DTOs;

import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;

public class F_DtoToResponse {
    public FoodDetailsResponse converter(FoodDto foodDto){
        FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
        foodDetailsResponse.setFoodId(foodDto.getFoodId());
        foodDetailsResponse.setFoodName(foodDto.getFoodName());
        foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
        foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
        return  foodDetailsResponse;
    }

    public UserResponse userConverter(UserDto userDto){
        UserResponse userResponse=new UserResponse();
        userResponse.setUserId(userDto.getUserId());
        userResponse.setEmail(userDto.getEmail());
        userResponse.setFirstName(userDto.getFirstName());
        userResponse.setLastName(userDto.getLastName());
        return userResponse;
    }
    public OrderDetailsResponse orderConverter(OrderDto orderDto){
        OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
        orderDetailsResponse.setOrderId(orderDto.getOrderId());
        orderDetailsResponse.setCost(orderDto.getCost());
        orderDetailsResponse.setItems(orderDto.getItems());
        orderDetailsResponse.setUserId(orderDto.getUserId());
        orderDetailsResponse.setStatus(orderDto.isStatus());
        return orderDetailsResponse;
    }
}
