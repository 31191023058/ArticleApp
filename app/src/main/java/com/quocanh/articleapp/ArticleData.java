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
    public static ArrayList<Article> data = new ArrayList<>();
    static final private String URL = "https://articleapp-35be2-default-rtdb.asia-southeast1.firebasedatabase.app/";

    public ArticleData(Context context, GridView gridView) {
        ArticleData.context = context;
        ArticleData.gridView = gridView;
        getDataFromDB();
    }

    public static Article getPhotoFromId(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getArticle_id() == id) {
                return data.get(i);
            }
        }
        return null;
    }

    public static void getDataFromDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(URL);
        DatabaseReference myRef = database.getReference("Articles");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
        ArticleAdapter adapter = new ArticleAdapter(data, context);
        gridView.setAdapter(adapter);
    }
}
