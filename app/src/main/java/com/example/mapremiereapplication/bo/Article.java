package com.example.mapremiereapplication.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private int note;
    private String url;
    private Boolean achete;
    private Float prix;
    private String description;

    public Article(String nom, int note, String url, Boolean achete, Float prix, String description) {
        this.nom = nom;
        this.note = note;
        this.url = url;
        this.achete = achete;
        this.prix = prix;
        this.description = description;
    }

    protected Article(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        note = in.readInt();
        url = in.readString();
        byte tmpAchete = in.readByte();
        achete = tmpAchete == 0 ? null : tmpAchete == 1;
        if (in.readByte() == 0) {
            prix = null;
        } else {
            prix = in.readFloat();
        }
        description = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getAchete() {
        return achete;
    }

    public void setAchete(Boolean achete) {
        this.achete = achete;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", note='" + note + '\'' +
                ", url='" + url + '\'' +
                ", achete=" + achete +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeInt(note);
        parcel.writeString(url);
        parcel.writeByte((byte) (achete == null ? 0 : achete ? 1 : 2));
        if (prix == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(prix);
        }
        parcel.writeString(description);
    }
}
