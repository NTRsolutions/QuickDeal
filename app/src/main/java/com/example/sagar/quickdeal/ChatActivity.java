package com.example.sagar.quickdeal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 20/4/17.
 */

public class ChatActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    ChatCardAdapter adapter;
    RecyclerView recyclerView;
    List<ChartCardModel> chartCardModelList;
    PrefManager prefManager;
    Toolbar toolbar;

    int image[]={R.drawable.handshake,R.drawable.chandshake};

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_recycle_grid);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.chats);
        toolbar.setTitleTextColor(Color.WHITE);
        DrawableResizeClass dr=new DrawableResizeClass();
        Drawable drawable=dr.resizeImage(getApplicationContext(),R.drawable.back,240,240);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatActivity.this,UserActivity.class));
                finish();
            }
        });

        prefManager=new PrefManager(getApplicationContext());

        recyclerView=(RecyclerView)findViewById(R.id.card_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        getChartCards();


    }

    private void getChartCards() {

        chartCardModelList=new ArrayList<>();

        //Filling data here
        chartCardModelList.add(new ChartCardModel("GLOBAL ELECTRONICS","Fine sir.Will deliver sample on your registerd...",1,"2 min ago",image[0]));
        chartCardModelList.add(new ChartCardModel("PATEL ELECTRONICS","Yes sir!We have product available in stock sir...",3,"5 min ago",image[0]));
        chartCardModelList.add(new ChartCardModel("BOMBAY ELECTRONICS","Thank you sir !",0,"10 min ago",image[1]));
        chartCardModelList.add(new ChartCardModel("OPERA SOLUTIONS","No problem sir.Will update you soon.",0,"30 min ago",image[0]));
        chartCardModelList.add(new ChartCardModel("HARI OM ELECTRONICS","Sorry sir.But you can check out in other sha...",0,"2 days ago",image[0]));
        chartCardModelList.add(new ChartCardModel("KRISHNA LIGHT JUNCTION","Your requirement has been received sir.:)",0,"3 days ago",image[0]));


        adapter=new ChatCardAdapter(getApplicationContext(), chartCardModelList, new ChatCardAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick() {

                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();

            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat,menu);
        return true;
    }
}
