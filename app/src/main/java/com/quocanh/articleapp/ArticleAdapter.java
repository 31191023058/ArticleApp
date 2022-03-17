package com.quocanh.articleapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {
    private final ArrayList<Article> article_list;
    private final Context context;

    public ArticleAdapter(ArrayList<Article> article_list, Context context) {
        this.article_list = article_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return article_list.size();
    }

    @Override
    public Object getItem(int i) {
        return article_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return article_list.get(i).getArticle_id();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            dataitem = new MyView();
            view = inflater.inflate(R.layout.article_disp_tpl, null);
            dataitem.iv_photo = view.findViewById(R.id.imv_photo);
            dataitem.tv_caption = view.findViewById(R.id.tv_photo);
            view.setTag(dataitem);
        } else {
            dataitem = (MyView) view.getTag();
        }

        Picasso.get().load(article_list.get(i).getArticle_image()).resize(300, 400).centerCrop().into(dataitem.iv_photo);
        dataitem.tv_caption.setText(article_list.get(i).getArticle_title());
        return view;
    }

    public static class MyView {
        ImageView iv_photo;
        TextView tv_caption;
    }
}
