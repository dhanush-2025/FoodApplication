package com.example.foodapplication.customerFoodPanel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapplication.chefFoodPanel.FoodDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;





import com.example.foodapplication.R;
public class CustomerOrders extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference foodItemsRef;
    private List<FoodDetails> foodItemsList;
    private RecyclerView recyclerView;
    private FoodItemAdapter adapter;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        foodItemsRef = firebaseDatabase.getReference("/FoodDetails/GKe1XCncDJQf6PWzddEEGggSlxg1");

        // Initialize UI elements
        recyclerView = findViewById(R.id.recyclerView);
        orderButton = findViewById(R.id.orderButton);

        // Set up RecyclerView
        foodItemsList = new ArrayList<>();
        adapter = new FoodItemAdapter(foodItemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Set up ValueEventListener to fetch food items from Firebase
        foodItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItemsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FoodDetails foodItem = snapshot.getValue(FoodDetails
                            .class);
                    foodItemsList.add(foodItem);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CustomerOrders.this, "Failed to retrieve food items.", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle order button click
        // Handle order button click
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected food items and their quantities from the adapter
                List<FoodDetails> selectedItems = adapter.getSelectedItems();

                // Create a list to store the selected item names
                List<String> selectedItemNames = new ArrayList<>();
                for (FoodDetails item : selectedItems) {
                    selectedItemNames.add(item.getName());
                }

                // Share order details to Firebase
                DatabaseReference orderRef = firebaseDatabase.getReference("orders");
                String orderId = orderRef.push().getKey();
                OrderDetails order = new OrderDetails(orderId, selectedItems, selectedItemNames);
                orderRef.child(orderId).setValue(order);

                Toast.makeText(CustomerOrders.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}



















