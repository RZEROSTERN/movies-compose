package mx.dev1.movies.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev1.movies.BuildConfig
import mx.dev1.movies.data.local.FavoriteMovieDao
import mx.dev1.movies.data.local.FavoriteMovieDatabase
import mx.dev1.movies.data.remote.MovieDbApi
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.data.repository.MoviesRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {
    private val okHttpClient = initOkHttpClient()

    @Singleton
    @Provides
    fun providesDatabase(application: Application): FavoriteMovieDatabase = Room.databaseBuilder(
        context = application,
        klass = FavoriteMovieDatabase::class.java,
        name="favorite_movie_db"
    ).build()

    @Singleton
    @Provides
    fun providesFavoriteMovieDao(favoriteMovieDatabase: FavoriteMovieDatabase) : FavoriteMovieDao =
        favoriteMovieDatabase.createFavoriteMovieDao()

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/") // Please change to constants
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun initOkHttpClient(): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS) // Please change to constants
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if(BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(interceptor)
        }

        return httpClient.build()
    }

    @Singleton
    @Provides
    fun movieDbApiProvider(retrofit: Retrofit) : MovieDbApi = retrofit.create(MovieDbApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class Binds {
    @Binds
    abstract fun moviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ) : MoviesRepository
}
