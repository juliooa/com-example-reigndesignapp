package com.example.reigndesignapp.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by JulioAndres on 8/3/15.
 */
public class Story implements Comparable<Story>{

    @SerializedName("objectID")
    public int object_id;
    public int story_id;
    public int parent_id;
    public String title;
    public String url;
    public String author;
    public String story_title;
    public String story_url;
    public String created_at;

    public boolean deleted;

    private Date getDate() throws ParseException {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH).parse(this.created_at);
    }

    @Override
    public int compareTo(Story otherStory) {

        try {
            return this.getDate().compareTo(otherStory.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
