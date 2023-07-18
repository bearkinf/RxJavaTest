package com.example.rxjavatest.net.client;

import android.util.Log;

import com.example.rxjavatest.net.api.GithubService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpServer {

    private static HttpServer instance;

    //
    public static synchronized HttpServer getInstance() {

        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    private HttpServer() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String BaseUrl = "https://api.github.com/";

    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(s -> {
                Log.e("http", "body: " + s);
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .build();

    public final GithubService api = retrofit.create(GithubService.class);


    public <T> T apiService(final Class<T> service) {
        return retrofit.create(service);
    }


}
