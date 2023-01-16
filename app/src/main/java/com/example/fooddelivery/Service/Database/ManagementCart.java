package com.example.fooddelivery.Service.Database;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddelivery.Service.Model.FoodDomains;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private FoodDB foodDB;

    public ManagementCart(Context context){
        this.context = context;
        this.foodDB = new FoodDB(context);
    }

    public void insertFood(FoodDomains item){
        ArrayList<FoodDomains> listFood = getListCart();
        boolean existAlready = false;

        int n = 0;
        for (int i = 0; i<listFood.size(); i++){
            if (listFood.get(i).getTittle().equals(item.getTittle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        foodDB.putListObject("CardList",listFood);
        Toast.makeText(context,"Added To Your Cart",Toast.LENGTH_LONG).show();
    }

    public ArrayList<FoodDomains> getListCart(){
        return foodDB.getListObject("CartList");
    }
}
