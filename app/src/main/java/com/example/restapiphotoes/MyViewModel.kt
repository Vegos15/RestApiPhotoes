package com.example.restapiphotoes

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restapiphotoes.api.DataSource
import com.example.restapiphotoes.api.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MyViewModel: ViewModel() {

    private var myCompositeDisposable: CompositeDisposable = CompositeDisposable()
    var userList: MutableLiveData<Model> = MutableLiveData()

    fun loadUsers() {

        val disposable: Disposable = DataSource.getUsers(1)
            .subscribeOn(Schedulers.newThread())
            .map {
                Log.d(TAG, "Name is ${it.name}")
                val hashName = DataSource.getUserHash(it.name)
                it.name = hashName
                Log.d(TAG, "HashName is ${it.name}")
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)
        myCompositeDisposable.add(disposable)

    }

    private fun handleResponse(model: Model) {
        Log.d("RESPONSE: ", model.toString())
        userList.value = model
    }

    private fun handleError(error: Throwable) {
        Log.d("ERROR: ", error.message.toString())
    }

    override fun onCleared() {
        myCompositeDisposable.clear()
        super.onCleared()
    }
}