package mx.dev1.movies.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev1.movies.data.remote.MovieDbApi
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.data.repository.MoviesRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {
    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
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
