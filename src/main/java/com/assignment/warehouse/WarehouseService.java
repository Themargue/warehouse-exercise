package com.assignment.warehouse;

import com.assignment.warehouse.inventory.InventoryItem;
import com.assignment.warehouse.inventory.InventoryService;
import com.assignment.warehouse.products.Product;
import com.assignment.warehouse.products.ProductResponse;
import com.assignment.warehouse.products.ProductService;
import com.assignment.warehouse.products.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ProductService productService;

    public List<ProductResponse> findAvailableProductsAndQuantities() {
        final List<Product> allProducts = productService.findAll();
        return allProducts.stream().map(product -> {
            return new ProductResponse(product.getName(), getNumberAvailableProductsFromInventory(product.getSpec()), product.getProductId());
        }).collect(Collectors.toList());
    }

    private Integer getNumberAvailableProductsFromInventory(List<Spec> specs) {
        final List<Integer> quantityPerArticle = specs.stream().map(spec -> {
            Optional<InventoryItem> item = inventoryService.findById(spec.getItemId());
            if(item.isPresent()) {
                return item.get().getStock();
            }
            return 0;
        }).collect(Collectors.toList());
        if(!quantityPerArticle.isEmpty()) {
            return Collections.min(quantityPerArticle);
        }
        return 0;
    }
}
