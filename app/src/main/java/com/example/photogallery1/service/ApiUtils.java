package com.example.photogallery1.service;


public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne/";

    public static ApiService getApiService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
