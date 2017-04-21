package com.example.sagar.quickdeal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    LeadCardAdapter adapter;
    RecyclerView recyclerView;
    List<LeadCardModel> leadCardModelList;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_recycle_grid);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.leads);
        toolbar.setTitleTextColor(Color.WHITE);

        //customizing toolbar navigation icon
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.back);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);

        prefManager=new PrefManager(getApplicationContext());

        recyclerView=(RecyclerView)findViewById(R.id.card_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //filling recycle view adapter
        getLeadCards();

    }

    public void getLeadCards(){

        leadCardModelList=new ArrayList<>();

        //Adding data to the cards
        leadCardModelList.add(new LeadCardModel("RAKESH PATEL","Need 1000pcs of Electric fans","Posted 30 min ago"));
        leadCardModelList.add(new LeadCardModel("JOSEPH SMITH","500 LED Television for New Showroom","Posted 45 min ago"));
        leadCardModelList.add(new LeadCardModel("SHAILEE SHAH","Need 1000pcs of Electric fans","Posted 1 hr ago"));
        leadCardModelList.add(new LeadCardModel("VAIBHAV GUPTA","Requirement of 500 LED TV","Posted 1 day ago"));
        leadCardModelList.add(new LeadCardModel("HARRY D'SOUZA","Need 100pcs of DEll Laptops","Posted 3 days ago"));

        adapter=new LeadCardAdapter(getApplicationContext(), leadCardModelList, new LeadCardAdapter.VenueAdapterClickCallbacks() {
            @Override
            public void onCardClick(String n, String r, String d) {

                //Storing data for future use in application
                prefManager.setCName(n);
                prefManager.setRequirements(r);
                prefManager.setDays(d);
                startActivity(new Intent(MainActivity.this,UserActivity.class));

            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
