package com.example.lakshya.movies.Details;

import java.io.Serializable;

/**
 * Created by LAKSHYA on 7/26/2017.
 */

public class ReviewResult implements Serializable{
    String author;
    String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
