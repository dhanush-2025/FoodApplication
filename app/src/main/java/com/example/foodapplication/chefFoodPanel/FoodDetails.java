package com.example.foodapplication.chefFoodPanel;

public class FoodDetails {

    public String Dishes,Quantity,Price,Description,RandomUID,RestaurentId;
    // Alt+insert

    public FoodDetails(String dishes, String quantity, String price, String description,  String randomUID, String chefid) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;

        RandomUID = randomUID;
        RestaurentId=chefid;


    }
    public String getName(){
        return Dishes;
    }
    public String getPrice(){
        return Price;
    }


}
