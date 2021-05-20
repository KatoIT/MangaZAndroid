package com.example.mangaz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangaz.Model.Chapter;
import com.example.mangaz.Model.ChapterImage;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends BaseAdapter {
    private Context context;
    private List<ChapterImage> listpage;

    public PageAdapter(Context context, List<ChapterImage> listpage) {
        this.context = context;
        this.listpage = listpage;
    }

    public void setData(List<ChapterImage> listpage) {
        this.listpage = listpage;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listpage.size();
    }

    @Override
    public Object getItem(int i) {
        return listpage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mview = view;
        if (mview == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            mview = inflater.inflate(R.layout.page, null);
        }
        ChapterImage p = (ChapterImage) getItem(i);
        if (p != null) {
            // Anh xa + Gan gia tri
            ImageView image = (ImageView) mview.findViewById(R.id.imgPage);
            image.setImageBitmap(p.getImage());
        }
        return mview;
    }
}
