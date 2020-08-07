package com.example.mapremiereapplication.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mapremiereapplication.bo.Article;

import java.util.List;

/**
 * Cette classe permet de donner les consignes necessaires à Room
 * pour qu'il puisse créer la dao pour la table utilisateur.
 */
@Dao
public interface ArticleDao
{
    @Insert
    void insert(Article article);

    @Insert
    void insert(Article ... articles);

    @Query("SELECT * FROM Article")
    LiveData<List<Article>> get();

    @Query("SELECT * FROM Article WHERE id = :id")
    Article get(int id);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("Delete from article")
    void delete();
}
