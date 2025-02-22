package com.diettracker.backend.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaryType name;

//    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<DiaryFood> diaryFoods;

    public Diary() {
    }

    public Diary(DiaryType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaryType getName() {
        return name;
    }

    public void setName(DiaryType name) {
        this.name = name;
    }
}
