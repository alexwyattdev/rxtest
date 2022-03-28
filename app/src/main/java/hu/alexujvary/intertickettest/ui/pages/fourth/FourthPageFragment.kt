package hu.alexujvary.intertickettest.ui.pages.fourth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hu.alexujvary.intertickettest.R
import hu.alexujvary.intertickettest.base.BaseFragment
import hu.alexujvary.intertickettest.databinding.FragmentFourthPageBinding
import hu.alexujvary.intertickettest.di.Injector
import hu.alexujvary.intertickettest.extensions.gone
import hu.alexujvary.intertickettest.extensions.visible
import hu.alexujvary.intertickettest.model.CountryResponse
import javax.inject.Inject

class FourthPageFragment : BaseFragment(), FourthPageContract.View {

    @Inject
    lateinit var presenter: FourthPagePresenter

    private var _binding: FragmentFourthPageBinding? = null
    private val binding get() = _binding!!
    private val adapter = CountriesAdapter()

    override fun injectDependencies() {
        Injector.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFourthPageBinding.inflate(inflater, container, false)
        presenter.view = this
        lifecycle.addObserver(presenter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideProgress()
        binding.rvCountries.adapter = adapter
        binding.btnLoadCountries.setOnClickListener {
            presenter.loadCountries()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun countriesLoaded(countries: List<CountryResponse>) {
        adapter.update(countries)
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