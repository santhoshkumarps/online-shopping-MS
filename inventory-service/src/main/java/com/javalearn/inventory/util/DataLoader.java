package com.javalearn.inventory.util;

import com.javalearn.inventory.model.Inventory;
import com.javalearn.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DataLoader  implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) {

        Inventory inventory = new Inventory();
        inventory.setSkuCode("MOB435");
        inventory.setQuantity(100);

        Inventory inventory1 = new Inventory();
        inventory1.setSkuCode("MOB235");
        inventory1.setQuantity(0);
        boolean checkSKu = checkSkuCodeIfExists(inventory);
        boolean checkSKu1 = checkSkuCodeIfExists(inventory1);
        if (!checkSKu){
            inventoryRepository.save(inventory);
        }
        if (!checkSKu1){
            inventoryRepository.save(inventory1);
        }
    }

    private boolean checkSkuCodeIfExists(Inventory inventory) {
      return inventoryRepository.findBySkuCode(inventory.getSkuCode()).isPresent();
    }
}
