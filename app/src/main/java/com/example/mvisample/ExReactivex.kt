package com.example.mvisample

import io.reactivex.Observable

fun <T> T.toObservable() = Observable.just(this)