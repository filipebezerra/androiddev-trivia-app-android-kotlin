package dev.filipebezerra.android.androiddevtrivia.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.androiddevtrivia.databinding.AboutScreenBinding

class AboutScreen : Fragment() {

    private val aboutViewModel: AboutViewModel by viewModels()

    private lateinit var viewBinding: AboutScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = AboutScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = aboutViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}