package com.veronicacordobes.restomatic.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.activity.DishActivity;
import com.veronicacordobes.restomatic.activity.TableActivity;
import com.veronicacordobes.restomatic.activity.TablesListActivity;
import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Table;
import com.veronicacordobes.restomatic.model.Tables;

public class DishFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_DISH = "dish";
    public static final String ARG_TABLE = "table";

    private TextView mDishName;
    private TextView mDishDescription;
    private TextView mPrice;
    private ImageView mDishImage;
    private ImageView mDishAllergen1;
    private ImageView mDishAllergen2;
    private EditText mSpecialOrder;

    private Table mTable;

    private Dish mDish;

    public static DishFragment newInstance(Dish dish, Table table) {
        DishFragment fragment = new DishFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_DISH, dish);
        arguments.putSerializable(ARG_TABLE, table);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDish = (Dish) getArguments().getSerializable(ARG_DISH);
            mTable = (Table) getArguments().getSerializable(ARG_TABLE);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish, container, false);

        Button orderDishButton = (Button) root.findViewById(R.id.button_order_dish);
        orderDishButton.setOnClickListener(this);

        if (getArguments() != null) {
            mDish = (Dish) getArguments().getSerializable(ARG_DISH);
            mTable = (Table) getArguments().getSerializable(ARG_TABLE);
        }

        mDishName = (TextView) root.findViewById(R.id.dish_name);
        mDishDescription = (TextView) root.findViewById(R.id.dish_description);
        mPrice = (TextView) root.findViewById(R.id.dish_price);
        mDishImage = (ImageView) root.findViewById(R.id.dish_image);
        mSpecialOrder = (EditText) root.findViewById(R.id.special_order);
        mDishAllergen1 = (ImageView) root.findViewById(R.id.allergen_image_1);
        mDishAllergen2 = (ImageView) root.findViewById(R.id.allergen_image_2);

        setDish(mDish);


        return root;
    }

    private void setDish(Dish dish) {
        mDishName.setText(String.valueOf(dish.getName()));
        mDishDescription.setText(String.valueOf(dish.getDescription()));
        mPrice.setText(String.format("%s Doblones", String.valueOf(dish.getPrice())));
        mDishImage.setImageResource(dish.getImage());
        if(dish.getAllergenCount()>0){
            mDishAllergen1.setImageResource(dish.getAllergen(0));
        }
        if(dish.getAllergenCount()>1){
            mDishAllergen2.setImageResource(dish.getAllergen(1));
        }
    }

    @Override
    public void onClick(View view) {

        if(mSpecialOrder.getText().toString() != ""){
            Log.v("DishFragment", mSpecialOrder.getText().toString());
            mDish.setSpecialOrder(mSpecialOrder.getText().toString());
        }

        mTable.addDishes(mDish);
        int tableIndex = mTable.getId() -1;
        Intent intent = new Intent(getActivity(), TableActivity.class);
        intent.putExtra(TableActivity.EXTRA_TABLE_INDEX, tableIndex);
        startActivity(intent);
    }
}

