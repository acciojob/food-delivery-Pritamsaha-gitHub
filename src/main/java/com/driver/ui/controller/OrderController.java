package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

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
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto returnOrder=orderService.getOrderById(id);
		//OrderDto to OrderDetailsResponse
		OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(returnOrder.getOrderId());
		orderDetailsResponse.setCost(returnOrder.getCost());
		orderDetailsResponse.setItems(returnOrder.getItems());
		orderDetailsResponse.setUserId(returnOrder.getUserId());
		orderDetailsResponse.setStatus(returnOrder.isStatus());
		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		//OrderDetailsRequestModel To OrderDto
		OrderDto orderDto=new OrderDto();
		orderDto.setItems(order.getItems());
		orderDto.setCost(order.getCost());
		orderDto.setUserId(order.getUserId());

		OrderDto returnOrder=orderService.createOrder(orderDto);

		//OrderDto To OrderDetailsResponse
		OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(returnOrder.getOrderId());
		orderDetailsResponse.setCost(returnOrder.getCost());
		orderDetailsResponse.setItems(returnOrder.getItems());
		orderDetailsResponse.setUserId(returnOrder.getUserId());
		orderDetailsResponse.setStatus(returnOrder.isStatus());
		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		//OrderDetailsRequestModel To OrderDto
		OrderDto orderDto=new OrderDto();
		orderDto.setItems(order.getItems());
		orderDto.setCost(order.getCost());
		orderDto.setUserId(order.getUserId());
		OrderDto returnOrder=orderService.updateOrderDetails(id,orderDto);
		//OrderDto To OrderDetailsResponse
		OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(returnOrder.getOrderId());
		orderDetailsResponse.setCost(returnOrder.getCost());
		orderDetailsResponse.setItems(returnOrder.getItems());
		orderDetailsResponse.setUserId(returnOrder.getUserId());
		orderDetailsResponse.setStatus(returnOrder.isStatus());
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
			OrderDetailsResponse orderDetailsResponse=new OrderDetailsResponse();
			orderDetailsResponse.setOrderId(o.getOrderId());
			orderDetailsResponse.setCost(o.getCost());
			orderDetailsResponse.setItems(o.getItems());
			orderDetailsResponse.setUserId(o.getUserId());
			orderDetailsResponse.setStatus(o.isStatus());
			ansList.add(orderDetailsResponse);
		}
		return ansList;
	}
}
