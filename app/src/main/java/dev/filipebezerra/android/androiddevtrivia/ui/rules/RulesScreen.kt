package dev.filipebezerra.android.androiddevtrivia.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.androiddevtrivia.databinding.RulesScreenBinding

class RulesScreen : Fragment() {

    private val rulesViewModel: RulesViewModel by viewModels()

    private lateinit var viewBinding: RulesScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = RulesScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = rulesViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}