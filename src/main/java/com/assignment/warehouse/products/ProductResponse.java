package com.assignment.warehouse.products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final Integer numberAvailable;
    @JsonProperty
    private final Integer id;

    public ProductResponse(String name, Integer numberAvailable, Integer id) {
        this.name = name;
        this.numberAvailable = numberAvailable;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberAvailable() {
        return numberAvailable;
    }

    public Integer getId() {
        return id;
    }
}
