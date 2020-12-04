package com.example.raihanapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {

    TextView tvnodata;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<ModelSepakBolaRealm> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(ListData.this);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();

    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang mengolah data...");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        ModelSepakBolaRealm modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new ModelSepakBolaRealm();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setId(jsonObject.getInt("idTeam"));
                                modelku.setTeam(jsonObject.getString("strTeam"));
                                modelku.setDescription(jsonObject.getString("strDescriptionEN"));
                                modelku.setImageUrl(jsonObject.getString("strTeamBadge"));
                                modelku.setFormedYear(jsonObject.getString("intFormedYear"));
                                modelku.setStadiumName(jsonObject.getString("strStadium"));
                                modelku.setStadiumImage(jsonObject.getString("strStadiumThumb"));
                                modelku.setStadiumDesc(jsonObject.getString("strStadiumDescription"));
                                modelku.setStadiumLocation(jsonObject.getString("strStadiumLocation"));
                                modelku.setIsFavorite(false);
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    ModelSepakBolaRealm bola = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailSepakBola.class);
                                    intent.putExtra("idTeam",bola.getId());
                                    intent.putExtra("strTeam",bola.getTeam());
                                    intent.putExtra("strTeamBadge",bola.getImageUrl());
                                    intent.putExtra("strDescriptionEN",bola.getDescription());
                                    intent.putExtra("intFormedYear",bola.getFormedYear());
                                    intent.putExtra("strStadium",bola.getStadiumName());
                                    intent.putExtra("strStadiumLocation",bola.getStadiumLocation());
                                    intent.putExtra("is Favourite",bola.getIsFavorite());
                                    startActivity(intent);
                                    Toast.makeText(ListData.this, ""+position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}