package com.mobi.codingtest.di.module

import androidx.lifecycle.ViewModelProvider

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobi.codingtest.networkcall.ApiCall
import com.mobi.codingtest.networkcall.ApiConfig
import com.mobi.codingtest.repository.Repository
import com.mobi.codingtest.viewmodel.ViewModelFactory

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    internal val requestHeader: OkHttpClient
        @Provides
        @Singleton
        get() {

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder().build()
                chain.proceed(request)
            }
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)

            return httpClient.build()
        }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(ApiConfig.BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    internal fun getApiCallInterface(retrofit: Retrofit): ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

    @Provides
    @Singleton
    internal fun getRepository(apiCallInterface: ApiCall): Repository {
        return Repository(apiCallInterface)
    }

    @Provides
    @Singleton
    internal fun getViewModelFactory(myRepository: Repository): ViewModelProvider.Factory {
        return ViewModelFactory(myRepository)
    }
}