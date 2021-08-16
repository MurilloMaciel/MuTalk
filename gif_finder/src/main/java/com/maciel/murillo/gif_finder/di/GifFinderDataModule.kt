package com.maciel.murillo.gif_finder.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.data.datasource.GifFinderLocalDataSource
import com.maciel.murillo.gif_finder.data.datasource.GifFinderRemoteDataSource
import com.maciel.murillo.gif_finder.data.local.GifFinderLocalDataSourceImpl
import com.maciel.murillo.gif_finder.data.local.cache.GifFinderCacheSerializer
import com.maciel.murillo.gif_finder.data.local.cache.createCacheDatastore
import com.maciel.murillo.gif_finder.data.remote.GifFinderApi
import com.maciel.murillo.gif_finder.data.remote.GifFinderRemoteDataSourceImpl
import com.maciel.murillo.gif_finder.data.repository.GifFinderRepositoryImpl
import com.maciel.murillo.gif_finder.data.util.Constants.BASE_URL
import com.maciel.murillo.gif_finder.domain.repository.GifFinderRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.maciel.murillo.network.network.createNetworkClient
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
interface GifFinderDataModule {

    @Binds
    fun bindRepository(repository: GifFinderRepositoryImpl): GifFinderRepository

    @Binds
    fun bindRemoteDataSource(dataSource: GifFinderRemoteDataSourceImpl): GifFinderRemoteDataSource

    @Binds
    fun bindLocalDataSource(dataSource: GifFinderLocalDataSourceImpl): GifFinderLocalDataSource

    companion object {
        @Provides
        fun provideDatastore(@ApplicationContext context: Context): DataStore<GifListProto> {
            return context.createCacheDatastore(GifFinderCacheSerializer)
        }

        @Provides
        fun provideApi(): GifFinderApi {
            return createNetworkClient(baseUrl = BASE_URL).create(GifFinderApi::class.java)
        }
    }
}