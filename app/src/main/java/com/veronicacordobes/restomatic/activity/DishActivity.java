package com.veronicacordobes.restomatic.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.fragment.DishListFragment;
import com.veronicacordobes.restomatic.fragment.DishPagerFragment;
import com.veronicacordobes.restomatic.model.Dish;

public class DishActivity extends AppCompatActivity implements DishListFragment.OnDishSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dish);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_dish_pager) != null) {
            if (fm.findFragmentById(R.id.fragment_dish_pager) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_dish_pager, new DishPagerFragment())
                        .commit();
            }
        }
        if(fm.findFragmentById(R.id.fragment_dish_list) == null){
            DishListFragment dishListFragment = new DishListFragment();
            if (fm.findFragmentById(R.id.fragment_dish_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_dish_list, new DishListFragment())
                        .commit();
            }
        }
    }


    @Override
    public void onDishSelected(Dish dish, int position) {
        Log.v("DishActivity", "Se ha seleccionado el plato " + position);

        FragmentManager fm = getFragmentManager();
        DishPagerFragment dishPagerFragment = (DishPagerFragment) fm.findFragmentById(R.id.fragment_dish_pager);
        if(dishPagerFragment != null){
            dishPagerFragment.showDish(position);
        } else {
            Intent intent = new Intent(this, DishPagerActivity.class);
            intent.putExtra(DishPagerActivity.EXTRA_DISH_INDEX, position);
            startActivity(intent);
        }

    }
}