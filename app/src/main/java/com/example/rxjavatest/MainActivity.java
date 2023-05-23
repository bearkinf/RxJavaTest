package com.example.rxjavatest;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjavatest.databinding.ActivityMainBinding;
import com.example.rxjavatest.net.GitHttpAction;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Disposable dd = GitHttpAction.listRepos("fdsafdas")
                .subscribe(s -> {
                    Log.d("bear", "fdsafdsa a : " + s);
                }, throwable -> {
                    throwable.printStackTrace();
//                    disposable.dispose();
                });

        Log.v("bear", "dddhhanddf : " + dd.isDisposed());

        disposable.add(dd);
        Log.w("bear", "dddhhanddf : " + disposable.isDisposed());


        new Handler().postDelayed(() -> {

            Log.w("bear", "dddhhanddf : " + dd.isDisposed());
            Log.e("bear", "dddhhanddf : " + disposable.isDisposed());
        }, 5000);


        binding.HelloWorld.setOnClickListener(v -> {
//            disposable.dispose();
            new Handler().postDelayed(() -> {
                Log.w("bear", "dddhhanddf : " + dd.isDisposed());
//                Log.e("bear", "dddhhanddf : " + disposable.isDisposed());
            }, 5000);

        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("bear", "destory:");
        disposable.dispose();
    }
}