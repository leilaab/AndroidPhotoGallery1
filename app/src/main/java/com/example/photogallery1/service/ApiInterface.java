package com.example.photogallery1.service;


import com.example.photogallery1.model.CategoryResponse;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {




    @GET("?tags=any&tagmode=any&format=json&nojsoncallback=1")
    Call<CategoryResponse> getCategories();
    @GET("?tags=any&tagmode=any&format=json&nojsoncallback=1")
    Call<CategoryResponse> getCategories(@Query("tags") String tags);






}
