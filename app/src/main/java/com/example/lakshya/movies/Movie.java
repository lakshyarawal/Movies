package com.example.lakshya.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable{
    private int id;
    private String title;

    public Movie() {
    }

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    private String[] genre;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;


    public Movie(String title, double voteAverage, String[] genre, String releaseDate,String posterPath) {
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
