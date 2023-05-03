package com.rehlat.rehlatsample.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {

    //retrofit instance is created using BASE_URL
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}