package com.example.buidanhnam.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableAllSingle;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                 e.onNext("ngon1");
                 e.onNext("ngon2");
                 e.onNext("ngon3");
                 e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: " + Thread.currentThread().getName());
            }
        };
        stringObservable.subscribe(stringObserver);
        stringObservable.observeOn(AndroidS)


    }
}
