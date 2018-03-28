package com.tieorange.graphqlwdi

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.http.ApolloHttpCache
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import java.io.File

class NetModule(private val context: Context) {
    private fun provideOkHttpClient(): OkHttpClient {
        val builder = Builder()
                .addInterceptor(ChuckInterceptor(context))

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    private fun provideApollo(): ApolloClient {
        //Directory where cached responses will be stored
        val file = File("/cache/")

        //Size in bytes of the cache
        val size = 1024 * 1024L

        //Create the http response cache store
        val cacheStore = DiskLruHttpCacheStore(file, size)

        //Build the Apollo Client
        return ApolloClient.builder()
                .serverUrl(BASE_URL)
                .httpCache(ApolloHttpCache(cacheStore))
                .okHttpClient(provideOkHttpClient())
                .build()
    }

    internal fun provideGraphQlClient(): GraphQlClient {
        return GraphQlClient(provideApollo())
    }

    companion object {
        val BASE_URL = "https://api.graph.cool/simple/v1/cjdjyomln2cqh0165r55pm2na"
    }
}