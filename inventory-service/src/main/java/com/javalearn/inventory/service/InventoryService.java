package com.javalearn.inventory.service;

import com.javalearn.inventory.dto.InventoryResponseDto;
import com.javalearn.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> skuCodes){
        /*if (!Strings.isBlank(skuCode) || !Strings.isEmpty(skuCode)){
          return inventoryRepository.findBySkuCode(skuCode).isPresent();
        }
        return Boolean.FALSE;*/

        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory -> InventoryResponseDto.builder().skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity()>0)
                .build()
        ).toList();
    }


}
