package controllers;

import com.diettracker.backend.models.Food;
import com.diettracker.backend.repositories.FoodRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @PostMapping
    public Food addFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodRepository.deleteById(id);
    }
}