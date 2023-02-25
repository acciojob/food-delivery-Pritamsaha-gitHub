package com.driver.service.impl;

import com.driver.io.DTOs.F_EntityToDto;
import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.io.repository.OrderRepository;
import com.driver.io.repository.UserRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;
    F_EntityToDto change=new F_EntityToDto();

    @Override
    public OrderDto createOrder(OrderDto order) {

        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderId(UUID.randomUUID().toString());
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());
        if(!userRepository.findByUserId(order.getUserId()).equals(null)){
            orderEntity.setStatus(true);
        }else{
            orderEntity.setStatus(false);
        }
        orderRepository.save(orderEntity);
        order.setId(orderEntity.getId());
        order.setStatus(orderEntity.isStatus());
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity.equals(null)){
            throw new Exception("ERROR");
        }
        OrderDto orderDto=change.orderConverter(orderEntity);
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity.equals(null)){
            throw new Exception("ERROR");
        }
        orderEntity.setItems(order.getItems());
        orderEntity.setCost(order.getCost());
        orderEntity.setUserId(order.getUserId());
        orderRepository.save(orderEntity);
        order.setId(orderEntity.getId());
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if (orderEntity.equals(null)){
            throw new Exception("ERROR");
        }
        orderRepository.deleteById(orderEntity.getId());
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> foodlist =new ArrayList<>();
        Iterable<OrderEntity>iter= orderRepository.findAll();
        iter.forEach(foodlist::add);
        List<OrderDto>ansList=new ArrayList<>();

        for(OrderEntity o : foodlist){
            OrderDto orderDto=change.orderConverter(o);
            ansList.add(orderDto);
        }
        return ansList;
    }
}