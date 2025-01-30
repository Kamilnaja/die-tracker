package com.diettracker.backend.repositories;

import com.diettracker.backend.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // Możesz dodać metody wyszukiwania, np. wyszukiwanie po nazwie
}