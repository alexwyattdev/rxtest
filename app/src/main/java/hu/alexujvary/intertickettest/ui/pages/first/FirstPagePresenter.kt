package hu.alexujvary.intertickettest.ui.pages.first

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import hu.alexujvary.intertickettest.base.BasePresenter
import hu.alexujvary.intertickettest.extensions.add
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FirstPagePresenter @Inject constructor(private val model: FirstPageModel) : FirstPageContract.Presenter, BasePresenter<FirstPageContract.View>(), DefaultLifecycleObserver {

    override fun processInput(input: String) {
        view?.showProgress()
        model.processInput(input)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view?.hideProgress()
                view?.inputProcessed(response)
            }, { t: Throwable ->
                Timber.e(t)
                view?.hideProgress()
                view?.showError(t.message)
            }).add(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dispose()
    }
}