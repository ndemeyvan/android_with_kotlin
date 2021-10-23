package com.bee_studio.learn_recycler_view.UI.data.api

import com.bee_studio.learn_recycler_view.UI.data.api.Utils.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        /*Ici il est question de cree une Instance de Retrofit*/
        private val retrofit by lazy {
            //Ceci me permet de voir les requetes entrante et sortante
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            //Instance Retrofit
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

        }
        //Unique Instance de la classe API
        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }


    }
}