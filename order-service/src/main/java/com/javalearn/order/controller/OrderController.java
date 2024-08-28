package com.javalearn.order.controller;

import com.javalearn.order.dto.OrderRequestDto;
import com.javalearn.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    @PostMapping
    @ResponseStatus(CREATED)
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto){
       Boolean response =  orderService.placeOrder(orderRequestDto);
       if (response) {
           return "Order Placed successfully";
       }
       throw new IllegalArgumentException("Order Didn't placed due to no stock of items");
    }


}
