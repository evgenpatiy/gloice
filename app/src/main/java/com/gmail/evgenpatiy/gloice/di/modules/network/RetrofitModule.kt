package com.gmail.evgenpatiy.gloice.di.modules.network

import android.content.Context
import com.gmail.evgenpatiy.gloice.api.RestApiInterface
import com.gmail.evgenpatiy.gloice.di.qualifiers.ApplicationContext
import com.gmail.evgenpatiy.gloice.di.scopes.ApplicationScope
import com.gmail.evgenpatiy.gloice.helpers.validator.GloiceValidator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

@Module
object RetrofitModule {

    private const val backendURL: String = "https://api.themoviedb.org/3/"
    private const val fqdn: String = "api.themoviedb.org"
    private const val cacheSize: Long = 10 * 1024 * 1024 // 10 megabytes of cache

    @Provides
    @ApplicationScope
    fun provideNetworkValidator(@ApplicationContext context: Context) = GloiceValidator(context)

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(
                LocalDate::class.java,
                JsonLocalDateDeserializer()
            )
            .create()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    fun provideCacheInterceptor(validator: GloiceValidator): CacheInterceptor =
        CacheInterceptor(validator)

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    @ApplicationScope
    fun provideRestApiInterface(retrofit: Retrofit): RestApiInterface =
        retrofit.create(RestApiInterface::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(backendURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        authInterceptor: AuthInterceptor,
        cacheInterceptor: CacheInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val cache = Cache(context.cacheDir, cacheSize)

        val fqdnHostnameVerifier = HostnameVerifier { hostname, session ->
            val verifier: HostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
            verifier.verify(fqdn, session)
        }
        return OkHttpClient.Builder()
            .hostnameVerifier(fqdnHostnameVerifier)
            .addInterceptor(authInterceptor)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }
}