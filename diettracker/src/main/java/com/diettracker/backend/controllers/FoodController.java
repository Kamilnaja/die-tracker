package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Food;
import com.diettracker.backend.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Food addFood(@RequestBody Food food) {
        System.out.println("Dodano: " + food);
        return foodRepository.save(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food newFood) {
        System.out.println("Aktualizacja: " + newFood);

        return foodRepository.findById(id)
                .map(food -> {
                    food.setName(newFood.getName());
                    food.setCalories(newFood.getCalories());
                    food.setImageUrl(newFood.getImageUrl());
                    return ResponseEntity.ok(foodRepository.save(food));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            System.out.println("Usunięto: ID " + id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Food> searchFoodByName(@RequestParam String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }
}
