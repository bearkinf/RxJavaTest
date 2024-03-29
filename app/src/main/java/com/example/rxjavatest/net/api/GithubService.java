package com.example.rxjavatest.net.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GithubService {
    @GET("users/{user}/repos")
    Single<String> listRepos(@Path("user") String user);

    @GET("{path}")
    Single<String> listRepos22(@Path(value = "path", encoded = true) String path);


    @GET
    Single<String> listRepos222(@Url String path);

    @GET("users/{user}/repos")
    Single<Response<String>> listRepos2(@Path("user") String user);
}
