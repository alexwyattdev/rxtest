package hu.alexujvary.intertickettest.ui.pages.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.alexujvary.intertickettest.databinding.FragmentSecondPageBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.ReplaySubject

class SecondPageFragment : Fragment() {

    private var _binding: FragmentSecondPageBinding? = null
    private val binding get() = _binding!!

    private var source = ReplaySubject.create<String>()
    private val firstSubjectContentString = StringBuilder()
    private val secondSubjectContentString = StringBuilder()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doSubjectTask()
    }

    private fun doSubjectTask() {
        source.onNext("1")
        source.onNext("2")
        source.onNext("3")

        compositeDisposable.add(source.subscribe { value ->
            firstSubjectContentString.appendLine(value)
        })

        source.onNext("4")

        compositeDisposable.add(source.subscribe { value ->
            secondSubjectContentString.appendLine(value)
        })

        source.onComplete()

        binding.tvResults1.text = firstSubjectContentString
        binding.tvResults2.text = secondSubjectContentString
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}
