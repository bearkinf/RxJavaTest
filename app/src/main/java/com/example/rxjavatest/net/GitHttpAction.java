package com.example.rxjavatest.net;

import com.example.rxjavatest.net.client.HttpServer;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.http.Path;

public class GitHttpAction {

    public static Single<String> listRepos(String user) {

        return HttpServer.getInstance().api.listRepos(user)
                .subscribeOn(Schedulers.io())


                ;
    }

}
