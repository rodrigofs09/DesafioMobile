package com.example.rodrigo.desafiomobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GamesEntity implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("trailer")
    @Expose
    private String trailer;

    @SerializedName("platforms")
    @Expose
    private List<String> platforms = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public GamesEntity(Integer id, String name, String image, String releaseDate, String trailer, List<String> platforms) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.releaseDate = releaseDate;
        this.trailer = trailer;
        this.platforms = platforms;
    }
}
