package com.maciel.murillo.mutalk.di

import com.maciel.murillo.mutalk.data.datasource.AuthDataSource
import com.maciel.murillo.mutalk.data.remote.AuthDataSourceImpl
import com.maciel.murillo.mutalk.data.repository.AuthRepositoryImpl
import com.maciel.murillo.mutalk.domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {

//    single<StorageReference> { FirebaseStorage.getInstance().reference }
//    single<DataStore<Preferences>> { androidContext().dataStore }

    single<AuthDataSource> { AuthDataSourceImpl(auth = get()) }
    single<AuthRepository> { AuthRepositoryImpl(authDataSource = get()) }
}