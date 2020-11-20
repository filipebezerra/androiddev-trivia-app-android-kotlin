package dev.filipebezerra.android.androiddevtrivia.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.androiddevtrivia.databinding.GameScreenBinding
import dev.filipebezerra.android.androiddevtrivia.ui.game.GameScreenDirections.Companion.actionGameToGameWon as toGameWon
import dev.filipebezerra.android.androiddevtrivia.ui.game.GameScreenDirections.Companion.actionGameToGameOver as toGameOver

class GameScreen : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = GameScreenBinding.inflate(inflater)
        .apply {
            viewModel = gameViewModel
            lifecycleOwner = viewLifecycleOwner
            observeUi()
        }
        .root

    private fun observeUi() {
        gameViewModel.navigateToGameWon.observe(viewLifecycleOwner) {
            navController.navigate(toGameWon())
        }

        gameViewModel.navigateToGameOver.observe(viewLifecycleOwner) {
            navController.navigate(toGameOver())
        }
    }
}