package hu.alexujvary.intertickettest.extensions

import hu.alexujvary.intertickettest.interfaces.DisposableStore
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.add(disposableStore: DisposableStore) = disposableStore.add(this)