package com.example.foodapplication.chefFoodPanel;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplication.R;
import com.example.foodapplication.UpdateDishModel;

import java.text.BreakIterator;
import java.util.List;



public class ChefHomeAdapter extends RecyclerView.Adapter<ChefHomeAdapter.ViewHolder> {

    private Context mcont;
    private List<UpdateDishModel> updateDishModelList;
    private OnItemClickListener clickListener;
    private List<Chef> restaurantList;

    public ChefHomeAdapter(Context context , List<UpdateDishModel>updateDishModelList){
        this.updateDishModelList = updateDishModelList;
        this.mcont = context;
    }

    public ChefHomeAdapter() {

    }

    @NonNull
    @Override
    public ChefHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcont).inflate(R.layout.chefmenu_update_delete,parent,false);
        return new ChefHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefHomeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final UpdateDishModel updateDishModel = updateDishModelList.get(position);
        holder.dishes.setText(updateDishModel.getDishes());
        updateDishModel.getRandomUID();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcont, UpdateDelete_Dish.class);
                intent.putExtra("updatedeletedish", updateDishModel.getRandomUID());
                mcont.startActivity(intent);
            }
        });
        Chef restaurant = null;
        holder.nameTextView.setText(restaurant.getFname());
        restaurant = restaurantList.get(position);
        holder.nameTextView.setText(restaurant.getFname());

        holder.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return updateDishModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public BreakIterator nameTextView;
        public View orderButton;
        TextView dishes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishes = itemView.findViewById(R.id.dish_name);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }



}
