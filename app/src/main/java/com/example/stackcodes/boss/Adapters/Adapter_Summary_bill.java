package com.example.stackcodes.boss.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.stackcodes.boss.Activity.MainActivity;
import com.example.stackcodes.boss.Model.Model_Summary_Main;
import com.example.stackcodes.boss.R;
import com.example.stackcodes.boss.SqlLite.DB;

import java.util.List;

public class Adapter_Summary_bill  extends RecyclerView.Adapter<Adapter_Summary_bill.Myholder> {

    private Context mContext;
    private List <Model_Summary_Main> dataModelArrayList;
    DB database;

    public Adapter_Summary_bill(Context context,List<Model_Summary_Main> Arraylist)
    {
        mContext = context;
        dataModelArrayList =Arraylist;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView Burger_Name,Burger_Qunat, Burger_Price;

        public Myholder(View itemView) {
            super(itemView);

            Burger_Name  = itemView.findViewById(R.id.itemlist_burger);
            Burger_Qunat =  itemView.findViewById(R.id.itemlist_Quantity);
            Burger_Price = itemView.findViewById (R.id.itemlist_Amount);
        }
    }


    @Override
    public Adapter_Summary_bill.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_bill_summary,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new Adapter_Summary_bill.Myholder(view);

    }

    @Override
    public void onBindViewHolder(Adapter_Summary_bill.Myholder holder, int position) {
        Model_Summary_Main dataModel = dataModelArrayList.get(position);
        double sales = dataModel.getBurger_price ();
        holder.Burger_Name.setText(dataModel.getBurgerName () );
        holder.Burger_Qunat.setText(String.valueOf(dataModel.getBurgerQuantity ()));
        holder.Burger_Price.setText ("RM "+String.format("%.2f", sales));
        database = new DB (mContext);
        MainActivity main = new MainActivity ();


   if(position == main.clicker){
           String abc  = database.getAllElements ();

            SharedPreferences preferences = mContext.getSharedPreferences ("AmountInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit ();
            editor.putString ("Burger",abc);
            editor.apply ();




 }
}

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}
