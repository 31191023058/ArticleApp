package com.quocanh.articleapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArticleData {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static GridView gridView;

    public static ArticleList articleList;
    public static ArrayList<Article> data;

    public ArticleData(Context context, GridView gridView) {
        ArticleData.context = context;
        ArticleData.gridView = gridView;
        getDataFromDB();
    }

    public static Article getPhotoFromId(int id) {
        for (int i = 0; i < articleList.getArticles().size(); i++) {
            if (articleList.getArticles().get(i).getArticle_id() == id) {
                return articleList.getArticles().get(i);
            }
        }
        articleList.getArticles().clear();
        return null;
    }

    public static void getDataFromDB() {
        String URL = "https://articleapp-35be2-default-rtdb.asia-southeast1.firebasedatabase.app/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(URL);
        DatabaseReference myRef = database.getReference("Articles");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = new ArrayList<>();
                for (DataSnapshot dataSnap : snapshot.getChildren()) {
                    Article article = dataSnap.getValue(Article.class);
                    data.add(article);
                }
                displayData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(MainActivity.class.getSimpleName(), "Error: " + error.toException());
            }
        });
    }
    public static void displayData() {
        articleList = new ArticleList(data);
        ArticleAdapter adapter = new ArticleAdapter(articleList.getArticles(), context);
        gridView.setAdapter(adapter);
    }
}
