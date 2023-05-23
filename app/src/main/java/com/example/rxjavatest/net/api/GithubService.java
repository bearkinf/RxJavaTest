package com.example.rxjavatest.net.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Single<String> listRepos(@Path("user") String user);
}
