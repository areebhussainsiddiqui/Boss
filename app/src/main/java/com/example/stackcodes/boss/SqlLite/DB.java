package com.example.stackcodes.boss.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.stackcodes.boss.Model.Model_Summary_Main;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    double iHits;
    int i=0;
    String NAMES;
    String a= "";
    Context context;
    public double tot;
    private	static final int DATABASE_VERSION =	5;
    int c;
    double totalAmount;

    public static String DATABASE = "BOSS.db";

    public static String  TableName             = "Burger_Bill_Table";
    private static String Col_Burger_Name       = "Burger_name";
    private static String Col_Burger_Price      = "Burger_price";
    private static String Col_Burger_Quantity   = "Burger_qty";
    private static String Col_TotalPrice        = "Burger_sub_total";

    public static String  TableName2             = "Burger_Summary_Table";
    private static String Col_Order_Detail       = "Order_Detail";
    private static String Col_Order_Price        = "Order_Amount";
    private static String Col_Order_Date         = "Order_Date";


    private static String  TableName3   = "_Nothing";
    private static String noting_col    = "noting_col";


    public DB(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_SUMMARY_TABLE = "CREATE	TABLE " + TableName2 + "(" + Col_Order_Detail + " TEXT NOT NULL,"+ Col_Order_Price + " INTEGER NOT NULL,"+ Col_Order_Date + " TEXT NOT NULL" +")";
        String	CREATE_BLL_TABLE = "CREATE	TABLE " + TableName + "( "+Col_Burger_Name + " TEXT NOT NULL,"+ Col_Burger_Price + " INTEGER NOT NULL,"+ Col_Burger_Quantity + " INTEGER NOT NULL," + Col_TotalPrice + " INTEGER " + ")";
        String	not = "CREATE TABLE " + TableName3 + "( " + noting_col + " INTEGER NOT NULL " + ")";


        db.execSQL(CREATE_BLL_TABLE);
        db.execSQL (CREATE_SUMMARY_TABLE);
        db.execSQL (not);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        db.execSQL("DROP TABLE IF EXISTS " + TableName2);
        db.execSQL("DROP TABLE IF EXISTS " + TableName3);

        onCreate(db);
    }

    public void add_Single(String Burger,double price, int Quantity){
        ContentValues values = new ContentValues();
        values.put(Col_Burger_Name,Burger);
        values.put(Col_Burger_Price,price);//1350
        values.put(Col_Burger_Quantity,Quantity);
        values.put (Col_TotalPrice,price*Quantity);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TableName, null, values);
    }
    public ArrayList <Model_Summary_Main> listorder(){
        String sql = "select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Model_Summary_Main> Store = new ArrayList<>();
        Model_Summary_Main dataModel = null;
        Cursor cursor = db.rawQuery(sql, null);
        Cursor cur = db.rawQuery("SELECT SUM("+ Col_TotalPrice +") FROM "+TableName, null);
        Cursor curs = db.rawQuery("SELECT SUM("+ Col_Burger_Name +") FROM "+TableName, null);
        curs.moveToNext ();
        cur.moveToFirst ();
        if(cursor.moveToFirst()){
            do{
          //      int Burger_id = Integer.parseInt(cursor.getString(0));
                String    Burger_name = cursor.getString(cursor.getColumnIndexOrThrow("Burger_name"));
          //      int Burger_price = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Burger_price")));
                int    Burger_quantity = cursor.getInt (cursor.getColumnIndexOrThrow("Burger_qty"));
            //    int total = (Burger_quantity)*(Burger_price);
                Double TotalPrice = cursor.getDouble (cursor.getColumnIndexOrThrow("Burger_sub_total"));
                iHits =cur.getDouble (0);
                NAMES = curs.getString (0);
                i = i+1;
                Store.add(new Model_Summary_Main (i,Burger_name,Burger_quantity,TotalPrice));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return Store;
    }


    public void add_Summary(String Details,double price, String Date){
        ContentValues values = new ContentValues();
        values.put(Col_Order_Detail,Details);
        values.put(Col_Order_Price,price);//1350
        values.put(Col_Order_Date, Date);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TableName2, null, values);
    }


    public ArrayList <Model_Summary_Main> listsale(){
        String sql = "select * from " + TableName2;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Model_Summary_Main> Store = new ArrayList<>();
        Model_Summary_Main dataModel = null;
        Cursor cursor = db.rawQuery(sql, null);
        Cursor cur = db.rawQuery("SELECT SUM("+ Col_Order_Price +") FROM "+TableName2, null);
        cur.moveToFirst ();
        if(cursor.moveToFirst()){
            do{
                String    Burger_name = cursor.getString(cursor.getColumnIndexOrThrow("Order_Detail"));
                String    Ordr_Date = cursor.getString (cursor.getColumnIndexOrThrow("Order_Date"));
                Double    TotalPrice = cursor.getDouble (cursor.getColumnIndexOrThrow("Order_Amount"));
                totalAmount =cur.getDouble (0);
                i = i+1;
                Store.add(new Model_Summary_Main (Ordr_Date,Burger_name,TotalPrice));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return Store;
    }

    public double GrandAmount (){

   return totalAmount; }
    public double TotalAmount() {

        Cursor cursor = getCheck ();

            while (cursor.moveToNext ()){
            String  c = cursor.getString (0);
            double d = Double.valueOf (c);
        if(d != 0) {

            if (( iHits >= 30.0 ) && ( iHits <= 49.9 )) {
                tot = iHits - ( 0.10 * iHits );
            } else if (( iHits >= 50.0 )) {
                tot = iHits - ( 0.15 * iHits );
            } else {
                tot = iHits;
            }
            Log.d ("BLACK", "TotalAmount: "+d);
            deletePerson (Integer.parseInt (c));
            return tot;
        }
        else{
            tot = iHits;
            Log.d ("BLACK", "TotalAmount: "+d);
            deletePerson (Integer.parseInt (c));
            return tot;
        }

            }
            Log.d ("BLACK", "TotalAmount: "+c);
    return c;
    }


    public double ValueParse(){
        return iHits;
    }
    public void update_BurgerValue(String Burger, double price, int Quantity){
        ContentValues values = new ContentValues();
        values.put(Col_Burger_Name,Burger);
        values.put(Col_Burger_Price,price);//1350
        values.put(Col_Burger_Quantity,Quantity);
        values.put (Col_TotalPrice,price*Quantity);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TableName, values, Col_Burger_Name 	+ "	= ?", new String[] { String.valueOf(Burger)});
    }

    public boolean hasData(String Burger, double price, int Quantity) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TableName + " WHERE " + Col_Burger_Name + " =?";

        Cursor cursor = db.rawQuery(selectString, new String[] {Burger});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;
            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            update_BurgerValue (Burger,price,Quantity);
        }

        cursor.close();
        db.close();
        return hasObject;
    }
    public String getAllElements() {


        // Select All Query
        String selectQuery = "SELECT " + Col_Burger_Name + " FROM " + TableName ;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        String abc;
                        //only one column
                        abc  =cursor.getString(0);

                        //you could add additional columns here..
//                        list.add(obj);
                        a = a +"\n " + abc +",";
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        return a;
    }


    public SQLiteDatabase delete_(String Burger  ) {
        SQLiteDatabase db = this.getWritableDatabase ();
        db.delete(TableName, Col_Burger_Name + " = ?",new String[] {Burger});
        return db;
    }

    public SQLiteDatabase Delete_AllData() {
        SQLiteDatabase db = this.getWritableDatabase ();
        db.execSQL("delete from "+ TableName);
        return db;
    }

    public boolean check(int not){
        ContentValues values = new ContentValues();
        values.put(noting_col,not);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TableName3, null, values);
        return true;
    }


    public Cursor getCheck() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( " SELECT *  FROM " + TableName3  , null );

        return res;
    }

    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName3,
                noting_col + " = ? ",
                new String[] { Integer.toString(id) });
    }





}
