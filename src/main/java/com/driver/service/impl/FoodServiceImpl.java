package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
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

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity=new FoodEntity();
        foodEntity.setFoodId(food.getFoodId());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodCategory(food.getFoodCategory());
        foodEntity.setFoodPrice(food.getFoodPrice());
        foodRepository.save(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
       FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
       //entity to dto
        FoodDto foodDto=new FoodDto();
        foodDto.setId(foodEntity.getId());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        //foodDetails to foodEntity
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodRepository.save(foodEntity);
        return  foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity food=foodRepository.findByFoodId(id);
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
            FoodDto foodDto=new FoodDto();
            foodDto.setId(foodDto.getId());
            foodDto.setFoodName(f.getFoodName());
            foodDto.setFoodId(f.getFoodId());
            foodDto.setFoodPrice(f.getFoodPrice());
            foodDto.setFoodCategory(f.getFoodCategory());
            ansList.add(foodDto);
        }
        return ansList;
    }
}