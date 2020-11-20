package dev.filipebezerra.android.androiddevtrivia.ui.gameover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.androiddevtrivia.databinding.GameOverScreenBinding

class GameOverScreen : Fragment() {

    private val gameWonScreenViewModel: GameOverScreenViewModel by viewModels()

    private lateinit var viewBinding: GameOverScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = GameOverScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = gameWonScreenViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}