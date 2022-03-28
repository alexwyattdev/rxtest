package hu.alexujvary.intertickettest.base

import hu.alexujvary.intertickettest.interfaces.DisposableStore
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BasePresenter<T : BaseContract.BaseView> : BaseContract.Presenter, DisposableStore {

    private var disposables = CompositeDisposable()
    private var viewWeakReference: WeakReference<T>? = null

    var view: T?
        set(value) {

            value?.let {

                viewWeakReference = WeakReference(it)
            }
        }
        get() {

            return viewWeakReference?.get()
        }

    override fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun dispose() {
        disposables.dispose()
        disposables = CompositeDisposable()
    }
}