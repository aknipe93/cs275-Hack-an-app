package com.example.alex.hack_a_app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 3/17/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "favoriteHacks";
    // Contacts table name
    private static final String TABLE_FAV = "fav";
    // Shops Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_URLHACK = "urlhack";
    //    private static final String KEY_SH_ADDR = "shop_address";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FAV + "("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, " +
                KEY_DATE + " TEXT," + KEY_LOCATION + " TEXT, " +KEY_URLHACK + " TEXT " +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV);
// Creating tables again
        onCreate(db);
    }

    // Adding new shop
    public void addFav(favDB fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, fav.getName());
        values.put(KEY_DATE, fav.getDate());
        values.put(KEY_URLHACK, fav.getUrlHack());
        values.put(KEY_LOCATION, fav.getLocation());
// Inserting Row
        db.insert(TABLE_FAV, null, values);
        db.close(); // Closing database connection
    }

    // Getting one shop
    public favDB getFav(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAV, new String[]{
                        KEY_NAME, KEY_DATE, KEY_LOCATION,KEY_URLHACK}, KEY_ID + "=?",
                new String[]{String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        favDB last = new favDB(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
// return shop
        return last;
    }

    // Getting shops Count
    public int getFavCount() {
        String countQuery = "SELECT * FROM " + TABLE_FAV;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
// return count
        return count;
    }


//    // Deleting a shop
//    public void deleteFav(favDB shop) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_FAV, KEY_ID + " = ?",
//                new String[]{String.valueOf(shop.getId())});
//        db.close();
//    }
public void delete_byID(int id){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_FAV, KEY_ID+"="+id, null);
    db.close();
}

    // Getting All Shops
    public List<favDB> getAllFavs() {
        List<favDB> favList = new ArrayList<favDB>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FAV;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                favDB fav = new favDB();
                fav.setId(Integer.parseInt(cursor.getString(0)));
                fav.setName(cursor.getString(1));
                fav.setDate(cursor.getString(2));
                fav.setLocation(cursor.getString(3));
                fav.setUrlHack(cursor.getString(4));
//                shop.setAddress(cursor.getString(2));
// Adding contact to list
                favList.add(fav);
            } while (cursor.moveToNext());
        }
// return contact list
        return favList;
    }

}
