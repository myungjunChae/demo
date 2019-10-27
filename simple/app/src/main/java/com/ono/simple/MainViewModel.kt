package com.ono.simple

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ono.simple.Model.Test
import com.ono.simple.Model.mapToDomain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val testApi: TestApi) : ViewModel() {
    val liveData = MutableLiveData<Test>()
    private val compositeDisposable = CompositeDisposable()

    fun get(id: Int) {
        compositeDisposable.add(
            testApi.get(id)
                .subscribeOn(Schedulers.io())
                .map { it.mapToDomain() }
                .subscribe({ liveData.postValue(it) }, { e -> println(e) })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}