package com.example.fooddelivery.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fooddelivery.Model.Adapter.CartListAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Service.Database.ManagementCart;
import com.example.fooddelivery.Service.Interface.ChangeNumberItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCartList;
    private ManagementCart managementCart;
    private TextView itemsCart, deliveryCart, taxCart, totalCart, emptyChart;
    private double tax;
    private Button checkOutCart;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        calculateCart();
        bottomNavigation();
    }

    public void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartbtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(CartListActivity.this,CartListActivity.class));
        });

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(CartListActivity.this,MainActivity.class));
        });
    }

    private void initView() {
        recyclerViewCartList = findViewById(R.id.cRecyclerViewID);
        itemsCart = findViewById(R.id.cItemsTotalID);
        deliveryCart = findViewById(R.id.cDeliveryChargeID);
        taxCart = findViewById(R.id.cTaxID);
        totalCart = findViewById(R.id.cTotalID);
        emptyChart = findViewById(R.id.cEmptyCartID);
        checkOutCart = findViewById(R.id.cCheckoutID);
        scrollView = findViewById(R.id.cScrollView);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCartList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        recyclerViewCartList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyChart.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
             emptyChart.setVisibility(View.GONE);
             scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        itemsCart.setText("$"+ itemTotal);
        deliveryCart.setText("$"+ delivery);
        taxCart.setText("$"+ tax);
        totalCart.setText("$"+ total);

    }
}