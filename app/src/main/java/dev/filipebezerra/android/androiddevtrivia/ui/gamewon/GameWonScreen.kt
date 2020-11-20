package dev.filipebezerra.android.androiddevtrivia.ui.gamewon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.androiddevtrivia.databinding.GameWonScreenBinding

class GameWonScreen : Fragment() {

    private val gameWonScreenViewModel: GameWonScreenViewModel by viewModels()

    private lateinit var viewBinding: GameWonScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = GameWonScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = gameWonScreenViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}