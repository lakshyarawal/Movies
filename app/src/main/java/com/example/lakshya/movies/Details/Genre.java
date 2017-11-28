package com.example.lakshya.movies.Details;

import java.io.Serializable;

/**
 * Created by LAKSHYA on 7/26/2017.
 */

public class Genre implements Serializable{
    int id;
    String name;

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
}
