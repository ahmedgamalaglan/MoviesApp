package com.gemy.ahmed.tmas2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gemy.ahmed.tmas2.entities.Movie;

@Database(entities = Movie.class, version = 2,exportSchema = false)
public abstract class MoviesDataBase extends RoomDatabase {

    private static volatile MoviesDataBase Instance;

    public abstract MovieDao movieDao();

    public static MoviesDataBase getInstance(Context context) {
        if (Instance == null) {
            synchronized (MoviesDataBase.class) {
                Instance = Room.databaseBuilder(context, MoviesDataBase.class, "movies_dataBase").build();
            }
        }
        return Instance;
    }

}
