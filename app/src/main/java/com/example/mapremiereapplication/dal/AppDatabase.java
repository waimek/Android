package com.example.mapremiereapplication.dal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mapremiereapplication.bo.Article;

@Database(entities = {Article.class},exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;

    /**
     * Permet de fournir une instance de la dao utilisateur aux couches supérieures.
     * @return
     */
    public abstract ArticleDao getArticleDao();

    public static AppDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"listeEnvie.db").build();
        }
        return INSTANCE;
    }
}
