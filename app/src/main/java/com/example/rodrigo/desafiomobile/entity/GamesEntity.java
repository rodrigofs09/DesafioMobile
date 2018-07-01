package com.example.rodrigo.desafiomobile.entity;

import java.util.List;

public class GamesEntity {
    private Integer id;

    private String name;

    private String image;

    private String releaseDate;

    private String trailer;

    private List<String> platforms = null;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTrailer() {
        return trailer;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

}
