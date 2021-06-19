package com.example.stackcodes.boss.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.stackcodes.boss.R;
import com.example.stackcodes.boss.SqlLite.DB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener   {
    private ImageView       add_b1,add_b2,add_b3,add_b4,Remove_b1,Remove_b2,
                            Remove_b3,Remove_b4,burger1_CV, burger2_CV,burger3_CV,burger4_CV;;
    private LinearLayout    home_IV,summary_IV;
    private TextView        count_b1,count_b2,count_b3,count_b4,QB1,QB2,QB3,QB4;
    public int clicker = 0;
    private Button          dis_btn,nor_btn;
    private  int c1,c2,c3,c4;
    private DB database;
    public  int dist = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        FindAllID ();
        database = new DB (this);

    }
    private void FindAllID(){
         burger1_CV = findViewById (R.id.main_burger1);
         burger2_CV = findViewById (R.id.main_burger2);
         burger3_CV = findViewById (R.id.main_burger3);
         burger4_CV = findViewById (R.id.main_burger4);

         home_IV    = findViewById (R.id.Main_Home);
         summary_IV = findViewById (R.id.Main_Summary);

         add_b1     = findViewById (R.id.Main_add_b1);
         add_b2     = findViewById (R.id.Main_add_b2);
         add_b3     = findViewById (R.id.Main_add_b3);
         add_b4     = findViewById (R.id.Main_add_b4);

         Remove_b1  = findViewById (R.id.Main_remove_b1);
         Remove_b2  = findViewById (R.id.Main_remove_b2);
         Remove_b3  = findViewById (R.id.Main_remove_b3);
         Remove_b4  = findViewById (R.id.Main_remove_b4);

         count_b1   = findViewById (R.id.Main_count_b1);
         count_b2   = findViewById (R.id.Main_count_b2);
         count_b3   = findViewById (R.id.Main_count_b3);
         count_b4   = findViewById (R.id.Main_count_b4);

      //   RV         = findViewById (R.id.Main_RV);
         dis_btn    = findViewById (R.id.btn_Discount);
         nor_btn    = findViewById (R.id.btn_Normal);

        QB1 =findViewById (R.id.Q_B1);
        QB2 =findViewById (R.id.Q_B2);
        QB3 =findViewById (R.id.Q_B3);
        QB4 =findViewById (R.id.Q_B4);
    }


    @Override
    public void onClick(View v) {
        if(v == burger1_CV){
            if(c1 == 0) {
            count_b1.setText ("1");
            QB1.setText ("1");
            c1= 1;
            clicker  = clicker+1;
            UpdateAndAdd ("After Workout Burger", 8.50, c1);
            }
        }
        if(v == burger2_CV){
            if(c2 == 0) {
                count_b2.setText ("1");
                QB2.setText ("1");
                clicker  = clicker+1;

                c2 =1;
                UpdateAndAdd ("Big Fat Burger",9.50,c2);

            }
        }
        if(v == burger3_CV){
            if(c3 == 0) {
                count_b3.setText ("1");
                QB3.setText ("1");
                clicker  = clicker+1;

                c3 = 1;
                UpdateAndAdd ("First Meal Burger  ",7.20,c3);

            }
        }
        if(v == burger4_CV){
            if(c4 == 0) {
                count_b4.setText ("1");
                QB4.setText ("1");
                clicker  = clicker+1;

                c4 = 1;
                UpdateAndAdd("The Boring Burger  ",13.80,c4);
            }
        }

        if(v == add_b1) {
                c1 = c1 + 1;
            clicker  = clicker+1;
            count_b1.setText (String.valueOf (c1));
                QB1.setText (String.valueOf (c1));
                UpdateAndAdd("After Workout Burger",8.50,c1);

        }
        if(v == add_b2) {
            c2= c2+1;
            clicker  = clicker+1;
            count_b2.setText (String.valueOf (c2));
            QB2.setText (String.valueOf (c2));
            UpdateAndAdd ("Big Fat Burger",9.50,c2);
        }
        if(v == add_b3) {
            c3= c3+1;
            clicker  = clicker+1;

            count_b3.setText (String.valueOf (c3));
            QB3.setText (String.valueOf (c3));
            UpdateAndAdd ("First Meal Burger",7.20,c3);
        }
        if(v == add_b4) {
            c4= c4+1;
            clicker  = clicker+1;
            count_b4.setText (String.valueOf (c4));
            QB4.setText (String.valueOf (c4));
            UpdateAndAdd("The Boring Burger",13.80,c4);

        }

        if(v == Remove_b1) {
                c1 = c1-1;
                if (c1 <= 0) {
                    database.delete_ ("After Workout Burger");
                    clicker =0;
                    c1 = 0;
                    count_b1.setText (String.valueOf (c1));
                    QB1.setText (String.valueOf (c1));

                }else {
                    count_b1.setText (String.valueOf (c1));
                    QB1.setText (String.valueOf (c1));
                    clicker = clicker-1;
                    UpdateAndAdd ("After Workout Burger", 8.50, c1);

            }
        }
        if(v == Remove_b2) {
            c2 = c2 - 1;
            if (c2 <= 0) {
                c2 = 0;
                count_b2.setText (String.valueOf (c2));
                QB2.setText (String.valueOf (c2));
                clicker = 0;
                database.delete_ ("Big Fat Burger");

            } else {
                count_b2.setText (String.valueOf (c2));
                QB2.setText (String.valueOf (c2));
                clicker = clicker-1;
                UpdateAndAdd ("Big Fat Burger", 9.50, c2);
            }
          }
        if(v == Remove_b3) {
                c3 = c3-1;
            if (c3 <= 0) {
                c3 = 0;
                count_b3.setText (String.valueOf (c3));
                QB3.setText (String.valueOf (c3));
                clicker = 0;
                database.delete_ ("First Meal Burger");

            }else {
                count_b3.setText (String.valueOf (c3));
                QB3.setText (String.valueOf (c3));
                clicker  = clicker-1;
                UpdateAndAdd ("First Meal Burger",7.20,c3);

            }
        }
        if(v == Remove_b4) {
                c4 = c4-1;
            if (c4 <= 0) {
                c4 = 0;
                count_b4.setText (String.valueOf (c4));
                QB4.setText (String.valueOf (c4));
                clicker = 0;
                database.delete_ ("The Boring Burger");

            } else {
                count_b4.setText (String.valueOf (c4));
                QB4.setText (String.valueOf (c4));
                clicker = clicker-1;
                UpdateAndAdd ("The Boring Burger",13.80,c4);
            }

        }

        if(v == home_IV){
            Toast.makeText (getApplicationContext (),"You're Already on Home Screen",Toast.LENGTH_SHORT).show ();
        }
        if(v == summary_IV){
            startActivity (new Intent (MainActivity.this,Total_Sales_Activity.class));

        }

        if(v == dis_btn){
            startActivity (new Intent (MainActivity.this,Purchase_Activity.class));
            database.check (1);

        }
        if(v ==nor_btn){
            startActivity (new Intent (MainActivity.this,Purchase_Activity.class));

            database.check (0);
          }

        SaveInfo (clicker);

    }

    private void UpdateAndAdd(String Burger,double price, int Quantity){
        if(database.hasData (Burger,price,Quantity)== true){
        }
        else {
            database.add_Single (Burger, price, Quantity);
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    public void SaveInfo(int IHits){

        SharedPreferences preferences = getApplicationContext().getSharedPreferences ("AmountInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit ();
        editor.putInt ("TotalAmount",IHits);
        editor.apply ();
    }


}

