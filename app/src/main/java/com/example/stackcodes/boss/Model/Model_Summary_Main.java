package com.example.stackcodes.boss.Model;

import android.widget.TextView;

public class Model_Summary_Main {
    private String BurgerName;
    private String date;
    private String Details;
    private double Sales;
    private int BurgerId,BurgerQuantity;
    private double Burger_price;


    public Model_Summary_Main(int burgerId,String burgerName, int burgerQuantity, double burger_price) {
        BurgerName = burgerName;
        BurgerQuantity = burgerQuantity;
        Burger_price = burger_price;
        BurgerId = burgerId;
    }
    public Model_Summary_Main(String burger_Name, int burger_Quantity, double burger_Price) {
        BurgerName = burger_Name;
        BurgerQuantity = burger_Quantity;
        Burger_price = burger_Price;
    }
    public Model_Summary_Main(String date, String details, double sales) {
        this.date = date;
        Details = details;
        Sales = sales;
    }



    public int getBurgerId() {
        return BurgerId;
    }

    public void setBurgerId(int burgerId) {
        BurgerId = burgerId;
    }

    public double getBurger_price() {
        return Burger_price;
    }

    public void setBurger_price(int burger_price) {
        Burger_price = burger_price;
    }


    public String getBurgerName() {
        return BurgerName;
    }

    public void setBurgerName(String burgerName) {
        BurgerName = burgerName;
    }

    public int getBurgerQuantity() {
        return BurgerQuantity;
    }

    public void setBurgerQuantity(int burgerQuantity) {
        BurgerQuantity = burgerQuantity;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public double getSales() {
        return Sales;
    }

    public void setSales(double sales) {
        Sales = sales;
    }

}


