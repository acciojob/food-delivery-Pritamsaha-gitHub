package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.io.DTOs.F_DtoToResponse;
import com.driver.io.DTOs.F_RequestToDto;
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
	F_DtoToResponse change=new F_DtoToResponse();
	F_RequestToDto change1=new F_RequestToDto();


	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{

		FoodDto returnDto=foodService.getFoodById(id);
		//dto to foodDetailsresponse

		FoodDetailsResponse foodDetailsResponse= change.converter(returnDto);
		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		//converting foodDetails to foodDto
		FoodDto foodDto=change1.converter(foodDetails);

		FoodDto returnValue=foodService.createFood(foodDto);
		//returnvalue to foodDetailsResponse
		FoodDetailsResponse foodDetailsResponse=change.converter(returnValue);
		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		//foodDetails to foodDto
		FoodDto foodDto=change1.converter(foodDetails);

		FoodDto returnDto=foodService.updateFoodDetails(id,foodDto);

		//dto to foodResponse
		FoodDetailsResponse foodDetailsResponse=change.converter(foodDto);
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
			FoodDetailsResponse food=change.converter(f);
			foodlist.add(food);
		}
		return foodlist;
	}
}
