package com.example.reigndesignapp.rest;

import com.example.reigndesignapp.model.Story;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 8/3/15.
 */
public class ResponseQuery {

    public ArrayList<Story> hits;
    public int nbHits;
    int page;
    int nbPages;
    int hitsPerPage;
    public int processingTimeMS;
    String query;
    String params;

}
