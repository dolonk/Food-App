package com.example.fooddelivery.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Service.Database.ManagementCart;
import com.example.fooddelivery.Service.Model.FoodDomains;

public class ShowDetailsActivity extends AppCompatActivity {

    private TextView tittle, fee, description, numberOrderText;
    private ImageView plusBtn, minusBtn, picFood;
    private Button addToCart;

    private FoodDomains object;
    int numberOrder=1;
    ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomains) getIntent().getSerializableExtra("OBJECT");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        tittle.setText(object.getTittle());
        fee.setText("$"+object.getFee());
        description.setText(object.getDescription());
        numberOrderText.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder+1;
            numberOrderText.setText(String.valueOf(numberOrder));
        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder>1){
                numberOrder = numberOrder - 1;
            }
            numberOrderText.setText(String.valueOf(numberOrder));
        });

        addToCart.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);
            finish();
        });

    }

    private void initView() {
        tittle = findViewById(R.id.sTittleID);
        fee = findViewById(R.id.sFeeID);
        description = findViewById(R.id.sDescriptionID);
        numberOrderText = findViewById(R.id.sNumberOrderId);
        plusBtn = findViewById(R.id.sPlusID);
        minusBtn = findViewById(R.id.sMinusID);
        picFood = findViewById(R.id.sPicFoodID);
        addToCart = findViewById(R.id.sAddToCartBtID);
    }
}