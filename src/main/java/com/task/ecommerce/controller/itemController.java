package com.task.ecommerce.controller;

import com.task.ecommerce.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.task.ecommerce.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class itemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> welcome() {
        log.info("Welcome endpoint accessed");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to E-commerce API");
        response.put("version", "1.0.0");
        response.put("totalItems", itemService.getItemCount());
        response.put("endpoints", Map.of(
                "GET /api/items", "Get this welcome message",
                "GET /api/items/all", "Get all items",
                "POST /api/items/addItem", "Add a new item",
                "GET /api/items/{id}", "Get item by ID",
                "PUT /api/items/{id}", "Update an item",
                "DELETE /api/items/{id}", "Delete an item"
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        log.info("GET /api/items/{} - Retrieving item by ID", id);

        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        log.info("GET /api/items/all - Retrieving all items");

        List<Item> items = itemService.getAllItems();

        log.info("Retrieved {} items", items.size());
        return ResponseEntity.ok(items);
    }

    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@Valid @RequestBody Item item) {
        log.info("POST /api/items - Adding new item: {}", item.getName());

        Item createdItem = itemService.addItem(item);

        log.info("Item created successfully with ID: {}", createdItem.getId());
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable Long id) {
        log.info("DELETE /api/items/{} - Deleting item", id);

        itemService.deleteItem(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Item deleted successfully");
        response.put("deletedId", id);
        response.put("timestamp", java.time.LocalDateTime.now());

        log.info("Item deleted successfully with ID: {}", id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody Item item) {
        log.info("PUT /api/items/{} - Updating item", id);

        Item updatedItem = itemService.updateItem(id, item);

        log.info("Item updated successfully: {}", updatedItem.getName());

        return ResponseEntity.ok(updatedItem);
    }

}
