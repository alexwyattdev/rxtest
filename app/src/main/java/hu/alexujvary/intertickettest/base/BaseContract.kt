package hu.alexujvary.intertickettest.base

interface BaseContract {

    interface BaseView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
    }

    interface Model {

    }

    interface Presenter {
    }
}