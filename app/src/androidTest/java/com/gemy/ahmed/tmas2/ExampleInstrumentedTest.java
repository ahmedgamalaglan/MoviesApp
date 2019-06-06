package com.gemy.ahmed.tmas2;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.gemy.ahmed.tmas2.database.MovieDao;
import com.gemy.ahmed.tmas2.database.MoviesDataBase;
import com.gemy.ahmed.tmas2.entities.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MovieDao movieDao;
    private MoviesDataBase db;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MoviesDataBase.class).build();
        movieDao = db.movieDao();
    }

    @Test
    public void testDataBase() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        Movie movie = new Movie(54,
                "overView Test",
                "test Title",
                7.5,
                "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
                "release date test");
        movieDao.insertMovie(movie);
        LiveData<List<Movie>> movies=movieDao.getMovies();
        assertThat(movies.getValue().get(0),equalTo(movie));
    }


    @After
    public void closeDb() throws IOException {
        db.close();
    }
}
