package com.beyazidyargici.revolutcurrency.di.module

import com.beyazidyargici.revolutcurrency.data.network.ApiService
import com.beyazidyargici.revolutcurrency.RevolutCurrencyApp
import com.beyazidyargici.revolutcurrency.data.model.CurrencyMapper
import com.beyazidyargici.revolutcurrency.util.BASE_URL
import com.beyazidyargici.revolutcurrency.util.InternetConnectionAvailability
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    /*
     * The method returns the Gson object
     * */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gson: Gson
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        gson = gsonBuilder.create()
        GsonConverterFactory.create(gson)
        return gson
    }

    /*
     * The method returns the Cache object
     * todo unused
     */
    @Provides
    @Singleton
    fun provideCache(application: RevolutCurrencyApp): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        //build interceptor
        return Interceptor { chain ->
            // HttpUrl builder
            val url = chain.request()
                .url()
                .newBuilder()
                .build()
            // request builder
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }
    }

    /*
     * This returns the OkHttpClient.
     * This will be using by Retrofit
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()


    }

    /*
     * The method returns the Retrofit object
     */
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    /*
     * We need provide ApiService for fetching data from API.
     * For this, We need the Retrofit object, Gson and OkHttpClient .
     * So we will define the providers for these objects here in this module.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create<ApiService>(ApiService::class.java)

    @Provides
    @Singleton
    internal fun provideStethoInitializerBuilder(context: RevolutCurrencyApp): Stetho.InitializerBuilder {
        val initializerBuilder = Stetho.newInitializerBuilder(context)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        return initializerBuilder
    }

    @Provides
    @Singleton
    internal fun provideStethoInitializer(initializerBuilder: Stetho.InitializerBuilder): Stetho.Initializer = initializerBuilder.build()

    @Provides
    fun provideCurrencyMapper()= CurrencyMapper()

    @Provides
    @Singleton
    fun provideInternetConnectionAvailability(context: RevolutCurrencyApp) = InternetConnectionAvailability(context)

}