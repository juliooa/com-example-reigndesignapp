package com.example.reigndesignapp;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.reigndesignapp.db.StoryDBAdapter;
import com.example.reigndesignapp.model.Story;


public class StoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        ActionBar ab= getSupportActionBar();
        if(ab!=null)
            ab.setDisplayHomeAsUpEnabled(true);


        int object_id = getIntent().getIntExtra("object_id", -1);
        Log.d("lalala", "object_id:" + object_id);

        StoryDBAdapter sdb = new StoryDBAdapter(getApplicationContext());
        sdb.open();
        Story story= sdb.getStoryByID(object_id);

        setTitle(story.title==null? story.story_title: story.title);

        Log.d("lalala", "story_url:" + story.story_url);
        Log.d("lalala", "url:" + story.url);
        WebView wv = (WebView) findViewById(R.id.webview);
        wv.loadUrl(story.url==null? story.story_url: story.url);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
