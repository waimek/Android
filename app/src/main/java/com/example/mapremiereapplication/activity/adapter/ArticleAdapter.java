package com.example.mapremiereapplication.activity.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mapremiereapplication.R;
import com.example.mapremiereapplication.bo.Article;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article>{


    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.style_ligne_article, parent, false);
        }
        // On met les données dans la Ligne
        TextView tvNom = convertView.findViewById(R.id.nom);
        tvNom.setText(getItem(position).getNom());
        TextView tvPrix = convertView.findViewById(R.id.prix);
        tvPrix.setText(getItem(position).getPrix().toString());
        RatingBar tvNote = convertView.findViewById(R.id.note);
        tvNote.setRating(getItem(position).getNote());
        // On retourne a la ligne
        return convertView;

    }
}
