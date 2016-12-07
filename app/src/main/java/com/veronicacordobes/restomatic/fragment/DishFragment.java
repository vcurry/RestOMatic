package com.veronicacordobes.restomatic.fragment;


import android.app.Fragment;
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
import com.veronicacordobes.restomatic.model.Dish;

public class DishFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_DISH = "dish";

    private TextView mDishName;
    private TextView mDishDescription;
    private TextView mPrice;
    private ImageView mDishImage;
    private EditText mSpecialOrder;

    private Dish mDish;

    public static DishFragment newInstance(Dish dish) {
        DishFragment fragment = new DishFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_DISH, dish);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDish = (Dish) getArguments().getSerializable(ARG_DISH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish, container, false);

        Button orderDishButton = (Button) root.findViewById(R.id.button_order_dish);
        orderDishButton.setOnClickListener(this);

        //Saco el modelo de los arguments
        if (getArguments() != null) {
            mDish = (Dish) getArguments().getSerializable(ARG_DISH);
        }

        //Accedo a las vistas de la interfaz
        mDishName = (TextView) root.findViewById(R.id.dish_name);
        mDishDescription = (TextView) root.findViewById(R.id.dish_description);
        mPrice = (TextView) root.findViewById(R.id.dish_price);
        mDishImage = (ImageView) root.findViewById(R.id.dish_image);
        mSpecialOrder = (EditText) root.findViewById(R.id.special_order);


        //Actualizo la interfaz con el modelo
        setDish(mDish);


        return root;
    }

    private void setDish(Dish dish) {
        mDishName.setText(String.valueOf(dish.getName()));
        mDishDescription.setText(String.valueOf(dish.getDescription()));
        mPrice.setText(String.format("%s Doblones", String.valueOf(dish.getPrice())));
        mDishImage.setImageResource(dish.getImage());
    }

    @Override
    public void onClick(View view) {

        if(mSpecialOrder.getText().toString() != ""){
            Log.v("DishFragment", mSpecialOrder.getText().toString());
            mDish.setSpecialOrder(mSpecialOrder.getText().toString());
        }
    }
}

