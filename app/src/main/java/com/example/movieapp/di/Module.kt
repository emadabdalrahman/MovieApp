package com.example.movieapp.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.data.remote.data_source.MovieRemoteSource
import com.example.movieapp.data.remote.data_source.MovieRemoteSourceImpl
import com.example.movieapp.data.repository.MovieRepo
import com.example.movieapp.data.repository.MovieRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRemoteSource(source: MovieRemoteSourceImpl): MovieRemoteSource = source

    @Singleton
    @Provides
    fun provideCategoryRepo(repo: MovieRepoImpl): MovieRepo = repo

}