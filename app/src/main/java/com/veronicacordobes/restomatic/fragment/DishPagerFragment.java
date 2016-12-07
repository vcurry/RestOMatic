package com.veronicacordobes.restomatic.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Menu;

/**
 * A simple {@link Fragment} subclass.
 */
public class DishPagerFragment extends Fragment {

    private static final String ARG_DISH_INDEX = "ARG_DISH INDEX";

    private Menu mMenu;
    private int mInitialDishIndex;
    private ViewPager mPager;


    public DishPagerFragment() {

    }

    public static  DishPagerFragment newInstance(int dishIndex){
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_DISH_INDEX, dishIndex);
        DishPagerFragment dishPagerFragment = new DishPagerFragment();
        dishPagerFragment.setArguments(arguments);
        return dishPagerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if(getArguments() != null){
            mInitialDishIndex = getArguments().getInt(ARG_DISH_INDEX,0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dish_pager, container, false);

        mPager = (ViewPager) root.findViewById(R.id.view_pager);

        AsyncTask<Void,Void,Menu> menuDownloader = new AsyncTask<Void, Void, Menu>() {
            @Override
            protected Menu doInBackground(Void... voids) {
                return Menu.getInstance();
            }

            @Override
            protected void onPostExecute(Menu menu) {
                mMenu = Menu.getInstance();
                mPager.setAdapter(new DishPagerAdapter(getFragmentManager(),mMenu));
                mPager.setCurrentItem(mInitialDishIndex);
            }
        };

        menuDownloader.execute();

        return root;
    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pager, menu);
    }

    public void showDish(int position){
        mPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.previous){
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            return true;
        } else if(item.getItemId() == R.id.next){
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            return true;
        }

        return superValue;
    }

    @Override
    public void onPrepareOptionsMenu(android.view.Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuPrev = menu.findItem(R.id.previous);
        MenuItem menuNext = menu.findItem(R.id.next);

        menuPrev.setEnabled(mPager.getCurrentItem() > 0);
        menuNext.setEnabled(mPager.getCurrentItem() < mMenu.getCount() - 1);
    }
}


class DishPagerAdapter extends FragmentPagerAdapter {

    private Menu mMenu;

    public DishPagerAdapter(FragmentManager fm, Menu menu) {
        super(fm);
        mMenu = menu;
    }

    @Override
    public Fragment getItem(int position) {
        Dish dish = mMenu.getDishes().get(position);
        DishFragment fragment = DishFragment.newInstance(dish);



        return fragment;
    }

    @Override
    public int getCount() {
        return mMenu.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);

        Dish dish = mMenu.getDish(position);

        return dish.getName();
    }
}