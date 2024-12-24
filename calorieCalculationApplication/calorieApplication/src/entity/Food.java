package entity;

import java.util.Date;

public class Food {
    private int id;
    private int userId; // id e perdoruesit qe posedon kete ushqim
    private String foodName;
    private double calorie;
    private double price;
    private Date dateWhenConsumed;

    public Food(){

    }

    public Food(int id, int userId, String foodName, double calorie, double price, Date dateWhenConsumed) {
        this.id = id;
        this.userId = userId;
        this.foodName = foodName;
        this.calorie = calorie;
        this.price = price;
        this.dateWhenConsumed = dateWhenConsumed;
    }

    public int getFoodId(){
        return id;
    }
    public void setFoodId(int id){
        this.id = id;
    }
    public String getFoodName(){
        return foodName;
    }
    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public double getCalorie(){
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

    public String toString(){
        return "Food{"+"name= "+foodName+", consumed by= "+userId+", number of calories="+calorie+", price="+price+", dateWhenConsumed="+dateWhenConsumed+'}';
    }
}
