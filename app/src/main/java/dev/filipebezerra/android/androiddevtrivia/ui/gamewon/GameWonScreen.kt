package dev.filipebezerra.android.androiddevtrivia.ui.gamewon

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.filipebezerra.android.androiddevtrivia.R
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
            setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        activity?.let {
            menu.findItem(R.id.share)?.apply {
                isVisible = createShareIntent()?.resolveActivity(it.packageManager) != null
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.share -> {
                shareAccomplishment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun shareAccomplishment() {
        startActivity(createShareIntent())
    }

    private fun createShareIntent(): Intent? =
        activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setType("text/plain")
                .setText(getString(R.string.share_success_text, arguments.numCorrect, arguments.numQuestions))
                .intent
        }
}