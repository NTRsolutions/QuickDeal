package com.example.sagar.quickdeal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sagar on 20/4/17.
 */

public class LeadCardAdapter extends RecyclerView.Adapter<LeadCardAdapter.MyHolder>
{
    public RecyclerView re;
    private List<LeadCardModel> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView requirements;
        TextView days;

        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.requirements = (TextView) itemView.findViewById(R.id.requirements);
            this.days=(TextView)itemView.findViewById(R.id.days);

        }
    }
    public LeadCardAdapter(Context c, List<LeadCardModel> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;


    }
    @Override
    public MyHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leads_card, parent, false);
        MyHolder myHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.card_grid);
        return myHolder;
    }

    @Override
    public void onBindViewHolder( MyHolder holder,final int position)
    {
        TextView name_card = holder.name;
        TextView require_card = holder.requirements;
        final TextView days_card=holder.days;

        //setting data in the card elements
        name_card.setText(dataSet.get(position).name);
        require_card.setText(dataSet.get(position).requirements);
        days_card.setText(dataSet.get(position).days_update);
        if(dataSet.get(position).name.equals("RAKESH PATEL")||dataSet.get(position).name.equals("JOSEPH SMITH"))
        {
            name_card.setTextColor(Color.rgb(255,204,51));
        }
        else {
            name_card.setTextColor(Color.rgb(62,82,179));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                venueAdapterClickCallbacks.onCardClick(dataSet.get(position).name,dataSet.get(position).requirements,dataSet.get(position).days_update);

            }
        });


    }

    //Declaring interface for making card clickable

    public interface VenueAdapterClickCallbacks {
        void onCardClick( String n,String r,String d);

    }

    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }


}
