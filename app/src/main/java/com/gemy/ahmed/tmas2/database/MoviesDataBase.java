package com.gemy.ahmed.tmas2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gemy.ahmed.tmas2.entities.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDataBase extends RoomDatabase {

    private static volatile MoviesDataBase Instance;

    public abstract MovieDao movieDao();

    public static synchronized MoviesDataBase getInstance(Context context) {
        if (Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(),
                    MoviesDataBase.class, "movies_dataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        Instance.addFakeMovies();
        return Instance;
    }

    private void addFakeMovies() {
        Movie movie = new Movie(54,
                "overView Test",
                "test Title",
                7.5,
                "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
                "release date test");
        try {

                movieDao().insertMovie(movie);

        }catch (Exception e){

        }
    }

}
