package com.example.stackcodes.boss.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.stackcodes.boss.Adapters.Adapter_Summary_bill;
import com.example.stackcodes.boss.Model.Model_Summary_Main;
import com.example.stackcodes.boss.R;
import com.example.stackcodes.boss.SqlLite.DB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Purchase_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView disc_TV,Price_TV;
    private LinearLayout Home,Summary;
    private Button  newOrd_btn;
    public  String sales = "";
    private RecyclerView RV;
    private DB database;
    private Adapter_Summary_bill bill;
    private List <Model_Summary_Main> model;
    double dis;
    private MainActivity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_purchase);
        FindAllIDs ();
        main = new MainActivity ();
        database = new DB (Purchase_Activity.this);
        db ();
        dis =   database.TotalAmount ();
        sales =  String.format("%.2f", dis);
        Price_TV.setText ("RM "+ sales );
        Log.d ("BLACK", "onCreate: "+ database.ValueParse ());
           if(dis != database.ValueParse ()) {
               if (dis >= 30.0 && dis <= 49.9) {
                   disc_TV.setText ("-10 %");
               } else if (dis >= 50.0) {
                   disc_TV.setText ("-15 %");
               }
               else{
                   disc_TV.setText ("0 %");
               }
           }
            else{
                disc_TV.setText ("0 %");
            }

    }

    private void FindAllIDs(){
        disc_TV = findViewById (R.id.Purchase_DisTV);
        Price_TV = findViewById (R.id.Purchase_PriceTV);
        newOrd_btn = findViewById (R.id.Purchase_btn);
        RV = findViewById (R.id.Purchase_RV);
        Home = findViewById (R.id.Purchase_Home);
        Summary = findViewById (R.id.Purchase__Summary);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
    @Override
    public void onClick(View v) {
        if(v == newOrd_btn){
            database.Delete_AllData ();

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat ("dd-MM-yyyy hh:mm:ss");
            String date = df.format(c);
            SharedPreferences preferences = getApplicationContext ().getSharedPreferences ("AmountInfo",Context.MODE_PRIVATE);
            String Burger_details  = preferences.getString ("Burger","");
            Log.d ("BLACK", "onClick: "+ Burger_details+"\n" +date +"\n" + dis);
            database.add_Summary (Burger_details,dis,date);
            temp_Remove ();
            main.clicker = 0;

            startActivity (new Intent (Purchase_Activity.this,MainActivity.class));
        }
        if(v == Summary){
            database.Delete_AllData ();
        Toast.makeText (getApplicationContext (),"ORDER NOT CONFIRMED",Toast.LENGTH_SHORT).show ();
            startActivity (new Intent (Purchase_Activity.this,Total_Sales_Activity.class));
        }
        if(v == Home){
            database.Delete_AllData ();
            Toast.makeText (getApplicationContext (),"ORDER NOT CONFIRMED",Toast.LENGTH_SHORT).show ();
            startActivity (new Intent (Purchase_Activity.this,MainActivity.class));
        }
    }

    public void db() {
        model = new ArrayList <> ( );
        model = database.listorder ( );
        RV.setHasFixedSize(true);
        bill = new Adapter_Summary_bill (Purchase_Activity.this,model);
        final RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager (getApplicationContext ( ));
        RV.setLayoutManager (reLayoutManager);
        RV.setItemAnimator (new DefaultItemAnimator ( ));
        RV.setAdapter (bill);
        bill.notifyDataSetChanged ( );



    }
    private  void temp_Remove(){
        SharedPreferences preferences = getApplicationContext ().getSharedPreferences ("AmountInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit ();
        editor.putInt ("TotalAmount",0);
        editor.putString ("Burger","");
        editor.apply ();
    }



}
