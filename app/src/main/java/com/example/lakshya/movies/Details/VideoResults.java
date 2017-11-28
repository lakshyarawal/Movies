package com.example.lakshya.movies.Details;

import java.io.Serializable;

/**
 * Created by LAKSHYA on 7/26/2017.
 */

public class VideoResults implements Serializable{
    String key;
    String name;
    String id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
