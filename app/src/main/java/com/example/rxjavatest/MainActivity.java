package com.example.rxjavatest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjavatest.databinding.ActivityMainBinding;
import com.example.rxjavatest.net.GitHttpAction;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        disposable.add(GitHttpAction.listRepos("bearkinf")
            
                .subscribe(s -> {
                    Log.d("bear", "fdsafdsa a : " + s);
                }, throwable -> {
                    throwable.printStackTrace();
                    Log.e("bear", "error :  " + throwable.getMessage());

                })
        );


//
//        disposable.add(GitHttpAction.listRepos2("bearkinf22")
//                .map(stringResponse -> {
//
//                            if (stringResponse.isSuccessful()) {
//                                return stringResponse.body();
//                            } else {
//                                throw new HttpException(stringResponse);
//                            }
//                        }
//
//                )
//                .subscribe(s -> {
//                    Log.d("bear", "fdsafdsa a : " + s);
//                }, throwable -> {
////                    throwable.printStackTrace();
//                    Log.e("bear", "error :  " + throwable.getMessage());
//
//                })
//
//        );


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("bear", "destory:");
        disposable.dispose();
    }
}