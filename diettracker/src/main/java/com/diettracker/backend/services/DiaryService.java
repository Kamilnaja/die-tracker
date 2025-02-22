package com.diettracker.backend.services;

import com.diettracker.backend.models.Diary;
import com.diettracker.backend.models.DiaryFood;
import com.diettracker.backend.models.DiaryType;
import com.diettracker.backend.models.Food;
import com.diettracker.backend.repositories.DiaryFoodRepository;
import com.diettracker.backend.repositories.DiaryRepository;
import com.diettracker.backend.repositories.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private DiaryFoodRepository diaryFoodRepository;

    public DiaryFood addFoodToDiary(Long diaryId, Long foodId, double weight) {
        // First, get the persistent entities from the database
        Diary diary;
        if (diaryId == null) {
            diary = new Diary(DiaryType.BREAKFAST); // Default type, you might want to make this configurable
            diary = diaryRepository.save(diary);
        } else {
            diary = diaryRepository.findById(diaryId).orElseThrow(() -> new EntityNotFoundException("Diary not found"));
        }


        Food food = foodRepository.findById(foodId).orElseThrow(() -> new EntityNotFoundException("Food not found"));

        // Create new DiaryFood with the persistent entities
        DiaryFood diaryFood = new DiaryFood();
        diaryFood.setDiary(diary);
        diaryFood.setFood(food);
        diaryFood.setWeight(weight);

        // Add to diary's collection
//        diary.addDiaryFood(diaryFood);

        // Save and return
        return diaryFoodRepository.save(diaryFood);
    }
}
