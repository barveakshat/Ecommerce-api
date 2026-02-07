package com.task.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    Long id;

    @NotBlank(message = "Item name is required")         // Validation to ensure name is not null
    String name;

    @NotBlank(message = "Item description is required")
    String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    Double price;

    @NotBlank(message = "Category is required")
    private String category;
    private Integer stockQuantity;

    // Custom constructor without stockQuantity (defaults to 0)
    public Item(Long id, String name, String description, Double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = 0;
    }
}
