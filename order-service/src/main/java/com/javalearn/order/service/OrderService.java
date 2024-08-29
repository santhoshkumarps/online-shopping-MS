package com.javalearn.order.service;

import com.javalearn.order.dto.InventoryResponseDto;
import com.javalearn.order.dto.OrderRequestDto;
import com.javalearn.order.mapper.OrderMapper;
import com.javalearn.order.model.Order;
import com.javalearn.order.model.OrderLineItems;
import com.javalearn.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    private WebClient.Builder webClientBuilder;

    private OrderMapper orderMapper;

    public Boolean placeOrder(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequestDto
                .getOrderLineItemsDtoList()
                .stream()
                .map(orderMapper::mapOrderLineItemsDtoToOrderLineItems)
                .toList();
        order.setOrderLineItems(orderLineItems);

        // Call Inventory Service and place order if order is in stock
        List<String> skuCodes =    order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

       InventoryResponseDto[] inventoryResponses = getInventoryResponses(skuCodes);

        /*check if inventory response is null or not then check for skucode */
        if (Objects.nonNull(inventoryResponses)){
            Boolean areProductInStock = Arrays.stream(inventoryResponses)
                    .allMatch(InventoryResponseDto::getIsInStock);
            if (Boolean.TRUE.equals(areProductInStock)) {
                orderRepository.save(order);
                return true;
            }
        }
        return false;

    }

    private InventoryResponseDto[] getInventoryResponses(List<String> skuCodes){
        return webClientBuilder.build()
                .get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder
                                .queryParam("sku-code",skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();
    }

}
