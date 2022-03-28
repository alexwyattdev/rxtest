package hu.alexujvary.intertickettest.ui.pages.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hu.alexujvary.intertickettest.R
import hu.alexujvary.intertickettest.base.BaseFragment
import hu.alexujvary.intertickettest.databinding.FragmentFirstPageBinding
import hu.alexujvary.intertickettest.di.Injector
import hu.alexujvary.intertickettest.extensions.gone
import hu.alexujvary.intertickettest.extensions.visible
import javax.inject.Inject

class FirstPageFragment : BaseFragment(), FirstPageContract.View {

    @Inject
    lateinit var presenter: FirstPagePresenter

    private var _binding: FragmentFirstPageBinding? = null
    private val binding get() = _binding!!

    override fun injectDependencies() {
        Injector.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFirstPageBinding.inflate(inflater, container, false)
        presenter.view = this
        lifecycle.addObserver(presenter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideProgress()
        binding.btnProcessInput.setOnClickListener {
            presenter.processInput(binding.etInput.text.toString())
        }
    }

    override fun inputProcessed(output: String) {
        if (output.isEmpty()) {
            showError(getString(R.string.invalid_or_empty_text))
        } else {
            binding.tvResults.text = output
        }
    }

    override fun showProgress() {
        binding.progress.visible()
    }

    override fun hideProgress() {
        binding.progress.gone()
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message ?: getString(R.string.general_error), Toast.LENGTH_SHORT).show()
    }
}
