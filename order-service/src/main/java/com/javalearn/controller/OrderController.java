package com.javalearn.controller;

import com.javalearn.dto.OrderRequestDto;
import com.javalearn.service.OrderService;
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
        orderService.placeOrder(orderRequestDto);
        return "Order Placed successfully";
    }


}
