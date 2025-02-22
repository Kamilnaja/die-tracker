// AddDiaryFoodRequest.java
package com.diettracker.backend.requests;
public class AddDiaryFoodRequest {
    private Long diaryId;
    private Long foodId;
    private double weight;

    // Getters and setters
    public Long getDiaryId() { return diaryId; }
    public void setDiaryId(Long diaryId) { this.diaryId = diaryId; }
    public Long getFoodId() { return foodId; }
    public void setFoodId(Long foodId) { this.foodId = foodId; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
}