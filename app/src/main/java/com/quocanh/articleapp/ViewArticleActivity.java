package com.quocanh.articleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewArticleActivity extends AppCompatActivity {
    ImageView imv_photoview;
    TextView tv_titleview, tv_descview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        imv_photoview = findViewById(R.id.imv_photoview);
        tv_titleview = findViewById(R.id.tv_titleview);
        tv_descview = findViewById(R.id.tv_descview);

        Picasso.get().load(ArticleData.getPhotoFromId(id).getArticle_image()).resize(700, 700).centerCrop().into(imv_photoview);
        tv_titleview.setText(ArticleData.getPhotoFromId(id).getArticle_title());
        tv_descview.setText(ArticleData.getPhotoFromId(id).getArticle_description());
    }
}