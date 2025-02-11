package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Meal;
import com.diettracker.backend.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    @GetMapping
    public List<Meal> getMealsByDate(@RequestParam LocalDate date) {
        return mealRepository.findByDate(date);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        Optional<Meal> meal = mealRepository.findById(id);
        return meal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Meal addMeal(@RequestBody Meal meal) {
        System.out.println("Dodano posiłek: " + meal);
        return mealRepository.save(meal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestBody Meal newMeal) {
        System.out.println("Aktualizacja posiłku: " + newMeal);

        return mealRepository.findById(id)
                .map(meal -> {
                    meal.setName(newMeal.getName());
                    meal.setDate(newMeal.getDate());
                    return ResponseEntity.ok(mealRepository.save(meal));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        if (mealRepository.existsById(id)) {
            mealRepository.deleteById(id);
            System.out.println("Usunięto posiłek ID: " + id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
