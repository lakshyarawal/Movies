package com.example.lakshya.movies.Details;

import java.io.Serializable;

/**
 * Created by LAKSHYA on 7/26/2017.
 */

public class Cast implements Serializable{
    String name;
    String profile_path;
    String character;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
