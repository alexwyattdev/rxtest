package hu.alexujvary.intertickettest.ui.pages.first

import hu.alexujvary.intertickettest.base.BaseContract
import io.reactivex.rxjava3.core.Single

interface FirstPageContract : BaseContract {

    interface View : BaseContract.BaseView {
        fun inputProcessed(output: String)
    }

    interface Presenter {
        fun processInput(input: String)
    }

    interface Model {
        fun processInput(input: String): Single<String>
    }
}