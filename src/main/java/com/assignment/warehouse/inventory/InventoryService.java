package com.assignment.warehouse.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Optional<InventoryItem> findById(Integer id) {
        return inventoryRepository.findById(id);
    }

}
