package com.example.citizenmecase.di


import com.example.citizenmecase.api.CitizenMeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
open class RetrofitModule {

    val baseUrl: String = "https://jsonplaceholder.typicode.com/"
    val contentType = "application/json".toMediaType()


    @Provides
    @Singleton
    fun provideRetrofitJson(OkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
                    .asConverterFactory(contentType)
            )
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideJsonApi(retrofit: Retrofit): CitizenMeApi {
        return retrofit.create(CitizenMeApi::class.java)
    }



    @Provides
    @Singleton
    fun provideOkHttp(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    @Singleton
    fun setLogLevel(): HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

    @Provides
    @Singleton
    fun setupOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .also {
                        it.level = setLogLevel()
                    }
            )
            .followRedirects(true)
            .followSslRedirects(true)
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
    }

}