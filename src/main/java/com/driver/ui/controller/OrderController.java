package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.DTOs.F_DtoToResponse;
import com.driver.io.DTOs.F_RequestToDto;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.OrderService;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;
	F_DtoToResponse change=new F_DtoToResponse();
	F_RequestToDto change1=new F_RequestToDto();
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto returnOrder=orderService.getOrderById(id);
		//OrderDto to OrderDetailsResponse
		OrderDetailsResponse orderDetailsResponse=change.orderConverter(returnOrder);
		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		//OrderDetailsRequestModel To OrderDto
		OrderDto orderDto=change1.orderConverter(order);

		OrderDto returnOrder=orderService.createOrder(orderDto);

		//OrderDto To OrderDetailsResponse
		OrderDetailsResponse orderDetailsResponse=change.orderConverter(returnOrder);
		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		//OrderDetailsRequestModel To OrderDto
		OrderDto orderDto=change1.orderConverter(order);

		OrderDto returnOrder=orderService.updateOrderDetails(id,orderDto);
		//OrderDto To OrderDetailsResponse
		OrderDetailsResponse orderDetailsResponse=change.orderConverter(returnOrder);
		return orderDetailsResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		OrderDto orderDto=orderService.getOrderById(id);
		if(orderDto.equals(null)){
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.toString());
		}else{
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		}
		orderService.deleteOrder(id);
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {

		List<OrderDto>List=orderService.getOrders();
		List<OrderDetailsResponse>ansList=new ArrayList<>();

		for(OrderDto o : List){
			OrderDetailsResponse orderDetailsResponse=change.orderConverter(o);
			ansList.add(orderDetailsResponse);
		}
		return ansList;
	}
}
