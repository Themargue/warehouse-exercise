package com.assignment.warehouse.products;

import javax.persistence.*;

@Entity
public class Spec {
    @Id
    private Integer specId;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    private Integer itemId;
    private Integer quantity;

    public Spec() {
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
