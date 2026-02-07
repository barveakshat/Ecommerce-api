package com.task.ecommerce.service;

import com.task.ecommerce.exceptions.itemNotFoundException;
import com.task.ecommerce.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class ItemService {
    private final List<Item> Items = new ArrayList<>();   // In-memory storage using ArrayList
    private final AtomicLong idCounter = new AtomicLong(1);   // Auto-incrementing ID counter
// Constructor - Initialize with some sample data
    public ItemService() {
        log.info("Initializing ItemService with sample data");
        addItem(new Item(null, "Laptop", "High-performance gaming laptop", 1299.99, "Electronics", 15));
        addItem(new Item(null, "Smartphone", "Latest model with 5G support", 899.99, "Electronics", 30));
        addItem(new Item(null, "Headphones", "Wireless noise-cancelling headphones", 299.99, "Electronics", 50));
        log.info("ItemService initialized with {} items", Items.size());
    }
    public Item addItem(Item itm) {
        itm.setId(idCounter.getAndIncrement());  // Set unique ID
        // Set default stock quantity if null
        if (itm.getStockQuantity() == null) {
            itm.setStockQuantity(0);
        }
        Items.add(itm);
        log.info("Added new item: ID={}, Name={}, Price={}", itm.getId(), itm.getName(), itm.getPrice());
        return itm;  // Return the added item
    }
//    Get all items
    public List<Item> getAllItems() {
        return new ArrayList<>(Items);
    }
//    Get item by ID
    public Item getItemById(Long id) {
        log.debug("Searching for item with ID: {}", id);
        Optional<Item> item = Items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if (item.isPresent()) {
            log.debug("Found item: {}", item.get().getName());
            return item.get();
        } else {
            log.warn("Item not found with ID: {}", id);
            throw new itemNotFoundException(id);
        }
    }
// Get total item count
    public int getItemCount() {
        return Items.size();
    }
// Delete item by ID
    public void deleteItem(Long id) {
        log.info("Deleting item with ID: {}", id);

        Item item = getItemById(id); // Will throw exception if not found
        Items.remove(item);

        log.info("Item deleted successfully: ID={}, Name={}", item.getId(), item.getName());
    }
// Update item by ID
    public Item updateItem(Long id, Item updatedItem) {
        log.info("Updating item with ID: {}", id);

        Item existingItem = getItemById(id); // Will throw exception if not found

        // Update fields
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setCategory(updatedItem.getCategory());

        if (updatedItem.getStockQuantity() != null) {
            existingItem.setStockQuantity(updatedItem.getStockQuantity());
        }

        log.info("Item updated successfully: ID={}, Name={}", existingItem.getId(), existingItem.getName());

        return existingItem;
    }
}

