package com.veronicacordobes.restomatic.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Menu;

import java.util.List;


public class DishListFragment extends Fragment{

    private OnDishSelectedListener mOnDishSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View root = inflater.inflate(R.layout.fragment_dish_list, container, false);
        //Accedemos al ListView
        final ListView list = (ListView) root.findViewById(android.R.id.list);

        AsyncTask<Void, Void, Menu> menuDownloader = new AsyncTask<Void, Void, Menu>() {
            @Override
            protected Menu doInBackground(Void... voids) {
                return Menu.getInstance();
            }

            @Override
            protected void onPostExecute(final Menu menu) {
                //Creamos el adaptador
                DishListAdapter adapter = new DishListAdapter(
                        getActivity(),
                        menu.getDishes());

                //Asignamos el adaptador a la lista
                list.setAdapter(adapter);

                //Asignamos el listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.v("DishListFragment", "Se ha seleccionado el plato " + position);
                        if (mOnDishSelectedListener != null) {
                            mOnDishSelectedListener.onDishSelected(menu.getDish(position), position);
                        }
                    }
                });
            }

        };

        menuDownloader.execute();

        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity() instanceof  OnDishSelectedListener){
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(getActivity() instanceof  OnDishSelectedListener){
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnDishSelectedListener = null;
    }


    public interface OnDishSelectedListener {
        void onDishSelected(Dish dish, int position);
    }

    class DishListAdapter extends ArrayAdapter<Dish>{
        public DishListAdapter(Context context, List<Dish> dishList){
            super(context, R.layout.list_item_dish, dishList);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dishRow = inflater.inflate(R.layout.list_item_dish, parent, false);

            ImageView dishImage = (ImageView)dishRow.findViewById(R.id.dish_image);
            TextView dishName = (TextView)dishRow.findViewById(R.id.dish_name);
            TextView dishPrice = (TextView)dishRow.findViewById(R.id.dish_price);
            ImageView allergenImage1 = (ImageView)dishRow.findViewById(R.id.allergen_image_1);
            ImageView allergenImage2 = (ImageView)dishRow.findViewById(R.id.allergen_image_2);

            Dish currentDish = getItem(position);

            dishImage.setImageResource(currentDish.getImage());
            dishName.setText(currentDish.getName());
            dishPrice.setText(String.format("%s Doblones", String.valueOf(currentDish.getPrice())));
            if(currentDish.getAllergenCount()>0){
                allergenImage1.setImageResource(currentDish.getAllergen(0));
            }
            if(currentDish.getAllergenCount()>1){
                allergenImage2.setImageResource(currentDish.getAllergen(1));
            }

            return dishRow;
        }
    }
}
