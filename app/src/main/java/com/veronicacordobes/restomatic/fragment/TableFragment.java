/*
package com.veronicacordobes.restomatic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Table;
import com.veronicacordobes.restomatic.model.Tables;


public class TableFragment extends Fragment {

    private RecyclerView mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        ListView list = (ListView) root.findViewById(R.id.table_list);

        Tables tables = new Tables();
        Table table = tables.getTable(0);

        ArrayAdapter<Dish> adapter = new ArrayAdapter<Dish>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                table.getDishes()
        );



        return root;


    }
}
*/
