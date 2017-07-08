package com.zhangmiao.simplenewsapplication4_5;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Author: zhangmiao
 * Date: 2017/7/8
 */
public class NewsAdapter extends ArrayAdapter<News>{

    private int resourceId;

    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        } else {
            view = convertView;
        }
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
        newsTitleText.setText(news.getTitle());
        return view;
    }
}
