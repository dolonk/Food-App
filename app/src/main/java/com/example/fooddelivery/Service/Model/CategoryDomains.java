package com.example.fooddelivery.Service.Model;

public class CategoryDomains {
    private String tittle;
    public String pic;

    public CategoryDomains(String tittle, String pic) {
        this.tittle = tittle;
        this.pic = pic;
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
}
