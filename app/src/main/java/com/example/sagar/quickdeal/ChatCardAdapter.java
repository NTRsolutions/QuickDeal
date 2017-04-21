package com.example.sagar.quickdeal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sagar on 20/4/17.
 */

public class ChatCardAdapter extends RecyclerView.Adapter<ChatCardAdapter.MyHolder>
{
    public RecyclerView re;
    private List<ChartCardModel> dataSet ;
    public Context context=null;
    ChatCardAdapter.VenueAdapterClickCallbacks venueAdapterClickCallbacks;

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView chat;
        CircularTextView number;
        TextView days;
        ImageView image;

        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.chat = (TextView) itemView.findViewById(R.id.chat);
            this.number=(CircularTextView) itemView.findViewById(R.id.circularTextView);
            this.days=(TextView)itemView.findViewById(R.id.days);
            this.image=(ImageView)itemView.findViewById(R.id.image);

        }
    }
    public ChatCardAdapter(Context c, List<ChartCardModel> data, ChatCardAdapter.VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;


    }
    @Override
    public ChatCardAdapter.MyHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_card, parent, false);
        ChatCardAdapter.MyHolder myHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.card_grid);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(ChatCardAdapter.MyHolder holder, final int position)
    {
        TextView name_card = holder.name;
        TextView chat_card = holder.chat;
        CircularTextView number=holder.number;
        TextView days_card=holder.days;
        ImageView imageView=holder.image;

        name_card.setText(dataSet.get(position).sname);
        chat_card.setText(dataSet.get(position).chat);
        days_card.setText(dataSet.get(position).days);
        imageView.setImageResource(dataSet.get(position).image);

        if(dataSet.get(position).number!=0){
            number.setText(String.valueOf(dataSet.get(position).number));
            number.setStrokeWidth(1);
            number.setStrokeColor("#ffffff");
            number.setSolidColor("#4fc410");
            name_card.setTextColor(Color.rgb(255,204,51));
        }

        else {
            number.setVisibility(View.GONE);
            name_card.setTextColor(Color.rgb(62,82,179));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                venueAdapterClickCallbacks.onCardClick();

            }
        });


    }
    public interface VenueAdapterClickCallbacks {
        void onCardClick();

    }

    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }


}
