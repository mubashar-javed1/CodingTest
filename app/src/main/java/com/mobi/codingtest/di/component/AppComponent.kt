package com.mobi.codingtest.di.component

import com.mobi.codingtest.di.module.AppModule
import com.mobi.codingtest.di.module.RetrofitModule
import com.mobi.codingtest.ui.mainactivity.MainActivity

import javax.inject.Singleton

import dagger.Component

@Component(modules = [AppModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    fun doInjection(mainActivity: MainActivity)
}
