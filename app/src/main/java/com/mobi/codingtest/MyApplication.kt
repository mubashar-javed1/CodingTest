package com.mobi.codingtest

import android.app.Application

import com.mobi.codingtest.di.component.AppComponent
import com.mobi.codingtest.di.component.DaggerAppComponent
import com.mobi.codingtest.di.module.AppModule
import com.mobi.codingtest.di.module.RetrofitModule

@Suppress("DEPRECATION")
class MyApplication : Application() {
    lateinit var appComponent: AppComponent
        internal set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).retrofitModule(RetrofitModule()).build()
    }
}