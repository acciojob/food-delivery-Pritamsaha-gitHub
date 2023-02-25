package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {
	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{

		FoodDto returnDto=foodService.getFoodById(id);
		//dto to foodDetailsresponse
		FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
		foodDetailsResponse.setFoodId(returnDto.getFoodId());
		foodDetailsResponse.setFoodName(returnDto.getFoodName());
		foodDetailsResponse.setFoodCategory(returnDto.getFoodCategory());
		foodDetailsResponse.setFoodPrice(returnDto.getFoodPrice());
		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		//converting foodDetails to foodentity
		FoodEntity food=new FoodEntity();
		food.setFoodName(foodDetails.getFoodName());
		food.setFoodCategory(foodDetails.getFoodCategory());
		food.setFoodPrice(foodDetails.getFoodPrice());
		food.setFoodId(UUID.randomUUID().toString());
		//converting foodEntity to foodDto
		FoodDto foodDto=new FoodDto();
		foodDto.setFoodName(food.getFoodName());
		foodDto.setFoodCategory(food.getFoodCategory());
		foodDto.setFoodPrice(food.getFoodPrice());
		foodDto.setFoodId(food.getFoodId());
		foodDto.setId(food.getId());

		FoodDto returnValue=foodService.createFood(foodDto);
		//returnvalue to foodDetailsResponse
		FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
		foodDetailsResponse.setFoodId(returnValue.getFoodId());
		foodDetailsResponse.setFoodName(returnValue.getFoodName());
		foodDetailsResponse.setFoodCategory(returnValue.getFoodCategory());
		foodDetailsResponse.setFoodPrice(returnValue.getFoodPrice());
		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{

		FoodDto foodDto=new FoodDto();
		//foodDetails to foodDto
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());

		FoodDto returnDto=foodService.updateFoodDetails(id,foodDto);

		//dto to foodResponse
		FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
		foodDetailsResponse.setFoodId(returnDto.getFoodId());
		foodDetailsResponse.setFoodName(returnDto.getFoodName());
		foodDetailsResponse.setFoodCategory(returnDto.getFoodCategory());
		foodDetailsResponse.setFoodPrice(returnDto.getFoodPrice());
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		FoodDto foodDto= foodService.getFoodById(id);
		if (foodDto.equals(null)){
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.toString());
		}else{
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		}
		foodService.deleteFoodItem(id);
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {

		List<FoodDetailsResponse>foodlist=new ArrayList<>();
		List<FoodDto>returnList=foodService.getFoods();

		for(FoodDto f : returnList){
			FoodDetailsResponse food=new FoodDetailsResponse();
			food.setFoodId(f.getFoodId());
			food.setFoodName(f.getFoodName());
			food.setFoodCategory(f.getFoodCategory());
			food.setFoodPrice(f.getFoodPrice());
			foodlist.add(food);
		}
		return foodlist;
	}
}
