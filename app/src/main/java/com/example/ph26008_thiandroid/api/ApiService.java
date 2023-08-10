package com.example.ph26008_thiandroid.api;

import com.example.ph26008_thiandroid.models.SpModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://63db6922a3ac95cec5a10e24.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
     @GET("demo-api/demo")
    Call<List<SpModel>> GetDataMockApi();

     @POST("demo-api/demo")
     Call<SpModel> ThemDataToSever(@Body SpModel spModel);

     @PUT("demo-api/demo/{id}")
     Call<SpModel> UpdateData(@Path("id") String id, @Body SpModel spModel );

     @DELETE("demo-api/demo/{id}")
     Call<SpModel> DeleteData(@Path("id") String id, @Body SpModel spModel);



}
