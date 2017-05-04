package com.example.photogallery1.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by m.ashrafzade on 11/5/2016.
 */

public class ApiClient {
//    public static String BaseUrl = "http://94.101.186.148:70/";
        public static String BaseUrl="http://web-srv:70/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {

        BaseUrl =  "https://api.flickr.com/services/feeds/photos_public.gne/";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

            retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
