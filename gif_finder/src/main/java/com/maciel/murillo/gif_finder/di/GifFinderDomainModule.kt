package com.maciel.murillo.gif_finder.di

import com.maciel.murillo.gif_finder.domain.usecase.GetTrendingGifsUseCase
import com.maciel.murillo.gif_finder.domain.usecase.GetTrendingGifsUseCaseImpl
import com.maciel.murillo.gif_finder.domain.usecase.SearchGifsUseCase
import com.maciel.murillo.gif_finder.domain.usecase.SearchGifsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GifFinderDomainModule {

    @Binds
    fun bindGetTrendingGifsUseCase(useCase: GetTrendingGifsUseCaseImpl): GetTrendingGifsUseCase

    @Binds
    fun bindSearchGifsUseCase(useCase: SearchGifsUseCaseImpl): SearchGifsUseCase
}