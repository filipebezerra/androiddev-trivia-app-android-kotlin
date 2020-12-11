package dev.filipebezerra.android.androiddevtrivia.ui.gamewon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.filipebezerra.android.androiddevtrivia.databinding.GameWonScreenBinding
import dev.filipebezerra.android.androiddevtrivia.ui.util.event.EventObserver
import dev.filipebezerra.android.androiddevtrivia.ui.gamewon.GameWonScreenDirections.Companion.actionGameWonToGame as toGame

class GameWonScreen : Fragment() {

    private val arguments: GameWonScreenArgs by navArgs()

    private val gameWonScreenViewModel: GameWonScreenViewModel by viewModels()

    private lateinit var viewBinding: GameWonScreenBinding

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = GameWonScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = gameWonScreenViewModel
            lifecycleOwner = viewLifecycleOwner
            observeUi()
        }
        .root

    private fun observeUi() {
        gameWonScreenViewModel.navigateToGame.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(toGame())
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            Toast.makeText(
                this,
                "You answered ${arguments.numCorrect} out of ${arguments.numQuestions} questions",
                Toast.LENGTH_SHORT
            ).show(); }
    }
}