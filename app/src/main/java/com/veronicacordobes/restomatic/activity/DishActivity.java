package com.veronicacordobes.restomatic.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.fragment.DishListFragment;
import com.veronicacordobes.restomatic.fragment.DishPagerFragment;
import com.veronicacordobes.restomatic.model.Dish;

public class DishActivity extends AppCompatActivity implements DishListFragment.OnDishSelectedListener {

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    private int tableIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dish);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Carta");

        tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);

        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_dish_pager) != null) {
            if (fm.findFragmentById(R.id.fragment_dish_pager) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_dish_pager, new DishPagerFragment())
                        .commit();
            }
        }
        if(fm.findFragmentById(R.id.fragment_dish_list) == null){

            if (fm.findFragmentById(R.id.fragment_dish_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_dish_list, new DishListFragment())
                        .commit();
            }
        }
    }


    @Override
    public void onDishSelected(Dish dish, int position) {
        FragmentManager fm = getFragmentManager();
        DishPagerFragment dishPagerFragment = (DishPagerFragment) fm.findFragmentById(R.id.fragment_dish_pager);
        if(dishPagerFragment != null){
            dishPagerFragment.showDish(position);
        } else {
            Intent intent = new Intent(this, DishPagerActivity.class);
            intent.putExtra(DishPagerActivity.EXTRA_DISH_INDEX, position);
            intent.putExtra(DishPagerActivity.EXTRA_TABLE_INDEX, tableIndex);
            startActivity(intent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return superValue;
    }
}
