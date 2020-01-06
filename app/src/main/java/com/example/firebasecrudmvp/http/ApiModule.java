package com.example.firebasecrudmvp.http;


import com.example.firebasecrudmvp.JsonPlacholdeApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @Provides
    public OkHttpClient providesOkHTTPClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();

    }

    @Provides
    public Retrofit providesRetofit(String baseURL,OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    public JsonPlacholdeApi providesJsonApi(){
        return providesRetofit(BASE_URL,providesOkHTTPClient()).create(JsonPlacholdeApi.class);

    }


}
