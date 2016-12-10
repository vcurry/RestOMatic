package com.veronicacordobes.restomatic.model;


import java.util.LinkedList;

public class Tables {

    private LinkedList<Table> mTables;
    private static Tables sInstance = null;

    public static Tables getInstance(){
        if(sInstance == null){
            sInstance = new Tables();
        }

        return sInstance;
    }

    public Tables(){
        mTables = new LinkedList<>();
        mTables.add(new Table(1));
        mTables.add(new Table(2));
        mTables.add(new Table(3));
        mTables.add(new Table(4));
        mTables.add(new Table(5));
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public Table getTable(int position) {
        return mTables.get(position);
    }

    public int getCount() {
        return mTables.size();
    }


}
