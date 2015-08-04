package com.example.reigndesignapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.reigndesignapp.model.Story;

import java.util.ArrayList;
import java.util.Collections;

public class StoryDBAdapter {

	public static final String COLUMN_STORY_ID = "story_id";
	public static final String COLUMN_PARENT_ID = "parent_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_URL = "url";
    public static final String COLUMN_AUTHOR= "author";
    public static final String COLUMN_STORY_TITLE= "story_title";
    public static final String COLUMN_STORY_URL= "story_url";
    public static final String COLUMN_CREATED_AT= "created_at";
    public static final String COLUMN_DELETED = "deleted";
    public static final String COLUMN_OBJECT_ID = "object_id";

    private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

    public StoryDBAdapter(Context ctx) {
		dbHelper = DatabaseHelper.getInstance(ctx);
	}

	public synchronized void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	


	public boolean insertStory(Story s) {

		ContentValues v = new ContentValues();
        v.put(COLUMN_OBJECT_ID, s.object_id);
		v.put(COLUMN_STORY_ID, s.story_id);
        v.put(COLUMN_PARENT_ID, s.parent_id);
        v.put(COLUMN_TITLE, s.title);
		v.put(COLUMN_URL, s.url);
		v.put(COLUMN_AUTHOR, s.author);
		v.put(COLUMN_STORY_TITLE, s.story_title);
        v.put(COLUMN_STORY_URL, s.story_url);
		v.put(COLUMN_CREATED_AT, s.created_at);

        if(!existStory(s.object_id)){
        Log.d("lkala", "noexiste story");
            database.insert(DatabaseHelper.TABLE_STORIES, null, v);
            return true;
        }

        return false;
	}

	private boolean existStory(int object_id) {
		
		 Cursor cursor = database.rawQuery("select " + COLUMN_OBJECT_ID + " from " + DatabaseHelper.TABLE_STORIES + " where " + COLUMN_OBJECT_ID + "=?",
                 new String[]{String.valueOf(object_id)});

        if(cursor.moveToFirst()) {
            cursor.close();
            return true;
        }else{
            return false;
        }
	}

	public Story getStoryByID(int object_id) {

		Cursor c = this.database.query(DatabaseHelper.TABLE_STORIES,
                null, COLUMN_OBJECT_ID + "=?", new String[]{String.valueOf(object_id)},
                null, null, null);
		Story s = null;

		if (c.moveToFirst()) {
            s=new Story();
            s.object_id=object_id;
            s.story_id=c.getInt(c.getColumnIndex(COLUMN_STORY_ID));
            s.title=c.getString(c.getColumnIndex(COLUMN_TITLE));
            s.story_title=c.getString(c.getColumnIndex(COLUMN_STORY_TITLE));
			s.url = c.getString(c.getColumnIndex(COLUMN_URL));
            s.story_url=c.getString(c.getColumnIndex(COLUMN_STORY_URL));
        }

		c.close();

		return s;
	}

    public void deleteStory(Story story) {

        ContentValues cv = new ContentValues(1);
        cv.put(COLUMN_DELETED, "t");

        this.database.update(DatabaseHelper.TABLE_STORIES, cv, COLUMN_OBJECT_ID + "=?", new String[]{String.valueOf(story.object_id)});
    }

    public ArrayList<Story> getStories() {

        String where = COLUMN_DELETED+ "=?";
        String[] whereArgs = new String[] { "f" };

        Cursor c = database.query(DatabaseHelper.TABLE_STORIES, null,
                where, whereArgs, null, null, null);
        ArrayList<Story> stories= new ArrayList<>(c.getCount());

        if (c.moveToFirst()) {
            do {
                Story s=new Story();
                s.object_id=c.getInt(c.getColumnIndex(COLUMN_OBJECT_ID));
                s.story_id=c.getInt(c.getColumnIndex(COLUMN_STORY_ID));
                s.title=c.getString(c.getColumnIndex(COLUMN_TITLE));
                s.story_title=c.getString(c.getColumnIndex(COLUMN_STORY_TITLE));
                s.url = c.getString(c.getColumnIndex(COLUMN_URL));
                s.story_url=c.getString(c.getColumnIndex(COLUMN_STORY_URL));
                s.created_at = c.getString(c.getColumnIndex(COLUMN_CREATED_AT));
                s.author = c.getString(c.getColumnIndex(COLUMN_AUTHOR));
                stories.add(s);
            }while (c.moveToNext());
        }
        Collections.reverse(stories);
        c.close();
        return stories;
    }
}
