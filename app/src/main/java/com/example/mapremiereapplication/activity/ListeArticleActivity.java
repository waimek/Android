package com.example.mapremiereapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mapremiereapplication.R;
import com.example.mapremiereapplication.activity.adapter.ArticleAdapter;
import com.example.mapremiereapplication.bo.Article;
import com.example.mapremiereapplication.view_model.ArticleViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class ListeArticleActivity extends AppCompatActivity {

    ListView maListe = null;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_liste_article);
        maListe = findViewById(R.id.list_article);
        final ArticleViewModel vm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article article =(Article) maListe.getAdapter().getItem(i);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("article",article);
                startActivity(intent);
            }
        });

        maListe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Article article =(Article) maListe.getAdapter().getItem(i);
                                vm.delete(article);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Supprimer?").setPositiveButton("Oui", dialogClickListener)
                        .setNegativeButton("Annuler", dialogClickListener).show();

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArticleViewModel vm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        LiveData<List<Article>> observateur = vm.get();

        observateur.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                ArticleAdapter adapter = new ArticleAdapter(ListeArticleActivity.this, R.layout.style_ligne_article, articles);
                maListe.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void onClickNew(View view) {
        Intent intent = new Intent(this, InsertArticleActivity.class);
        startActivity(intent);
    }
}