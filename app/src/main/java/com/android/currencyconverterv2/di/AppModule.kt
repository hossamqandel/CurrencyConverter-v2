package com.android.currencyconverterv2.di

import com.android.currencyconverterv2.core.Const
import com.android.currencyconverterv2.data.remote.IWebService
import com.android.currencyconverterv2.data.repository.NotNullRepositoryImpl
import com.android.currencyconverterv2.domain.repository.INotNullRepository
import com.android.currencyconverterv2.domain.use_case.CurrencyConvertUseCase
import com.android.currencyconverterv2.presentation.main_screen.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMotNullConvertUseCase(iNotNullRepository: INotNullRepository): CurrencyConvertUseCase {
        return CurrencyConvertUseCase(iNotNullRepository)
    }


    @Provides
    @Singleton
    fun provideNotNullRepository(iWebService: IWebService): INotNullRepository {
        return NotNullRepositoryImpl(iWebService)
    }

    @Provides
    @Singleton
    fun provideNotNullViewModel(notNullConvertUseCase: CurrencyConvertUseCase): MainViewModel {
        return MainViewModel(notNullConvertUseCase)
    }


    @Provides
    @Singleton
    fun provideRetrofit(): IWebService {
        val client = OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .callTimeout(50, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val url = originalUrl.newBuilder().build()
                val requestBuilder = originalRequest.newBuilder().url(url)
                    .addHeader("X-RapidAPI-Key", Const.API_KEY_NOTNULL)
                    .addHeader("X-RapidAPI-Host", Const.NOTNULL_HOST)
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                response.code//status code
                response
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL_NOTNULL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(IWebService::class.java)
    }
}