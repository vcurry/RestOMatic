package com.veronicacordobes.restomatic.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.fragment.DishPagerFragment;

/**
 * Created by veronicacordobes on 1/12/16.
 */

public class DishPagerActivity extends AppCompatActivity{
    public static final String EXTRA_DISH_INDEX = "EXTRA_DISH_INDEX";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dish_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Carta");

        FragmentManager fm = getFragmentManager();
        if(fm.findFragmentById(R.id.fragment_dish_pager) == null){

            int dishIndex = getIntent().getIntExtra(EXTRA_DISH_INDEX, 0);
            DishPagerFragment dishPagerFragment = DishPagerFragment.newInstance(dishIndex);
            fm.beginTransaction()
                    .add(R.id.fragment_dish_pager, dishPagerFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return superValue;
    }
}
