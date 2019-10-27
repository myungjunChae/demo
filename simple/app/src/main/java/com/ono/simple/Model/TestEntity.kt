package com.ono.simple.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TestEntity(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("test") @Expose val test: String
)

fun TestEntity.mapToDomain() = Test(id, test)