package com.assignment.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(warehouseService.findAvailableProductsAndQuantities());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> sellProduct(@PathVariable Integer productId) throws IOException {
        if(!warehouseService.isProductAvailable(productId)) {
            return ResponseEntity.badRequest().body("Product not available");
        }
        warehouseService.sellProduct(productId);
        return ResponseEntity.ok("Product sold");
    }


}
