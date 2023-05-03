package com.rehlat.rehlatsample.api

import com.rehlat.rehlatsample.models.MainDataClass
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    //creating a suspend func to use it in a coroutine
    @GET("default/dynamodb-writer")
    suspend fun getData() : Response<MainDataClass>
}