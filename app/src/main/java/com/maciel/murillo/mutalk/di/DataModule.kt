package com.maciel.murillo.mutalk.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.maciel.murillo.mutalk.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.mutalk.data.datasource.UserRemoteDataSource
import com.maciel.murillo.mutalk.data.remote.AuthRemoteDataSourceImpl
import com.maciel.murillo.mutalk.data.remote.UserRemoteDataSourceImpl
import com.maciel.murillo.mutalk.data.repository.AuthRepositoryImpl
import com.maciel.murillo.mutalk.data.repository.UserRepositoryImpl
import com.maciel.murillo.mutalk.domain.repository.AuthRepository
import com.maciel.murillo.mutalk.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<StorageReference> { FirebaseStorage.getInstance().reference }
    single<FirebaseFirestore> { Firebase.firestore }
    single<FirebaseAuth> { Firebase.auth }

    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(auth = get()) }
    single<AuthRepository> { AuthRepositoryImpl(authRemoteDataSource = get()) }

    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(db = get()) }
    single<UserRepository> { UserRepositoryImpl(userRemoteDataSource = get()) }
}