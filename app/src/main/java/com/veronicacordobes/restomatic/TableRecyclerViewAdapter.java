package com.veronicacordobes.restomatic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Table;

import java.util.LinkedList;


public class TableRecyclerViewAdapter extends RecyclerView.Adapter<TableRecyclerViewAdapter.TableViewHolder> {
    private LinkedList<Dish> mDishes;
    private Context mContext;

    public TableRecyclerViewAdapter(LinkedList<Dish> dish, Context context){
        super();
        mDishes = dish;
        mContext = context;
    }

    @Override
    public TableRecyclerViewAdapter.TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_table, parent,false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TableRecyclerViewAdapter.TableViewHolder holder, int position) {
        holder.bindTable(mDishes.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    protected class TableViewHolder extends RecyclerView.ViewHolder {
        private TextView mDishName;
        private TextView mDishSpecials;
        private ImageView mDishImage;

        public TableViewHolder (View itemView) {
            super(itemView);
            mDishName = (TextView) itemView.findViewById(R.id.dish_name);
            mDishSpecials = (TextView) itemView.findViewById(R.id.dish_specials);
            mDishImage = (ImageView) itemView.findViewById(R.id.dish_image);
        }

        public void bindTable(Dish dish, Context context){
            mDishName.setText(dish.getName());
            mDishSpecials.setText(dish.getSpecialOrder());
            mDishImage.setImageResource(dish.getImage());
        }

    }
}
