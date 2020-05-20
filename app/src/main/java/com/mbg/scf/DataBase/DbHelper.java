package com.mbg.scf.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mbg.scf.classes.Items;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteAssetHelper {


    private static final int DATABASE_VERSION = 1 ;
    Context context;


    public DbHelper(Context context, String lan) {

        super(context, lan, null, DATABASE_VERSION);

        this.context = context;
    }



    public ArrayList<Items> getClass(String classnumber) {
        SQLiteDatabase db =getReadableDatabase();
        Items items = null;
        ArrayList<Items> countryList = new ArrayList<>();
        //openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+classnumber, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            items = new Items(cursor.getString(2),cursor.getString(1), cursor.getString(0));
            countryList.add(items);
            cursor.moveToNext();
        }

        cursor.close();
        //closeDatabase();
        return countryList;
    }




}
