package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Diary;
import com.diettracker.backend.repositories.DiaryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryRepository diaryRepository;

    public DiaryController(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryRepository.findById(id);
        return diary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diary addDiary(@RequestBody Diary diary) {
        return diaryRepository.save(diary);
    }
}

