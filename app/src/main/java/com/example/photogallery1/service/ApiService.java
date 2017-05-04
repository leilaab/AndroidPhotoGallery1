package com.example.photogallery1.service;


import com.example.photogallery1.model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {


    @GET("?tags=any&tagmode=any&format=json&nojsoncallback=1")
    Call<CategoryResponse> getAnswers();

    // RxJava
    // Observable<SOAnswersResponse> getAnswers();

//    @GET("?tags=photos&tagmode=any&format=json&jsoncallback=1")
//    Call<List<CategoryResponse>> getAnswers(@Query("tagged") String tags);

}
