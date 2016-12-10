package com.veronicacordobes.restomatic.model;


import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable{

    private int mId;
    private LinkedList<Dish> mDishes;

    public Table(int id){
        mId = id;
        mDishes = new LinkedList<>();

    }

    public int getId() {
        return mId;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    public void addDishes(Dish dish){
        mDishes.add(dish);
    }

    public int getCheck(){
        int check = 0;
        for (int i = 0; i<mDishes.size(); i++){
            Dish dish = mDishes.get(i);
            check += dish.getPrice();
        }
        return check;
    }

    public void emptyTable(){
        mDishes = new LinkedList<>();
    }

    @Override
    public String toString() {
        return String.format("Mesa %d", mId);
    }
}
