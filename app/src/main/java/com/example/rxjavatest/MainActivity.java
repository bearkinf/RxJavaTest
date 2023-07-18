package com.example.rxjavatest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjavatest.databinding.ActivityMainBinding;
import com.example.rxjavatest.net.GitHttpAction;
import com.example.rxjavatest.net.Global;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import kotlin.collections.ArraysKt;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//

//        // 연속적인 flatmap 사용으로 데이터를 계속 처리하려면?????
//        disposable.add(GitHttpAction.listRepos("bearkinf")
//                .map(s -> {
//                            ArrayList<String> arrayList = new ArrayList<>();
//                            arrayList.add(s);
//                            return arrayList;
//                        }
//                )
//                .flatMap(
//                        strings -> {
//                            return GitHttpAction.listRepos("bearkinf").map(
//                                    s -> {
//                                        strings.add(s);
//                                        return strings;
//                                    }
//                            );
//                        }
//                )
//                .flatMap(strings -> {
//                    return GitHttpAction.listRepos("bearkinf").map(
//                            s -> {
//                                strings.add(s);
//                                return strings;
//                            });
//                })
//                .subscribe(s -> {
//                    Log.d("bear", "fdsafdsa a : " + s.size());
//                }, throwable -> {
//                    throwable.printStackTrace();
//                    Log.e("bear", "error :  " + throwable.getMessage());
//
//                })
//        );


        Global.repos = "users/%s/repos";
        disposable.add(Single.just(String.format(Global.repos, "bearkinf"))

//                        .concatMap(strings -> {
//                            return GitHttpAction.listRepos("bearkinf")
//                                    .map(s -> {
//                                                strings.add(s);
//                                                return strings;
//                                            }
//                                    );
//                        })

                        .concatMap(strings -> {
                            return GitHttpAction.listRepos22(strings);

                        })
                        .subscribe(strings -> {
                            Log.e("bear", "strings : " + strings);

                        }, throwable -> {
                            throwable.printStackTrace();
                        })
        );


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("bear", "destory:");
        disposable.dispose();
    }
}