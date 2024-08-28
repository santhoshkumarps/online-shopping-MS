package com.javalearn.order.service;

import com.javalearn.order.dto.OrderRequestDto;
import com.javalearn.order.mapper.OrderMapper;
import com.javalearn.order.model.Order;
import com.javalearn.order.model.OrderLineItems;
import com.javalearn.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    public void placeOrder(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequestDto
                .getOrderLineItemsDtoList()
                .stream()
                .map(orderMapper::mapOrderLineItemsDtoToOrderLineItems)
                .toList();
        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);

    }

}
