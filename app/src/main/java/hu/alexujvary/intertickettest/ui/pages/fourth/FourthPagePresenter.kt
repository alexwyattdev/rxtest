package hu.alexujvary.intertickettest.ui.pages.fourth

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import hu.alexujvary.intertickettest.base.BasePresenter
import hu.alexujvary.intertickettest.extensions.add
import hu.alexujvary.intertickettest.model.CountryResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class FourthPagePresenter @Inject constructor(private val model: FourthPageModel) : FourthPageContract.Presenter, BasePresenter<FourthPageContract.View>(), DefaultLifecycleObserver {

    private val publishSubject: PublishSubject<Notification<List<CountryResponse>>> = PublishSubject.create()

    override fun loadCountries() {
        view?.showProgress()
        model.loadCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                response.sortedBy { it.name }
            }
            .subscribe({ response ->
                publishSubject.onNext(Notification.createOnNext(response))
            }, { t: Throwable ->
                publishSubject.onNext(Notification.createOnError(t))
            }).add(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        publishSubject.subscribe {
            view?.hideProgress()
            if (it.isOnError || it.value.isNullOrEmpty()) {
                Timber.e(it.error)
                view?.showError(it.error?.message)
            } else {
                view?.countriesLoaded(it.value!!)
            }
        }.add(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dispose()
    }
}