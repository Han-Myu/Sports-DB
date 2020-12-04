package com.example.raihanapps;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final ModelSepakBolaRealm bolaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(ModelSepakBolaRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    bolaModel.setId(nextId);
                    ModelSepakBolaRealm model = realm.copyToRealm(bolaModel);
                    final RealmResults<ModelSepakBolaRealm> item = realm.where(ModelSepakBolaRealm.class).findAll();
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<ModelSepakBolaRealm> getAllMovie(){
        RealmResults<ModelSepakBolaRealm> results = realm.where(ModelSepakBolaRealm.class).findAll();
        return results;
    }

    public void delete(Integer id){
        final RealmResults<ModelSepakBolaRealm> model = realm.where(ModelSepakBolaRealm.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}