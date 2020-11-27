package com.alisdnn.joke.di.home

import dagger.Module
import dagger.Provides
import com.alisdnn.data.api.JokeApi
import com.alisdnn.data.datasource.joke.JokesApiDataSource
import com.alisdnn.data.datasource.joke.JokesApiDataSourceImpl
import com.alisdnn.data.datasource.joke.JokesDatabaseDataSource
import com.alisdnn.data.datasource.joke.JokesDatabaseDataSourceImpl
import com.alisdnn.data.db.joke.JokeDao
import com.alisdnn.data.repository.joke.JokesRepositoryImpl
import com.alisdnn.domain.repository.joke.JokesRepository
import com.alisdnn.domain.usecase.joke.GetJokesUseCase
import com.alisdnn.domain.usecase.joke.GetJokesUseCaseImpl
import com.alisdnn.presentation.common.transformer.AsyncFTransformer
import com.alisdnn.presentation.common.transformer.AsyncSTransformer
import java.util.concurrent.Executors


@Module
class HomeModule {

    @Provides
    //@PerFragment
    fun provideDatabaseSource(jokeDao: JokeDao): JokesDatabaseDataSource =
            JokesDatabaseDataSourceImpl(jokeDao, Executors.newSingleThreadExecutor())

    @Provides
    //@PerFragment
    fun provideApiSource(api: JokeApi): JokesApiDataSource = JokesApiDataSourceImpl(api)

    @Provides
    //@PerFragment
    fun provideRepository(
            apiSource: JokesApiDataSource,
            databaseSource: JokesDatabaseDataSource
    ): JokesRepository {
        return JokesRepositoryImpl(apiSource, databaseSource)
    }

    @Provides
    //@PerFragment
    fun provideGetAlbumsUseCaseImpl(repository: JokesRepository): GetJokesUseCase = GetJokesUseCaseImpl(AsyncFTransformer(), AsyncSTransformer(), AsyncSTransformer(), repository)
}
