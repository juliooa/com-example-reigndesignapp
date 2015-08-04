package com.example.reigndesignapp.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reigndesignapp.R;
import com.example.reigndesignapp.model.Story;
import com.example.reigndesignapp.utils.Utils;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 8/3/15.
 */
public class StoriesListViewAdapter extends BaseAdapter {

    private final Activity act;
    private ArrayList<Story> stories;

    public StoriesListViewAdapter(ArrayList<Story> stories, Activity act) {
        this.stories=stories;
        this.act=act;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Story getItem(int i) {
        return stories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return stories.get(i).story_id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView =act.getLayoutInflater().inflate(R.layout.row_story, viewGroup, false);
            new ViewHolder(convertView);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        Story item = getItem(position);
        holder.tv_title.setText(item.story_title == null ? item.title : item.story_title);
        holder.tv_author.setText(item.author + " - ");
        holder.tv_date.setText(Utils.getTimePassed(item.created_at));
        //holder.tv_date.setText(item.created_at);
        return convertView;
    }

    class ViewHolder {
        TextView tv_title;
        TextView tv_author;
        TextView tv_date;

        public ViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.textViewRowStoryTitle);
            tv_author= (TextView) view.findViewById(R.id.textViewRowStoryAuthor);
            tv_date = (TextView) view.findViewById(R.id.textViewRowStoryDate);
            view.setTag(this);
        }
    }

}
