package com.gemy.ahmed.tmas2.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "movies_table")
public class Movie implements Parcelable {

    public Movie(int id, String overview, String title, double vote_average, String poster_path, String release_date) {
        this.id = id;
        this.overview = overview;
        this.title = title;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.release_date = release_date;
    }

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private double vote_average;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @Ignore
    @SerializedName("results")
    @Expose
    private List<Movie> results = new ArrayList<>();

    protected Movie(Parcel in) {
        id = in.readInt();
        overview = in.readString();
        title = in.readString();
        vote_average = in.readDouble();
        poster_path = in.readString();
        release_date = in.readString();
        results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public List<Movie> getResults() {
        return results;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(overview);
        dest.writeString(title);
        dest.writeDouble(vote_average);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeTypedList(results);
    }
}
