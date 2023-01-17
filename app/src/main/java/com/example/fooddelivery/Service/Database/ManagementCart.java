package com.example.fooddelivery.Service.Database;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddelivery.Service.Interface.ChangeNumberItemListener;
import com.example.fooddelivery.Service.Model.FoodDomains;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private FoodDB foodDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.foodDB = new FoodDB(context);
    }

    public void insertFood(FoodDomains item) {
        ArrayList<FoodDomains> listFood = getListCart();
        boolean existAlready = false;

        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTittle().equals(item.getTittle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        foodDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomains> getListCart() {
        return foodDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodDomains> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        foodDB.putListObject("CartList", listFood);
        changeNumberItemListener.change();
    }

    public void minusNumberFood(ArrayList<FoodDomains> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        if (listFood.get(position).getNumberInCart() == 1) {
            listFood.remove(position);
        } else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        foodDB.putListObject("CartList",listFood);
        changeNumberItemListener.change();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomains> listFood = getListCart();
        double fee = 0;
        for (int i =0; i<listFood.size(); i++){
            fee = fee + (listFood.get(i).getFee()* listFood.get(i).getNumberInCart());
        }
        return fee;
    }
}
