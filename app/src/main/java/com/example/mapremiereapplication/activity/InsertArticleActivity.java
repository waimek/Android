package com.example.mapremiereapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mapremiereapplication.R;
import com.example.mapremiereapplication.bo.Article;
import com.example.mapremiereapplication.view_model.ArticleViewModel;
import com.facebook.stetho.Stetho;

public class InsertArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_article);
    }


    public void onClickSave(View view) {
        Log.e("wololo", "onclickSave entrée");
        String nom;
        int note;
        String url;
        boolean achete = false;
        float prix;
        String description;

        EditText etDesc = findViewById(R.id.et_description);
        description = etDesc.getText().toString();

        EditText etNom = findViewById(R.id.et_nom);
        nom= etNom.getText().toString();

        EditText etUrl= findViewById(R.id.et_url);
        url= etUrl.getText().toString();

        EditText etPrix= findViewById(R.id.et_prix);
        prix= Float.parseFloat(etPrix.getText().toString());

        CheckBox checkBok = findViewById(R.id.chkAchete);
        if (checkBok.isChecked()){
            achete = true;
        }

        RatingBar barNote = findViewById(R.id.rb_note);
        note = Math.round(barNote.getRating());

        //
        Article article = new Article(nom,note,url,achete, prix, description);
        ArticleViewModel avm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        avm.insert(article);
        Intent intent = new Intent(this, ListeArticleActivity.class);
        Log.e("wololo", "avant  startActivity");
        startActivity(intent);
    }
}