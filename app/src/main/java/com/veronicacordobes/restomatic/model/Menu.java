package com.veronicacordobes.restomatic.model;


import android.util.Log;

import com.veronicacordobes.restomatic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class Menu {
    private static Menu sInstance = null;
    private LinkedList<Dish> mDishes;

    public static Menu getInstance(){
        Log.v("Menu", "entramos en getInstance");
        if(sInstance == null){
            try {
                sInstance = downloadMenu();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return sInstance;
    }

    private static Menu downloadMenu() throws IOException, JSONException {
        Menu menu = new Menu();
        menu.mDishes = new LinkedList<>();
        Log.v("Menu", "entramos en download");
        URLConnection conn = new URL("http://www.mocky.io/v2/5847d7c43f0000c62cfe6a31").openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line = null;

        while((line = reader.readLine()) != null){
            response.append(line);
        }

        reader.close();

        JSONArray dishes = new JSONArray(response.toString());

        for (int dishIndex = 0; dishIndex < dishes.length(); dishIndex++){
            String name = null;
            String description = null;
            int price = 0;
            String image = null;

            JSONObject jsonDish = dishes.getJSONObject(dishIndex);
            if(jsonDish.has("name")){
                name = jsonDish.getString("name");
                description = jsonDish.getString("description");
                price = jsonDish.getInt("price");
                image = jsonDish.getString("image");
                int imageInt = Integer.parseInt(image);
                int imageResource = R.drawable.chicken_with_a_pulley;
                switch (imageInt){
                    case 0:
                        imageResource = R.drawable.chicken_with_a_pulley;
                        break;
                    case 1:
                        imageResource = R.drawable.mentos;
                        break;
                    case 2:
                        imageResource = R.drawable.crackerbox;
                        break;
                    case 3:
                        imageResource = R.drawable.blue_whale;
                        break;
                    case 4:
                        imageResource = R.drawable.piece_of_meat;
                        break;
                    case 5:
                        imageResource = R.drawable.red_fish;
                        break;
                    case 6:
                        imageResource = R.drawable.grog;
                        break;
                    case 7:
                        imageResource = R.drawable.banana;
                        break;
                    case 8:
                        imageResource = R.drawable.yellow_beard_baby;
                        break;
                }

                Dish dish = new Dish(name, description, price, imageResource);
                JSONArray jsonAllergens = jsonDish.getJSONArray("allergens");
                for (int allergenIndex = 0; allergenIndex < jsonAllergens.length(); allergenIndex++){
                    dish.addAllergen(jsonAllergens.getJSONObject(allergenIndex).getString("allergen"));
                }

                menu.mDishes.add(dish);
            }
        }

        return menu;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public Dish getDish(int position){
        return mDishes.get(position);
    }

    public int getCount(){
        return mDishes.size();
    }

}
