package com.example.restapiphotoes.api

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.NoSuchAlgorithmException

object DataSource {

    private const val URL = "https://uinames.com/api/"

    private val json by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val okHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(json))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun getUsers(amount: Int): Observable<Model> {
        return apiService.getUsers(amount)
    }

    fun getUserHash(userName: String): String {
        return hashString(userName)
    }

    private fun hashString(s: String): String {
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices)
                hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
//
//
//    private fun hashString(s: String): Observable<String> {
//        try {
//            // Create MD5 Hash
//            val digest = java.security.MessageDigest.getInstance("MD5")
//            digest.update(s.toByteArray())
//            val messageDigest = digest.digest()
//
//            // Create Hex String
//            val hexString = StringBuffer()
//            for (i in messageDigest.indices)
//                hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
//            return Observable.just(hexString.toString())
//
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//        }
//        return Observable.empty()
    }
}