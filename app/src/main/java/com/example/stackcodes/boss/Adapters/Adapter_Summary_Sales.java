package com.example.stackcodes.boss.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.stackcodes.boss.Activity.Total_Sales_Activity;
import com.example.stackcodes.boss.Model.Model_Summary_Main;
import com.example.stackcodes.boss.R;

import java.util.List;

public class Adapter_Summary_Sales extends RecyclerView.Adapter<Adapter_Summary_Sales.Myholder> {

    List <Model_Summary_Main> dataModelArrayList;

    public Adapter_Summary_Sales(Total_Sales_Activity total_sales_activity, List <Model_Summary_Main> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView Details,Time_order, Amount,S_no;

        public Myholder(View itemView) {
            super(itemView);
            S_no    = itemView.findViewById (R.id.itemlist_S_no);
            Details  = itemView.findViewById(R.id.itemlist_S_detail);
            Time_order =  itemView.findViewById(R.id.itemlist_S_time);
            Amount = itemView.findViewById (R.id.itemlist_S_Amount);
        }
    }


    @Override
    public Adapter_Summary_Sales.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_summary_sales,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new Adapter_Summary_Sales.Myholder(view);

    }

    @Override
    public void onBindViewHolder(Adapter_Summary_Sales.Myholder holder, int position) {
        Model_Summary_Main dataModel = dataModelArrayList.get(position);
        double sales = dataModel.getSales ();

        holder.S_no.setText(String.valueOf (position+1) );
        holder.Time_order.setText(dataModel.getdate ());
        holder.Details.setText (dataModel.getDetails ());
        holder.Amount.setText ("RM " + String.format("%.2f", sales));

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}

