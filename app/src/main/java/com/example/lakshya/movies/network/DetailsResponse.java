package com.example.lakshya.movies.network;

import com.example.lakshya.movies.Details.Cast;
import com.example.lakshya.movies.Details.Crew;
import com.example.lakshya.movies.Details.Genre;
import com.example.lakshya.movies.Details.ReviewResult;
import com.example.lakshya.movies.Details.VideoResults;

import java.util.ArrayList;


public class DetailsResponse {
    public video videos;
    public credits credits;
    public reviews reviews;
    ArrayList<Genre> genres = new ArrayList<>();

    public ArrayList<Genre> getGenres() {
        return genres;
    }
String poster_path;

    public String getPoster_path() {
        return poster_path;
    }
    String overview;

    public String getOverview() {
        return overview;
    }

    int id;
    String imdb_id;
    long revenue;
    long budget;
    int runtime;

    public static class video {
        ArrayList<VideoResults> results = new ArrayList<>();

        public ArrayList<VideoResults> getVideoResults() {
            return results;
        }
    }

    public static class credits {
        ArrayList<Cast> cast = new ArrayList<>();
        ArrayList<Crew> crew = new ArrayList<>();

        public ArrayList<Cast> getCast() {
            return cast;
        }

        public ArrayList<Crew> getCrew() {
            return crew;
        }
    }

    public static class reviews {
        ArrayList<ReviewResult> results = new ArrayList<>();

        public ArrayList<ReviewResult> getReviewResults() {
            return results;
        }
    }

}
