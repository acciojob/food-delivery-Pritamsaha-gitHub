package com.driver.service.impl;

import com.driver.io.DTOs.F_DtoToEntity;
import com.driver.io.DTOs.F_EntityToDto;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodRepository foodRepository;
    F_DtoToEntity change=new F_DtoToEntity();
    F_EntityToDto change1=new F_EntityToDto();

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity=change.converter(food);
        foodRepository.save(foodEntity);
        food.setId(foodEntity.getId());
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
       FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
       if(foodEntity.equals(null)){
           throw new Exception(RequestOperationStatus.ERROR.toString());
       }
       //entity to dto
        FoodDto foodDto=change1.converter(foodEntity);
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        //foodDetails to foodEntity
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        if(foodEntity.equals(null)){
            throw new Exception(RequestOperationStatus.ERROR.toString());
        }
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodRepository.save(foodEntity);
        foodDetails.setId(foodEntity.getId());
        return  foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity food=foodRepository.findByFoodId(id);
        if(food.equals(null)){
            throw new Exception(RequestOperationStatus.ERROR.toString());
        }
        if (!food.equals(null)){
            foodRepository.deleteById(food.getId());
        }
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodlist =new ArrayList<>();
        Iterable<FoodEntity>iter= foodRepository.findAll();
        iter.forEach(foodlist::add);
        List<FoodDto>ansList=new ArrayList<>();

        for (FoodEntity f:foodlist){
            FoodDto foodDto=change1.converter(f);
            ansList.add(foodDto);
        }
        return ansList;
    }
}