package com.assignment.warehouse.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    List<Spec> findSpecsByProductId(Integer productId) {
        final Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            return product.get().getSpec();
        }
        return new ArrayList<>();
    }
}
