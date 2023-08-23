package com.example.rxjavatest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjavatest.databinding.ActivityMainBinding;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    CompositeDisposable disposable = new CompositeDisposable();


    private StringBuffer buffer = new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogPrintUtil.setDebug(true);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.startBtn.setOnClickListener(v -> {
            v.setEnabled(false);

            v.postDelayed(() -> {
                v.setEnabled(true);
                LogPrintUtil.e("click enabled");


            }, 1000);


        });


//        binding.startBtn.setOnClickListener(v -> {
//
//            if (binding.editText.getText().length() == 0 || binding.editText.getText().equals("")) {
//                return;
//            }
//            buffer = new StringBuffer();
//            gogodan(Integer.parseInt(binding.editText.getText().toString()));
//        });


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


//        Global.repos = "users/bearkin/repos";
//        disposable.add(Single.just(Global.repos)
//
////                        .concatMap(strings -> {
////                            return GitHttpAction.listRepos("bearkinf")
////                                    .map(s -> {
////                                                strings.add(s);
////                                                return strings;
////                                            }
////                                    );
////                        })
//
//                        .concatMap(strings -> {
//                            return GitHttpAction.listRepos22(strings);
//
//                        })
//                        .subscribe(strings -> {
//                            Log.e("bear", "strings : " + strings);
//
//                        }, throwable -> {
//                            throwable.printStackTrace();
//                        })
//        );


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("bear", "destory:");
        disposable.dispose();
    }


    void gogodan(int number) {

        LogPrintUtil.i("number : " + number);
//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//                .map(integer -> {
//                    return number * integer.intValue();
//                })
//
//                .subscribe(integer -> {
//
//                            buffer.append("gogodan : " + integer + "\n");
//                            binding.HelloWorld.setText(buffer.toString());
//
//                        }
//                );

        disposable.add(Observable.range(1, 9)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(integer -> {
                    LogPrintUtil.w(".subscribeOn(Schedulers.io())  : " + integer);
                    return integer;
                })
                .observeOn(Schedulers.io())
                .map(integer -> {

                    LogPrintUtil.v(".observeOn(Schedulers.io())  : " + integer);
                    int gogo = number * integer.intValue();
                    return buffer.append("gogodan : " + gogo + "\n").toString();
                })


                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    LogPrintUtil.e(" .observeOn(AndroidSchedulers.mainThread())  :\n" + s);

                }, throwable -> {
                    throwable.printStackTrace();
                }, () -> {

                    LogPrintUtil.e(" .observeOn(AndroidSchedulers.mainThread())  :  onComplete");
                    buffer.append("onComplete");
                    binding.HelloWorld.setText(buffer.toString());
                })
        );

//                .subscribe(integer -> {
//
////                            buffer.append("gogodan : " + integer + "\n");
////                            binding.HelloWorld.setText(buffer.toString());
//
//                        },
//                        throwable -> {
//                        },
//                        () -> {
//                            buffer.append("onComplete");
//                            binding.HelloWorld.setText(buffer.toString());
//                        }
//                );

    }

}