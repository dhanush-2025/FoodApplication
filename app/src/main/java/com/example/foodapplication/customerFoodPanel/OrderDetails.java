package com.example.foodapplication.customerFoodPanel;

import com.example.foodapplication.chefFoodPanel.FoodDetails;

import java.util.List;

public class OrderDetails {
    private String orderId;
    private List<FoodDetails> foodItems;
    private List<String> selectedItemNames;

    public OrderDetails() {
        // Required empty constructor for Firebase
    }

    public OrderDetails(String orderId, List<FoodDetails> foodItems, List<String> selectedItemNames) {
        this.orderId = orderId;
        this.foodItems = foodItems;
        this.selectedItemNames = selectedItemNames;
    }



    public String getOrderId() {
        return orderId;
    }

    public List<FoodDetails> getFoodItems() {
        return foodItems;
    }
    public List<String> getSelectedItemNames() {
        return selectedItemNames;
    }
}

