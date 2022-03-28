package hu.alexujvary.intertickettest.interfaces

import io.reactivex.rxjava3.disposables.Disposable

interface DisposableStore {
    fun add(disposable: Disposable)
    fun dispose()
}