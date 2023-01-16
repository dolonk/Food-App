package com.example.fooddelivery.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fooddelivery.Model.Adapter.CategoryAdapter;
import com.example.fooddelivery.Model.Adapter.PopularAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Service.Model.CategoryDomains;
import com.example.fooddelivery.Service.Model.FoodDomains;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomains> category = new ArrayList<>();
        category.add(new CategoryDomains("Pizza", "cat_1"));
        category.add(new CategoryDomains("Burger", "cat_2"));
        category.add(new CategoryDomains("HotDog", "cat_3"));
        category.add(new CategoryDomains("Drink", "cat_4"));
        category.add(new CategoryDomains("Donut", "cat_5"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView1);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomains> foodDomains = new ArrayList<>();
        foodDomains.add(new FoodDomains("Pepperoni Pizza","pop_1","Slices pepperoni, Mozzarella cheese, Fresh oregano, Ground black pepper, Pizza sauce",9.00));
        foodDomains.add(new FoodDomains("Cheese Burger","pop_2","Beef,Gouda cheese, Special sauce, Letterer, Tomato",8.50));
        foodDomains.add(new FoodDomains("Vegetable Pizza","pop_3","Olive oil, Vegetable oil, Pitted taramasalata, Cherry tomatoes, Fresh oregano, Basil",7.80));

        adapter2 = new PopularAdapter(foodDomains);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}