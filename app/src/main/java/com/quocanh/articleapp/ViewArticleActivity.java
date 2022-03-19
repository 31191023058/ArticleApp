package com.quocanh.articleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

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

        String URL = "https://articleapp-35be2-default-rtdb.asia-southeast1.firebasedatabase.app/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(URL);
        DatabaseReference myRef = database.getReference("Articles");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Article> data = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    data.add(dataSnapshot.getValue(Article.class));
                }
                ArticleData.articleList.setArticles(data);
                Picasso.get().load(Objects.requireNonNull(ArticleData.getPhotoFromId(id)).getArticle_image()).resize(700, 700).centerCrop().into(imv_photoview);
                tv_titleview.setText(Objects.requireNonNull(ArticleData.getPhotoFromId(id)).getArticle_title());
                tv_descview.setText(Objects.requireNonNull(ArticleData.getPhotoFromId(id)).getArticle_description());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}