package com.veronicacordobes.restomatic.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.TableRecyclerViewAdapter;
import com.veronicacordobes.restomatic.model.Dish;
import com.veronicacordobes.restomatic.model.Table;
import com.veronicacordobes.restomatic.model.Tables;

public class TableActivity extends AppCompatActivity {
    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";
    private int tableIndex;
    private Table mTable;

    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Tables tables = Tables.getInstance();
        tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);
        getSupportActionBar().setTitle(String.format("Mesa %s", tableIndex+1));

        mTable = tables.getTable(tableIndex);

        mList = (RecyclerView) findViewById(android.R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setItemAnimator(new DefaultItemAnimator());

        mList.setAdapter(new TableRecyclerViewAdapter(mTable.getDishes(), this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_table, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.show_menu ){
            Intent intent = new Intent(this, DishActivity.class);
            intent.putExtra(TableActivity.EXTRA_TABLE_INDEX, tableIndex);
            startActivity(intent);
            return true;
        } else if(item.getItemId() == R.id.show_check){
            final Intent intent = new Intent(this, TablesListActivity.class);
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.totalCheck);
            alertDialog.setMessage(String.format("%d Doblones", mTable.getCheck()));
            alertDialog.setPositiveButton("Pagado", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mTable.emptyTable();
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.setCancelable(true);
                }
            });

            alertDialog.show();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, TablesListActivity.class);
            startActivity(intent);
            return true;
        }

        return superValue;
    }
}
