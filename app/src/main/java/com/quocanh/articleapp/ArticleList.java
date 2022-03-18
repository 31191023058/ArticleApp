package com.quocanh.articleapp;

import java.util.ArrayList;

public class ArticleList {
    private ArrayList<Article> articles;

    public ArticleList(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
