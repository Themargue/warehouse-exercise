package com.assignment.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    Inventory inventory;
    @Autowired
    List<ProductSpec> productSpecs;

    public List<Product> findAvailableProductsAndQuantities() {
        return productSpecs.stream().map(productSpec -> {
            return new Product(productSpec.getName(), getNumberAvailableProductsFromInventory(productSpec.getArticleList()), productSpec.getId());
        }).collect(Collectors.toList());
    }

    private Integer getNumberAvailableProductsFromInventory(List<ProductSpec.Article> articleList) {
        List<Integer> quantityPerArticle = articleList.stream()
                .map(article -> (int) inventory.findQuantityByItemId(article.getId())/article.getAmount())
                .collect(Collectors.toList());
        return Collections.min(quantityPerArticle);
    }

    public boolean isProductAvailable(Integer productId) {
        return findAvailableProductsAndQuantities().stream()
                .anyMatch(product -> product.getId().equals(productId) && product.getNumberAvailable() > 0 );
    }

    public void sellProduct(Integer productId) throws IOException {
        final List<ProductSpec.Article> articlesList = productSpecs.stream()
                .filter(productSpec -> productSpec.getId().equals(productId)).findFirst().get().getArticleList();
        articlesList.forEach(article -> {
            Integer newQuantity = inventory.findQuantityByItemId(article.getId()) - article.getAmount();
            inventory.updateQuantityByItemId(article.getId(), newQuantity);
        });
        mapper.writeValue(Paths.get("src/main/resources/static/inventory2.json").toFile(), inventory);
    }

    @PostConstruct
    public void loadInventoryAndProductSpecs() throws IOException {
        inventory = mapper.readValue(new File("src/main/resources/static/inventory.json"), Inventory.class);
        productSpecs = mapper.readValue(new File("src/main/resources/static/products.json"), mapper.getTypeFactory()
                .constructCollectionType(List.class, ProductSpec.class));
    }
}
