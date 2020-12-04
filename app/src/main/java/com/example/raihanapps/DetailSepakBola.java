package com.example.raihanapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailSepakBola extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    ModelSepakBolaRealm bolaRealm;


    Bundle extras;
    private Integer id;
    private String team, imageUrl, description, formedYear, stadiumName, stadiumDesc, stadiumImage, stadiumLocation;
    private Boolean isFavorite;

    TextView tv_Team;
    TextView tv_FormedYear;
    TextView tv_TeamDesc;
    TextView tv_Stadion;
    TextView tv_StadionLocation;
    TextView tv_StadionDesc;
    ImageView iv_TeamLogo;
    ImageView iv_StadionImage;
    Button btn_bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sepak_bola);
        extras = getIntent().getExtras();
        tv_Team = (TextView) findViewById(R.id.tv_Team);
        tv_TeamDesc = (TextView) findViewById(R.id.tv_TeamDesc);
        tv_FormedYear = (TextView) findViewById(R.id.tv_FormedYear);
        tv_Stadion = (TextView) findViewById(R.id.tv_Stadion);
        tv_StadionLocation = (TextView) findViewById(R.id.tv_StadionLocation);
        tv_StadionDesc = (TextView) findViewById(R.id.tv_StadionDesc);
        iv_TeamLogo = (ImageView) findViewById(R.id.iv_TeamLogo);
        iv_StadionImage = (ImageView) findViewById(R.id.iv_StadionImage);
        btn_bookmark = (Button) findViewById(R.id.btn_bookmark);


        if (extras != null) {
            id = extras.getInt("id");
            team = extras.getString("strTeam");
            imageUrl = extras.getString("strTeamBadge");
            description = extras.getString("strDescriptionEN");
            formedYear = extras.getString("intFormedYear");
            stadiumName = extras.getString("strStadium");
            stadiumImage = extras.getString("strStadiumThumb");
            stadiumDesc = extras.getString("strStadiumDescription");
            stadiumLocation = extras.getString("strStadiumLocation");
            Glide.with(DetailSepakBola.this)
                    .load(imageUrl)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_TeamLogo);
            tv_Team.setText(team);
            tv_FormedYear.setText(formedYear);
            tv_TeamDesc.setText(description);
            Glide.with(DetailSepakBola.this)
                    .load(imageUrl)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_StadionImage);
            tv_StadionLocation.setText(stadiumLocation);
            tv_StadionDesc.setText(stadiumDesc);

            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelSepakBolaRealm footballModel = new ModelSepakBolaRealm();
                footballModel.setTeam(team);
                footballModel.setDescription(description);
                footballModel.setImageUrl(imageUrl);
                footballModel.setFormedYear(formedYear);
                footballModel.setStadiumName(stadiumName);
                footballModel.setStadiumDesc(stadiumDesc);
                footballModel.setStadiumImage(stadiumImage);
                footballModel.setStadiumLocation(stadiumLocation);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(footballModel);
            }
        });
    }
}


