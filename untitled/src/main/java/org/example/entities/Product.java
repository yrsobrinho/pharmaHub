package org.example.entities;

public class Product {
    private Long id;
    private String name;
    private String description;

    public Product(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
