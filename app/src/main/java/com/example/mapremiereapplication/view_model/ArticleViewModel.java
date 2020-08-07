package com.example.mapremiereapplication.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mapremiereapplication.bo.Article;
import com.example.mapremiereapplication.repository.ArticleBddRepository;
import com.example.mapremiereapplication.repository.IArticleRepository;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel
{
    private IArticleRepository repo;
    /**
     * Permet de n'avoir qu'un seul observateur pour toute l'application
     */
    private LiveData<List<Article>> observateur = null;

    public ArticleViewModel(@NonNull Application application)
    {
        super(application);
        repo = new ArticleBddRepository(application);
        observateur = repo.get();
    }

    public LiveData<List<Article>> get()
    {
        return observateur;
    }

    public void insert(Article article)
    {
        repo.insert(article);
    }
    public void update(Article article)
    {
        repo.update(article);
    }
    public void delete(Article article)
    {
        repo.delete(article);
    }
    public void delete()
    {
        repo.delete();
    }


}
