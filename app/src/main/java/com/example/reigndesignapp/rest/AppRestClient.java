package com.example.reigndesignapp.rest;

import retrofit.RestAdapter;

/**
 * Created by JulioAndres on 6/10/15.
 */
public class AppRestClient {

    private static final String BASE_URL = "http://hn.algolia.com/api/v1";
    public static final String API_QUERY="android";

    private AppServiceAPI apiService;

    public AppRestClient() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();

        apiService = restAdapter.create(AppServiceAPI.class);
    }

    public AppServiceAPI getApiService() {
        return apiService;
    }
}
