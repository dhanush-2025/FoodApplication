package com.example.foodapplication.customerFoodPanel;

public class OrderItem {
    private String foodName;
    private int quantity;

    public OrderItem() {
        // Default constructor required for Firebase
    }

    public OrderItem(String foodName, int quantity) {
        this.foodName = foodName;
        this.quantity = quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "foodName='" + foodName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

