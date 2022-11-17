package com.jc.collantes.emovie.data.service.local

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.room.*
import androidx.test.core.app.ApplicationProvider
import com.jc.collantes.emovie.data.model.database.entity.TopRatedMovie
import com.jc.collantes.emovie.data.model.database.entity.UpcomingMovie
import com.jc.collantes.emovie.data.model.local.LocalMockData
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = "AndroidManifest.xml",
    application = MovieDaoTest.ApplicationStub::class,
    sdk = [Build.VERSION_CODES.M]
)
class MovieDaoTest {

    private val application: Application by lazy {
        ApplicationProvider.getApplicationContext<ApplicationStub>()
    }

    private val context: Context by lazy { application }

    private lateinit var database: MovieDataBase

    private lateinit var dao: MovieDao

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        database = Room
            .inMemoryDatabaseBuilder(context, MovieDataBase::class.java)
            .allowMainThreadQueries().build()
        dao = database.movieDao
    }

    class ApplicationStub : Application()

    @kotlin.jvm.Throws(IOException::class)
    @After
    fun onDestroy() {
        database.close()
    }


    @Test
    fun getMovieDetails() = runTest {
        val fakeMovie = LocalMockData.getFakeMovieDetail()
        dao.insert(fakeMovie)
        val movie = dao.getMovieDetails(fakeMovie.id)
        Assert.assertEquals(fakeMovie,movie)
    }

    @Test
    fun getTopRatedMovies() = runTest {
        val fakeList = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeList)
        dao.deleteTopRatedMovies()
        dao.addTopRatedMovies(LocalMockData.getFakeIdsList().map { TopRatedMovie(it)})
        val result = dao.getTopRatedMovies()
        Assert.assertEquals(fakeList,result)
    }

    @Test
    fun getUpcomingMovies() = runTest {
        val fakeList = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeList)
        dao.deleteUpcomingMovies()
        dao.addUpcomingMovies(LocalMockData.getFakeIdsList().map { UpcomingMovie(it)})
        val result = dao.getUpcomingMovies()
        Assert.assertEquals(fakeList,result)
    }

    @Test
    fun insert() = runTest {
        val fakeMovie = LocalMockData.getFakeMovieDetail()
        dao.insert(fakeMovie)
        val movie = dao.getMovieDetails(fakeMovie.id)
        movie?.let { Assert.assertEquals(fakeMovie.id, it.id) }
    }

    @Test
    fun update() = runTest {
        val fakeMovie = LocalMockData.getFakeMovieDetail()
        dao.insert(fakeMovie)
        var movie = dao.getMovieDetails(fakeMovie.id)
        movie?.let { Assert.assertEquals(null, it.originalTitle) }
        val fakeMovieEdited = LocalMockData.getFakeMovieDetailEdited()
        dao.update(fakeMovieEdited)
        movie = dao.getMovieDetails(fakeMovie.id)
        movie?.let { Assert.assertEquals(fakeMovieEdited.originalTitle, it.originalTitle) }

    }

    @Test
    fun upsertMovie() = runTest{
        val fakeMovie = LocalMockData.getFakeMovieDetail()
        dao.upsertMovie(fakeMovie)
        var movie = dao.getMovieDetails(fakeMovie.id)
        movie?.let { Assert.assertEquals(null, it.originalTitle) }
        val fakeMovieEdited = LocalMockData.getFakeMovieDetailEdited()
        dao.upsertMovie(fakeMovieEdited)
        movie = dao.getMovieDetails(fakeMovie.id)
        movie?.let { Assert.assertEquals(fakeMovieEdited.originalTitle, it.originalTitle) }
    }

    @Test
    fun addUpcomingMovies() = runTest {
        val fakeList = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeList)
        dao.deleteUpcomingMovies()
        dao.addUpcomingMovies(LocalMockData.getFakeIdsList().map { UpcomingMovie(it)})
        val result = dao.getUpcomingMovies()
        Assert.assertEquals(fakeList.size, result?.size ?: 0)
    }

    @Test
    fun addTopRatedMovies() = runTest {
        val fakeList = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeList)
        dao.deleteTopRatedMovies()
        dao.addTopRatedMovies(LocalMockData.getFakeIdsList().map { TopRatedMovie(it)})
        val result = dao.getTopRatedMovies()
        Assert.assertEquals(fakeList.size, result?.size ?: 0)
    }

    @Test
    fun insertMovieList() = runTest {
        val fakeMovies = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeMovies)
        val movies = dao.getMovieDetailsList()
        Assert.assertEquals(fakeMovies.size, movies?.size ?: 0)
    }

    @Test
    fun updateMovieList() = runTest {
        val fakeMovies = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeMovies)
        val movies = dao.getMovieDetailsList()
        Assert.assertEquals(fakeMovies.size, movies?.size ?: 0)
        val fakeMoviesEdited = LocalMockData.getFakeMovieDetailList()
        dao.updateMovieList(fakeMoviesEdited)
        val moviesEdited = dao.getMovieDetailsList()
        Assert.assertEquals(fakeMoviesEdited.size, moviesEdited?.size ?: 0)
    }

    @Test
    fun upsertMovies() = runTest {
        val fakeMovies = LocalMockData.getFakeMovieDetailList()
        dao.upsertMovies(fakeMovies)
        val movies = dao.getMovieDetailsList()
        Assert.assertEquals(fakeMovies.size, movies?.size ?: 0)
        val fakeMoviesEdited = LocalMockData.getFakeMovieDetailList()
        dao.upsertMovies(fakeMoviesEdited)
        val moviesEdited = dao.getMovieDetailsList()
        Assert.assertEquals(fakeMoviesEdited.size, moviesEdited?.size ?: 0)
    }

    @Test
    fun deleteTopRatedMovies() = runTest {
        dao.deleteTopRatedMovies()
        val fakeList = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeList)
        val fakeListIds = LocalMockData.getFakeIdsList()
        dao.addTopRatedMovies(fakeListIds.map { TopRatedMovie(it)})
        var result = dao.getTopRatedMovies()
        Assert.assertEquals(fakeListIds.size, result?.size ?: 0)
        dao.deleteTopRatedMovies()
        result = dao.getTopRatedMovies()
        Assert.assertEquals(0, result?.size ?: -1)
    }

    @Test
    fun deleteUpcomingMovies() = runTest {
        dao.deleteTopRatedMovies()
        val fakeList = LocalMockData.getFakeMovieDetailList()
        dao.insertMovieList(fakeList)
        val fakeListIds = LocalMockData.getFakeIdsList()
        dao.addUpcomingMovies(fakeListIds.map { UpcomingMovie(it)})
        var result = dao.getUpcomingMovies()
        Assert.assertEquals(fakeList.size, result?.size ?: 0)
        dao.deleteUpcomingMovies()
        result = dao.getUpcomingMovies()
        Assert.assertEquals(0, result?.size ?: -1)
    }
}