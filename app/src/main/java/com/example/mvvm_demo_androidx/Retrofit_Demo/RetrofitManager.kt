package com.example.mvvm_demo_androidx.Retrofit_Demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 這裡是單純建立一個單一個基底，
 * */
//建立Retrofit連線的基底
class RetrofitManager private constructor() { //TODO Bill private constructor()這個為什麼一定要
    /**
     * private constructor() 代表無法從外部去修改裡面參數資料，不是代表已經new過的意思!(這觀念是錯的 X)
     *
     * 觀念一 : 如果你使用的 class 不想要 init就必須搭配 companion object 靜態物件使用(java的static) ex: test().id ->有init ;test.id ->id參數要使用companion object
     * 觀念二 : 如果加了 private constructor() 也必須搭配 companion object 靜態物件使用，你沒辦法 new 他，因為他是 private。但如果是只有 constructor()就可以省略了，跟一般 class一樣。
     *
     * */

    // 以Singleton模式建立
    companion object {
        val mInstance = RetrofitManager()

        val client: Retrofit
            get() = mInstance.retrofit
    }

    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}