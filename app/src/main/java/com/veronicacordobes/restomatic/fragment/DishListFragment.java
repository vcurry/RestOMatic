package com.veronicacordobes.restomatic.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Menu;


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
                ArrayAdapter<Dish> adapter = new ArrayAdapter<Dish>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
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
}
