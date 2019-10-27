package com.ono.simple

import com.ono.simple.Model.TestEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi{
    @GET("test/{id}")
    fun get(@Path("id") id : Int) : Single<TestEntity>
}