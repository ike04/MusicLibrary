package com.google.codelab.musiclibrary.di

import com.google.codelab.musiclibrary.repository.*
import com.google.codelab.musiclibrary.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindArtistDetailRepository(artistDetailRepositoryImpl: ArtistDetailRepositoryImpl): ArtistDetailRepository

    @Singleton
    @Binds
    abstract fun bindChartMusicRepository(chartMusicRepositoryImpl: ChartMusicRepositoryImpl): ChartMusicRepository

    @Singleton
    @Binds
    abstract fun bindSearchMusicRepository(searchMusicRepositoryImpl: SearchMusicRepositoryImpl): SearchMusicRepository

    @Singleton
    @Binds
    abstract fun bindArtistDetailUseCase(artistDetailUseCaseImpl: ArtistDetailUseCaseImpl): ArtistDetailUseCase

    @Singleton
    @Binds
    abstract fun bindChartMusicUseCase(chartMusicUseCaseImpl: ChartMusicUseCaseImpl): ChartMusicUseCase

    @Singleton
    @Binds
    abstract fun bindSearchMusicUseCase(searchMusicUseCaseImpl: SearchMusicUseCaseImpl): SearchMusicUseCase
}
