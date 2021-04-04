package com.assignment.warehouse.products;

import java.util.List;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer productId;
    private String name;
    @OneToMany(mappedBy="product")
    private List<Spec> spec;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spec> getSpec() {
        return spec;
    }

    public void setSpec(List<Spec> spec) {
        this.spec = spec;
    }
}
