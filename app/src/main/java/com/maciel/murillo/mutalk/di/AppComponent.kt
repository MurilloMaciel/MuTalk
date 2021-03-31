package com.maciel.murillo.mutalk.di

import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
        dataModule,
        domainModule,
        presentationModule
)