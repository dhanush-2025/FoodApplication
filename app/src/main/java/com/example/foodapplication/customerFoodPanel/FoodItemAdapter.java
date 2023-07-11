package com.example.foodapplication.customerFoodPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplication.R;
import com.example.foodapplication.chefFoodPanel.FoodDetails;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {
    private List<FoodDetails> foodItems;
    private List<FoodDetails> selectedItems;

    public FoodItemAdapter(List<FoodDetails> foodItems) {
        this.foodItems = foodItems;
        this.selectedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDetails foodItem = foodItems.get(position);
        holder.foodNameTextView.setText(foodItem.getName());
        holder.foodPriceTextView.setText("$" + String.valueOf(foodItem.getPrice()));
        holder.foodCheckBox.setChecked(selectedItems.contains(foodItem));

        holder.foodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(foodItem);
            } else {
                selectedItems.remove(foodItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public List<FoodDetails> getSelectedItems() {
        return selectedItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView;
        TextView foodPriceTextView;
        CheckBox foodCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            foodPriceTextView = itemView.findViewById(R.id.foodPriceTextView);
            foodCheckBox = itemView.findViewById(R.id.foodCheckBox);
        }
    }
}

