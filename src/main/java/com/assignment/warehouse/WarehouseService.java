package com.assignment.warehouse;

import com.assignment.warehouse.inventory.InventoryItem;
import com.assignment.warehouse.inventory.InventoryService;
import com.assignment.warehouse.products.Product;
import com.assignment.warehouse.products.ProductSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    List<ProductSpec> productSpecs;

    public List<Product> findAvailableProductsAndQuantities() {
        return productSpecs.stream().map(productSpec -> new Product(productSpec.getName(), getNumberAvailableProductsFromInventory(productSpec.getArticleList()), productSpec.getId())).collect(Collectors.toList());
    }

    private Integer getNumberAvailableProductsFromInventory(List<ProductSpec.Article> articleList) {
        final List<Integer> quantityPerArticle = articleList.stream().map(article -> {
            Optional<InventoryItem> item = inventoryService.findById(article.getId());
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

    public boolean isProductAvailable(Integer productId) {
        return findAvailableProductsAndQuantities().stream()
                .anyMatch(product -> product.getId().equals(productId) && product.getNumberAvailable() > 0 );
    }

    @PostConstruct
    public void loadProductSpecs() throws IOException {
        productSpecs = mapper.readValue(new File("src/main/resources/static/products.json"), mapper.getTypeFactory()
                .constructCollectionType(List.class, ProductSpec.class));
    }
}
