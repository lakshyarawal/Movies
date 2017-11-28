package com.example.lakshya.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Tv implements Serializable {
    private int id;
    private String name;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    public int[] getGenre() {
        return genre;
    }

    public void setGenre(int[] genre) {
        this.genre = genre;
    }
    @SerializedName("genre_ids")
    private int[] genre;
    private String overview;
    @SerializedName("first_air_date")
    private String releaseDate;


    public Tv(String name, double voteAverage, int[] genre, String releaseDate,String posterPath) {
        this.name = name;
        this.voteAverage = voteAverage;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}
