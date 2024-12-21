package entity;

import java.util.Date;

public class Food {
    private String foodName;
    private int calorie;
    private double price;
    private Date dateWhenConsumed;

    public Food(){

    }

    public String getFoodName(){
        return foodName;
    }
    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public int getCalorie(){
        return calorie;
    }
    public void setCalorie(int calorie){
        this.calorie = calorie;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }

    public Date getDateWhenConsumed(){
        return dateWhenConsumed;
    }
    public void setDateWhenConsumed(Date dateWhenConsumed){
        this.dateWhenConsumed = dateWhenConsumed;
    }
}
