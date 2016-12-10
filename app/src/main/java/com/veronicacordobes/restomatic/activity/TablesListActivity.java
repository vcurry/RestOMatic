package com.veronicacordobes.restomatic.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.veronicacordobes.restomatic.R;
import com.veronicacordobes.restomatic.model.Table;
import com.veronicacordobes.restomatic.model.Tables;

public class TablesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("ScummBar");

        ListView list = (ListView) findViewById(R.id.activity_tables_list);

        Tables tables = new Tables();

        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(this, android.R.layout.simple_list_item_1, tables.getTables());

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TablesListActivity.this, TableActivity.class);
                intent.putExtra(TableActivity.EXTRA_TABLE_INDEX, i);
                startActivity(intent);

            }
        });
    }

}
