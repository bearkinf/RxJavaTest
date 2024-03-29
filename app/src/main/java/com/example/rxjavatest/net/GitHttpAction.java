package com.example.rxjavatest.net;

import com.example.rxjavatest.net.api.GithubService;
import com.example.rxjavatest.net.api.GithubService_2;
import com.example.rxjavatest.net.client.HttpServer;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class GitHttpAction {

    public static CompositeDisposable disposable = new CompositeDisposable();


    public static Single<String> listRepos(String user) {
        return HttpServer.getInstance().api.listRepos(user)
                .subscribeOn(Schedulers.io());
    }

    public static Single<String> listRepos22(String path) {
        return HttpServer.getInstance().api.listRepos222(path)
                .subscribeOn(Schedulers.io());
    }

    private static GithubService_2 githubService_2 = HttpServer.getInstance().apiService(GithubService_2.class);

    public static Single<Response<String>> listRepos2(String user) {

        return githubService_2.listRepos2("bearkinf").subscribeOn(Schedulers.io());
    }
}
