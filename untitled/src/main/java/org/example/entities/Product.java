package org.example.entities;

public class Product {
    private Long id;
    private String name;
<<<<<<< HEAD
    private String description;
    private Double price;

    public Product(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

=======
    private Category category;
    private Double price;

    public Product(Long id, String name, Category category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

>>>>>>> 203e50aa1a9f0ea443d23b00ae4b0a8436b7394d
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

=======
>>>>>>> 203e50aa1a9f0ea443d23b00ae4b0a8436b7394d
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
