package com.example.reigndesignapp.rest;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by JulioAndres on 6/7/15.
 */
public interface AppServiceAPI {

    @GET("/search_by_date")
    void getHN(@Query("query") String query, Callback<ResponseQuery> callback);
}

