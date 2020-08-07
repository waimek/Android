package com.example.mapremiereapplication.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mapremiereapplication.bo.Article;
import com.example.mapremiereapplication.dal.AppDatabase;
import com.example.mapremiereapplication.dal.ArticleDao;

import java.util.List;

/**
 * Le repository permet de gerer l'asynchronisme
 */
public class ArticleBddRepository implements IArticleRepository {

    private ArticleDao articleDao = null;


    public ArticleBddRepository(Context context)
    {
        //Instance de ma bdd.
        AppDatabase maBaseDeDonnees = AppDatabase.getInstance(context);
        //Instance de la dao Article.
        articleDao = maBaseDeDonnees.getArticleDao();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void insert(Article article) {
        //Je créé un nouveau thread(ouvrier)
        new AsyncTask<Article,Void,Void>()
        {
            //Je lui dis quoi faire
            @Override
            protected Void doInBackground(Article... articles) {
                //Inserer un Article dans la bdd
                articleDao.insert(articles[0]);
                return null;
            }//Je lui dis de le faire
        }.execute(article);
    }

    List<Article> resultat = null;
    @Override
    public LiveData<List<Article>> get() {
        return articleDao.get();
    }

    @Override
    public Article get(int id) {
        return articleDao.get(id);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void update(Article Article) {
        //Je créé un nouveau thread(ouvrier)
        new AsyncTask<Article,Void,Void>()
        {
            //Je lui dis quoi faire
            @Override
            protected Void doInBackground(Article... Articles) {
                //Inserer un Article dans la bdd
                articleDao.update(Articles[0]);
                return null;
            }//Je lui dis de le faire
        }.execute(Article);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void delete(Article Article) {
        new AsyncTask<Article,Void,Void>()
        {
            @Override
            protected Void doInBackground(Article... Articles) {
                //Delete dans la base de données.
                articleDao.delete(Articles[0]);
                return null;
            }
        }.execute(Article);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void delete() {
        //Je créé un nouveau thread(ouvrier)
        new AsyncTask<Void,Void,Void>()
        {
            //Je lui dis quoi faire
            @Override
            protected Void doInBackground(Void... voids) {
                //Inserer un Article dans la bdd
                articleDao.delete();
                return null;
            }//Je lui dis de le faire
        }.execute();
    }
}
