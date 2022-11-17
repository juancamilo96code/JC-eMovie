package com.jc.collantes.emovie.domain.di

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.jc.collantes.emovie.data.service.api.MovieService
import com.jc.collantes.emovie.data.service.local.MovieDao
import io.mockk.MockKAnnotations
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = "AndroidManifest.xml",
    application = AppModuleTest.ApplicationStub::class,
    sdk = [Build.VERSION_CODES.M]
)
class AppModuleTest {

    private lateinit var appModule :AppModule

    private val application: Application by lazy {
        ApplicationProvider.getApplicationContext<ApplicationStub>()
    }

    private val context: Context by lazy { application }

    class ApplicationStub : Application()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        appModule = AppModule
    }

    @Test
    fun provideMovieApiServiceGenerator() {
        val apiServiceGenerator = appModule.provideMovieApiServiceGenerator(context)
        val service = apiServiceGenerator.createService(MovieService::class.java)
        MatcherAssert.assertThat(
            service,
            CoreMatchers.instanceOf(MovieService::class.java)
        )
    }

    @Test
    fun provideMovieDao() {
        val dao = appModule.provideMovieDao(application)
        MatcherAssert.assertThat(
            dao,
            CoreMatchers.instanceOf(MovieDao::class.java)
        )
    }
}