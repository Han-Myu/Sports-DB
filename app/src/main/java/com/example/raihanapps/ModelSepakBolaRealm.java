package com.example.raihanapps;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelSepakBolaRealm extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String team, imageUrl, description, formedYear, stadiumName, stadiumDesc, stadiumImage, stadiumLocation;
    private Boolean isFavorite;


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTeam() { return team; }

    public void setTeam(String team) { this.team = team; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() {return description; }

    public void setDescription(String description) { this.description = description; }

    public String getFormedYear() { return formedYear; }

    public void setFormedYear(String formedYear) { this.formedYear = formedYear; }

    public String getStadiumName() {return stadiumName; }

    public void setStadiumName(String stadiumName) { this.stadiumName = stadiumName; }

    public String getStadiumDesc() { return stadiumDesc; }

    public void setStadiumDesc(String stadiumDesc) { this.stadiumDesc = stadiumDesc; }

    public String getStadiumImage() { return stadiumImage; }

    public void setStadiumImage(String stadiumImage) { this.stadiumImage = stadiumImage; }

    public String getStadiumLocation() { return stadiumLocation; }

    public void setStadiumLocation(String stadiumLocation) { this.stadiumLocation = stadiumLocation; }

    public Boolean getIsFavorite() { return isFavorite; }

    public void setIsFavorite(Boolean isFavorite) { this.isFavorite = isFavorite; }

}