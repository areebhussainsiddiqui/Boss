package com.example.stackcodes.boss.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stackcodes.boss.Adapters.Adapter_Summary_Sales;
import com.example.stackcodes.boss.Adapters.Adapter_Summary_bill;
import com.example.stackcodes.boss.Model.Model_Summary_Main;
import com.example.stackcodes.boss.R;
import com.example.stackcodes.boss.SqlLite.DB;

import java.util.ArrayList;
import java.util.List;

public class Total_Sales_Activity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView RV;
    private LinearLayout HomeIV,SummaryIV;
    private Adapter_Summary_Sales bill;
    private List <Model_Summary_Main> model;
    private TextView Total;
    private DB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_total__sales_);
        database = new DB(Total_Sales_Activity.this);
        FindAllIDs ();
        db ();
       Total.setText ("RM "+ String.format("%.2f", database.GrandAmount ()));
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
    private void FindAllIDs(){
        RV = findViewById (R.id.Sales_RV);
        HomeIV = findViewById (R.id.Sales_Home);
        SummaryIV = findViewById (R.id.Sales_Summary);
        Total = findViewById (R.id.Sales_total);

    }

    @Override
    public void onClick(View v) {
    if(v == HomeIV){
        database.Delete_AllData ();

        startActivity (new Intent (Total_Sales_Activity.this,MainActivity.class));
    }
    if(v == SummaryIV){
        Toast.makeText (getApplicationContext (), "Yor're on Summary Screen", Toast.LENGTH_SHORT).show ( );
    }
    }

    private void db() {
        model = new ArrayList <> ( );
        model = database.listsale ( );
        RV.setHasFixedSize(true);
        bill = new Adapter_Summary_Sales (Total_Sales_Activity.this,model);

    /*    bill = new Adapter_Summary_bill (model, new cart_Adapter.OnTotalPriceChanger ( ) {
            @Override
            public void onPriceChange(int change) {
                int subtract = Integer.parseInt (database.DisplayInfo ())-change;
                database.SaveInfo (String.valueOf (subtract));
                totalAmount.setText (database.DisplayInfo ());
            *//*   int  a =  database.tot ();
               a = a -change;
               int cartBill = database.tot ()-change;
               database.SaveInfo (String.valueOf (total));
               totalAmount.setText (String.valueOf (a));
               database.SaveInfo (String.valueOf (a));
               totalAmount.setText ( database.DisplayInfo ());
            *//*
            }
    */    /*);*/

        final RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager (getApplicationContext ( ));
        RV.setLayoutManager (reLayoutManager);
        RV.setItemAnimator (new DefaultItemAnimator ( ));
        RV.setAdapter (bill);
        bill.notifyDataSetChanged ( );

    }

}
