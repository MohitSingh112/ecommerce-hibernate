package com.ecom.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> product;


    //Constructors

    public Category(){}

    public Category(String name,String description){
        this.name = name;
        this.description = description;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
