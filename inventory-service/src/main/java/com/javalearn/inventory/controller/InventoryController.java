package com.javalearn.inventory.controller;

import com.javalearn.inventory.dto.InventoryResponseDto;
import com.javalearn.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    /*instead of path variable we will use request-mapping
    *  http://localhost:8082/api/inventory?sku-code=MOB435&sku-code=MOB235
    * */
    public List<InventoryResponseDto> isInStock(@RequestParam("sku-code") List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }


}
