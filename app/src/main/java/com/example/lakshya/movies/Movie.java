package com.example.lakshya.movies;

import java.io.Serializable;

/**
 * Created by LAKSHYA on 7/18/2017.
 */

public class Movie implements Serializable{
    private int id;
    private String title;
    private double voteAverage;
    private String backdropPath;
    private int[] genreId;
    private String overview;
    private String releaseDate;
    public Movie(String title, double voteAverage, int[] genreId, String releaseDate) {
        this.title = title;
        this.voteAverage = voteAverage;
        this.genreId = genreId;
        this.releaseDate = releaseDate;
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

    public int[] getGenreId() {
        return genreId;
    }

    public void setGenreId(int[] genreId) {
        this.genreId = genreId;
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
