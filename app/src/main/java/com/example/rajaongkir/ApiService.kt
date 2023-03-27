package com.example.rajaongkir

import com.example.rajaongkir.Response.ResponseListProvince
import com.example.rajaongkir.ResponseCity.ResponseListCity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //--------- Hitung COST -----------


    //------- select dropdown ------
//    @GET("province")
//    fun provinceById(
//        @Header("key") headerValue: String,
//        @Query("id") id:String
//    ):Call<ResponseProvince>

//    @GET("city")
//    fun cityById(
//        @Header("key") headerValue: String,
//        @Query("id") id:String,
//        @Query("province") province:String
//    ):Call<ResponseListCityByProvince>

    //------- LIST ------------
    @GET("province")
    fun listprovince(
        @Header("key") headerValue: String
    ):Call<ResponseListProvince>

    @GET("city")
    fun listCityByProvince(
        @Header("key") headerValue: String,
        @Query("province") province:String
    ):Call<ResponseListCity>

}