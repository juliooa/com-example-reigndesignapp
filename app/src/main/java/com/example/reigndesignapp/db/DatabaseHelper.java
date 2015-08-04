package com.example.reigndesignapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static DatabaseHelper sInstance;
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "stories.db";
	
	public static final String TABLE_STORIES = "stories";

	private static final String CREATE_TABLE_STORIES= "create table " + TABLE_STORIES+ " ("
            + StoryDBAdapter.COLUMN_OBJECT_ID+ " integer primary key,"
			+ StoryDBAdapter.COLUMN_STORY_ID+ " integer,"
			+ StoryDBAdapter.COLUMN_PARENT_ID + " integer,"
			+ StoryDBAdapter.COLUMN_AUTHOR + " text,"
			+ StoryDBAdapter.COLUMN_TITLE + " text,"
			+ StoryDBAdapter.COLUMN_URL+ " text,"
			+ StoryDBAdapter.COLUMN_STORY_TITLE + " text,"
			+ StoryDBAdapter.COLUMN_STORY_URL + " text,"
			+ StoryDBAdapter.COLUMN_CREATED_AT + " text,"
            + StoryDBAdapter.COLUMN_DELETED + " text default 'f'"
			+ ");";
			

	public static DatabaseHelper getInstance(Context ctx){
	    if (sInstance == null) {
	      sInstance = new DatabaseHelper(ctx.getApplicationContext());
	    }
	    return sInstance;	
	}
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_STORIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
