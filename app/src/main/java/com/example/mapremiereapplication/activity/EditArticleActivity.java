package com.example.mapremiereapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.mapremiereapplication.R;
import com.example.mapremiereapplication.bo.Article;
import com.example.mapremiereapplication.repository.ArticleBddRepository;
import com.example.mapremiereapplication.repository.IArticleRepository;
import com.example.mapremiereapplication.view_model.ArticleViewModel;

public class EditArticleActivity extends AppCompatActivity {


    private Article article = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

    }
    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        this.article = (Article) extras.get("article");
        EditText etDesc = findViewById(R.id.et_description);
        etDesc.setText(article.getDescription());
        EditText etNom = findViewById(R.id.et_nom);
        etNom.setText(article.getNom());
        EditText etUrl = findViewById(R.id.et_url);
        etUrl.setText(article.getUrl());
        EditText etPrix = findViewById(R.id.et_prix);
        etPrix.setText(article.getPrix().toString());
        CheckBox checkBok = findViewById(R.id.chkAchete);
        RatingBar rbNote = findViewById(R.id.rb_note);
        rbNote.setRating(article.getNote());
        if (article.getAchete()) {
            checkBok.setChecked(true);
        }
    }

    public void onClickSave(View view) {

        EditText etDesc = findViewById(R.id.et_description);
        article.setDescription(etDesc.getText().toString());
        EditText etNom = findViewById(R.id.et_nom);
        article.setNom(etNom.getText().toString());
        EditText etUrl= findViewById(R.id.et_url);
        article.setUrl(etUrl.getText().toString());
        EditText etPrix= findViewById(R.id.et_prix);
        article.setPrix(Float.valueOf(etPrix.getText().toString()));
        CheckBox checkBok = findViewById(R.id.chkAchete);
        if (checkBok.isChecked()){
            article.setAchete(true);
        }
        RatingBar barNote = findViewById(R.id.rb_note);
        article.setNote(Math.round(barNote.getRating()));

        //
        ArticleViewModel avm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        avm.update(article);
        Intent intent = new Intent(this, ListeArticleActivity.class);
        startActivity(intent);
    }
}