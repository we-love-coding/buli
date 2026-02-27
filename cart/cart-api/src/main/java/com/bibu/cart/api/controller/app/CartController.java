package com.bibu.cart.api.controller.app;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private List<String> cart = new ArrayList<>();

    @PostMapping("/add")
    public String addItem(@RequestParam String item) {
        cart.add(item);
        return item + " has been added to the cart.";
    }

    @GetMapping("/list")
    public List<String> listItems() {
        return cart;
    }

    @PutMapping("/update")
    public String updateItem(@RequestParam int index, @RequestParam String item) {
        if (index < 0 || index >= cart.size()) {
            return "Item index out of bounds.";
        }
        cart.set(index, item);
        return "Item at index " + index + " has been updated.";
    }

    @DeleteMapping("/delete")
    public String deleteItem(@RequestParam int index) {
        if (index < 0 || index >= cart.size()) {
            return "Item index out of bounds.";
        }
        String removedItem = cart.remove(index);
        return removedItem + " has been removed from the cart.";
    }

    @DeleteMapping("/clear")
    public String clearCart() {
        cart.clear();
        return "Cart has been cleared.";
    }
}