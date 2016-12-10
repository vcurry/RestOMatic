package com.veronicacordobes.restomatic.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by veronicacordobes on 28/11/16.
 */

public class Dish implements Serializable{
    private String mName = null;
    private String mDescription = null;
    private int mPrice = 0;
    private int mImage = 0;
    private List<Integer> mAllergens = new LinkedList<>();
    private String mSpecialOrder = null;

    public Dish(String name, String description, int price, int image) {
        mName = name;
        mDescription = description;
        mPrice = price;
        mImage = image;
    }

    public Dish(String name){
        this(name, null,0,0);
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public void addAllergen(int allergen){
        mAllergens.add(allergen);
    }

    public int getAllergenCount(){
        return  mAllergens.size();
    }

    public int getAllergen(int index){
        return mAllergens.get(index);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getSpecialOrder() {
        return mSpecialOrder;
    }

    public void setSpecialOrder(String specialOrder) {
        mSpecialOrder = specialOrder;
    }
}
