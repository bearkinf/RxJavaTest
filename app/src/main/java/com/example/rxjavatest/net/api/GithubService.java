package com.example.rxjavatest.net.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Single<String> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Single<Response<String>> listRepos2(@Path("user") String user);
}
