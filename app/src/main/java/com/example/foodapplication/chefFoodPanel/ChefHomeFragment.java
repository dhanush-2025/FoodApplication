package com.example.foodapplication.chefFoodPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplication.MainMenu;
import com.example.foodapplication.R;
import com.example.foodapplication.UpdateDishModel;
import com.example.foodapplication.chefFoodPanel.Chef;
import com.example.foodapplication.chefFoodPanel.ChefHomeAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChefHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<UpdateDishModel> updateDishModelList;
    private ChefHomeAdapter adapter;
    private DatabaseReference dataa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_home, container, false);
        getActivity().setTitle("Home");
        setHasOptionsMenu(true);


        updateDishModelList = new ArrayList<>();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataa = FirebaseDatabase.getInstance().getReference("Restaurent").child(userId);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Chef chef = snapshot.getValue(Chef.class);
                chefDishes();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = v.findViewById(R.id.Recycle_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChefHomeAdapter(getContext(), updateDishModelList);
        recyclerView.setAdapter(adapter);

        return v;
    }

    private void chefDishes() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FoodDetails").child(userId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateDishModelList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    UpdateDishModel updateDishModel = snapshot1.getValue(UpdateDishModel.class);
                    updateDishModelList.add(updateDishModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idd = item.getItemId();
        if (idd == R.id.LOGOUT) {
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}


