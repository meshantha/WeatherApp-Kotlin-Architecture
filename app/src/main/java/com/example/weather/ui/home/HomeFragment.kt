package com.example.weather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.bases.BaseApplication
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.di.view_model.AppViewModelFactory
import com.example.weather.ui.home.adapter.HomeWeatherAdapter
import java.lang.IllegalArgumentException
import javax.inject.Inject

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    private var component: HomeComponent? = null

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    lateinit var viewModel: HomeViewModel

    private val adapter by lazy { HomeWeatherAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.applicationContext.let {
            if (it is BaseApplication) {
                component = HomeComponent.Initializer.init(it.component())
                component?.inject(this)

                viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
            } else {
                throw IllegalArgumentException("Require BaseApplication instance to perform dependency injection")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setLifeCycle()

        viewModel.errorLiveData.observeForever {
            if (!it.isNullOrBlank()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.viewDataLiveData.observeForever {
            adapter.setData(it)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.recyclerHome) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = this@HomeFragment.adapter
        }
    }

    private fun setLifeCycle() {
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        component = null
        super.onDestroy()
    }
}