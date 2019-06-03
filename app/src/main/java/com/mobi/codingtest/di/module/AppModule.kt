package com.mobi.codingtest.di.module

import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }
}