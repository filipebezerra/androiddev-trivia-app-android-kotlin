package dev.filipebezerra.android.androiddevtrivia.ui.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.androiddevtrivia.databinding.TitleScreenBinding
import dev.filipebezerra.android.androiddevtrivia.ui.util.event.EventObserver
import dev.filipebezerra.android.androiddevtrivia.ui.title.TitleScreenDirections.Companion.actionTitleToGame as toGame

class TitleScreen : Fragment() {

    private val titleViewModel: TitleViewModel by viewModels()

    private lateinit var viewBinding: TitleScreenBinding

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = TitleScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = titleViewModel
            lifecycleOwner = viewLifecycleOwner
            observeUi()
        }
        .root

    private fun observeUi() {
        titleViewModel.navigateToGame.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(toGame())
        })
    }
}