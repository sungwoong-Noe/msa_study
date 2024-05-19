package com.example.orderservice.service;


import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

        OrderEntity entity = mapper.map(orderDto, OrderEntity.class);
        orderRepository.save(entity);

        return mapper.map(entity, OrderDto.class);
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {

        OrderEntity byOrderId = orderRepository.findByOrderId(orderId);

        return mapper.map(byOrderId, OrderDto.class);
    }

    @Override
    public Iterable<OrderEntity> getOrderByUserId(String userId) {


        return orderRepository.findByUserId(userId);
    }

}
