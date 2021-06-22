package com.example.mvvm_demo_androidx.Retrofit_Demo

import com.example.mvvm_demo_androidx.Retrofit_Demo.InputOutput.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    // 測試網站     https://jsonplaceholder.typicode.com/
    // GET網址      https://jsonplaceholder.typicode.com/albums/1
    // POST網址     https://jsonplaceholder.typicode.com/albums
    // ...typicode.com/[這裡就是API的路徑]
    //TODO　Bill 目前用Response都沒辦法成功，已解決，原因:Retrofit升級到2.6.0就好了
    @GET("/albums")
    fun getAllUserData(): Call<List<UserData>> // 公司是用Response;例子是用Call(不能用suspend fun)

    @GET("/albums")
    suspend fun getAllUserData2(): Response<List<UserData>> //測試，使用Response

    @GET("/albums/1")
    suspend fun getUserData(): Call<UserData>

    @GET("/albums/{id}") // 用{}表示路徑參數，@Path會將參數帶入至該位置
    suspend fun getUserDataById(@Path("id") id: Int): Response<UserData>

    @GET("/albums") //在Url後面+?帶參數的方式要用 @Query Ex:?id=1
    suspend fun getUserDataById2(@Query("id") id: Int = 1): Response<UserData> // 如果有給預設值就可以不用帶參數到function裡面

    @POST("/albums") // 用@Body表示要傳送Body資料
    suspend fun postUserData(@Body userData: UserData): Response<UserData>

}