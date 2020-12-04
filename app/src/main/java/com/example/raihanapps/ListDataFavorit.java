package com.example.raihanapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavorit extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    TextView tvnodata;
    RecyclerView recyclerView;
    DataAdapterFavorit adapter;
    List<ModelSepakBolaRealm> DataArrayList; //kit add kan ke adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        getSupportActionBar().hide();
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllMovie();
        if (DataArrayList.size() == 0){
            tvnodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            tvnodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new DataAdapterFavorit(DataArrayList, new DataAdapterFavorit.Callback() {
                @Override
                public void onClick(int position) {
                    Intent move = new Intent(getApplicationContext(), DetailFavorit.class);
                    move.putExtra("strTeam",DataArrayList.get(position).getTeam());
                    move.putExtra("strTeamBadge",DataArrayList.get(position).getImageUrl());
                    move.putExtra("idTeam",DataArrayList.get(position).getId());
                    move.putExtra("strDescriptionEN",DataArrayList.get(position).getDescription());
                    move.putExtra("intFormedYear",DataArrayList.get(position).getFormedYear());
                    move.putExtra("strStadium",DataArrayList.get(position).getStadiumName());
                    move.putExtra("strStadiumThumb",DataArrayList.get(position).getImageUrl());
                    move.putExtra("strStadiumDescription",DataArrayList.get(position).getStadiumDesc());
                    move.putExtra("strStadiumLocation",DataArrayList.get(position).getStadiumLocation());
                    // di putextra yang lain
                    startActivity(move);
                }

                @Override
                public void test() {

                }
            });
            for (int i = 0; i < DataArrayList.size(); i++) {
                Log.d("nama", ""+DataArrayList.get(i).getTeam());
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavorit.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }


    }
}