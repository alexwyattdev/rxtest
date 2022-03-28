package hu.alexujvary.intertickettest.ui.pages.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.alexujvary.intertickettest.databinding.FragmentThirdPageBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject


class ThirdPageFragment : Fragment() {

    private var _binding: FragmentThirdPageBinding? = null
    private val binding get() = _binding!!

    private var source = PublishSubject.create<String>()
    private val firstSubjectContentString = StringBuilder()
    private val secondSubjectContentString = StringBuilder()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentThirdPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doSubjectTask()
    }

    private fun doSubjectTask() {
        compositeDisposable.add(source.startWithIterable(listOf("1", "2", "3")).subscribe { value ->
            firstSubjectContentString.appendLine(value)
        })

        source.onNext("4")

        compositeDisposable.add(source.subscribe { value ->
            secondSubjectContentString.appendLine(value)
        })

        source.onNext("5")

        source.onComplete()

        binding.tvResults1.text = firstSubjectContentString
        binding.tvResults2.text = secondSubjectContentString
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}
