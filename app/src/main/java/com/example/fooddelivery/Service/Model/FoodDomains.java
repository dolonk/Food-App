package com.example.fooddelivery.Service.Model;

import java.io.Serializable;

public class FoodDomains implements Serializable {
    private String tittle;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCart;

    public FoodDomains(String tittle, String pic, String description, Double fee) {
        this.tittle = tittle;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public FoodDomains(String tittle, String pic, String description, Double fee,int numberInCart) {
        this.tittle = tittle;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
