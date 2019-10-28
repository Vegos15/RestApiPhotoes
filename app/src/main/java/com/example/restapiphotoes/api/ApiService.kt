package com.example.restapiphotoes.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    fun getUsers(
        @Query("1") amount: Int
    ): Observable<Model>
}