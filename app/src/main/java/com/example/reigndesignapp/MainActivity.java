package com.example.reigndesignapp;


import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.widget.PullRefreshLayout;
import com.example.reigndesignapp.adapters.StoriesListViewAdapter;
import com.example.reigndesignapp.db.StoryDBAdapter;
import com.example.reigndesignapp.model.Story;
import com.example.reigndesignapp.rest.AppRestClient;
import com.example.reigndesignapp.rest.ResponseQuery;
import com.example.reigndesignapp.utils.SwipeItems;

import java.util.ArrayList;
import java.util.Collections;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    ArrayList<Story> stories = new ArrayList<>();
    private StoriesListViewAdapter storiesListAdapter;
    private PullRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoryDBAdapter sdb = new StoryDBAdapter(getApplicationContext());
        sdb.open();
        stories = sdb.getStories();

        storiesListAdapter = new StoriesListViewAdapter(stories,this);

        SwipeMenuListView storiesListView = (SwipeMenuListView) findViewById(R.id.storiesListView);
        storiesListView.setMenuCreator(new SwipeItems(this));
        storiesListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        storiesListView.setAdapter(storiesListAdapter);
        storiesListView.setOnMenuItemClickListener(new SwipeItemListener());
        storiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), StoryActivity.class);
                i.putExtra("object_id", stories.get(position).object_id);

                startActivity(i);
            }
        });


        getHNStories();

        layout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getHNStories();
            }
        });
    }

    private void getHNStories(){

        AppRestClient apiClient = new AppRestClient();
        apiClient.getApiService().getHN(AppRestClient.API_QUERY, new Callback<ResponseQuery>() {
            @Override
            public void success(ResponseQuery responseQuery, Response response) {

                Collections.sort(responseQuery.hits);

                StoryDBAdapter sdb = new StoryDBAdapter(getApplicationContext());
                sdb.open();
                for(Story story:responseQuery.hits){

                    if(sdb.insertStory(story)) {
                        stories.add(0,story);
                    }
                }
                storiesListAdapter.notifyDataSetChanged();
                layout.setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {
                layout.setRefreshing(false);
                Toast.makeText(getApplicationContext(),"Error retrieving data",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class SwipeItemListener implements  SwipeMenuListView.OnMenuItemClickListener{


        @Override
        public boolean onMenuItemClick(int position, SwipeMenu swipeMenu, int index) {

            Story story = stories.get(position);

            switch (index) {
                case SwipeItems.DELETE_ITEM:
                    stories.remove(position);
                    storiesListAdapter.notifyDataSetChanged();

                    StoryDBAdapter sdb = new StoryDBAdapter(getApplicationContext());
                    sdb.open();
                    sdb.deleteStory(story);
                    break;
            }
            return false;
        }
    }
}
