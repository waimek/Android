package com.example.mapremiereapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.mapremiereapplication.R;
import com.example.mapremiereapplication.bo.Article;
import com.example.mapremiereapplication.repository.ArticleBddRepository;
import com.example.mapremiereapplication.repository.IArticleRepository;
import com.example.mapremiereapplication.view_model.ArticleViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private Context context = null;
    private Article article = null;


    /**
     * Premiere fonction appelée dans le cycle de vie de l'activité.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArticleViewModel vm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        Bundle extras=  getIntent().getExtras();
        this.article = (Article) extras.get("article");
        //Charge l'ihm
        setContentView(R.layout.activity_main);
        Button.OnClickListener btn = new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(article.getUrl()));
                startActivity(viewIntent);
            }


        };

        LiveData<List<Article>> observateur = vm.get();

        observateur.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                for ( Article article : articles)
                {
                    Log.i("wololo","ITEM : " + article);
                }
            }
        });

        TextView nomtxt= (TextView) findViewById(R.id.nomtxt);
        TextView descriptiontxt= (TextView) findViewById(R.id.descriptiontxt);
        TextView prixtxt= findViewById(R.id.prixtxt);;
        TextView achetetxt = findViewById(R.id.tv_achete);

        if(article.getAchete()) {
            achetetxt.setText("ACHETÉ");
        }else{
            achetetxt.setText("NON ACHETÉ");
        }
        nomtxt.setText(article.getNom());
        descriptiontxt.setText(article.getDescription());
        prixtxt.setText(article.getPrix().toString());
    }

    public void onClickAchete(View view) {
        ((Button) findViewById(R.id.achete)).setEnabled(false);
    }



    public void onClickEdit(View view) {
        Intent intent= new Intent(this, EditArticleActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }

    /*
    public void OnclickSave(View view) {
        SharedPreferences monFichier = getSharedPreferences("Wololo", MODE_PRIVATE);
        SharedPreferences.Editor editor = monFichier.edit();

        editor.putString("cle", "123456789");
        editor.putInt("key", 555);

        editor.commit();

    }

    public void onClickLoad(View view) {
        SharedPreferences monFichier = getSharedPreferences("Wololo", MODE_PRIVATE);
        String info1 = monFichier.getString("cle", "RIEN");
        int info2 = monFichier.getInt("key", -444);

        Toast.makeText(this, "infos " + info1 + " " + info2, Toast.LENGTH_SHORT).show();


    }*/
}