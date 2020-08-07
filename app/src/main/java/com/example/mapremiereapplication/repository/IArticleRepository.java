package com.example.mapremiereapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.mapremiereapplication.bo.Article;

import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO.
 */
public interface IArticleRepository
{
    void insert(Article article);
    LiveData<List<Article>> get();
    Article get(int id);
    void delete(Article article);
    void update(Article article);
    void delete();
}
