package com.diettracker.backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaryType name;

    private LocalDate date;

    public Diary() {
    }

    public Diary(DiaryType name) {
        this.name = name;
        this.date = date;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
