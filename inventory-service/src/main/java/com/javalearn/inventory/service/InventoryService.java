package com.javalearn.inventory.service;

import com.javalearn.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public Boolean isInStock(String skuCode){
        if (!Strings.isBlank(skuCode) || !Strings.isEmpty(skuCode)){
          return inventoryRepository.findBySkuCode(skuCode).isPresent();
        }
        return Boolean.FALSE;

    }


}
