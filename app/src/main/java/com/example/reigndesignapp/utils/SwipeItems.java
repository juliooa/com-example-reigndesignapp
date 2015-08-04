package com.example.reigndesignapp.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

/**
 * Created by JulioAndres on 8/3/15.
 */
public class SwipeItems implements SwipeMenuCreator {

    public static final int DELETE_ITEM = 0;

    private Activity act;

    public SwipeItems(Activity act) {

        this.act = act;
    }

    @Override
    public void create(SwipeMenu swipeMenu) {

        SwipeMenuItem deleteItem = new SwipeMenuItem(this.act);

        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
        deleteItem.setWidth(Utils.dpToPx(120, this.act));
        deleteItem.setTitle("Delete");
        deleteItem.setTitleSize(16);
        deleteItem.setTitleColor(Color.WHITE);
        swipeMenu.addMenuItem(deleteItem);

    }
}