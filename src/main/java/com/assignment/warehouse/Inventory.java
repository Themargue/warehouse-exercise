package com.assignment.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Inventory {
    @JsonProperty
    List<Item> inventory;

    public Inventory() {
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Integer findQuantityByItemId(Integer articleId) {
        Item itemFound = inventory.stream().filter(item -> articleId.equals(item.getId())).findFirst().orElse(null);
        return itemFound != null ? itemFound.getStock() : null;
    }

    public void updateQuantityByItemId(Integer id, Integer newQuantity) {
        Item itemFound = inventory.stream().filter(item -> id.equals(item.getId())).findFirst().orElse(null);
        if(itemFound != null) itemFound.setStock(newQuantity);
    }

    static class Item {
        @JsonProperty("art_id")
        private Integer id;
        private String name;
        private Integer stock;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
    }
}
