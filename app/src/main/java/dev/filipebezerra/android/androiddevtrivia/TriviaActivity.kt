package dev.filipebezerra.android.androiddevtrivia

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import dev.filipebezerra.android.androiddevtrivia.databinding.MainActivityBinding
import timber.log.Timber

class TriviaActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<MainActivityBinding>(this, R.layout.main_activity)
            .apply {
                setupActionBar()
                setupAppBar()
            }
            .run {
                navController.addOnDestinationChangedListener { controller, destination, _ ->
                    destination.id.takeIf { destinationId ->
                        (destinationId == R.id.settings_screen)
                            .or(destinationId == R.id.title_screen)
                    }?.run {
                        Timber.d("calling invalidateOptionsMenu() ")
                        invalidateOptionsMenu()
                    }
                    val topLevelDestination =
                        appBarConfiguration.topLevelDestinations.contains(destination.id)
                    drawerLayout.setDrawerLockMode(
                        if (topLevelDestination) LOCK_MODE_UNLOCKED else LOCK_MODE_LOCKED_CLOSED
                    )
                }
            }
    }

    private fun MainActivityBinding.setupActionBar() = setSupportActionBar(toolbar)

    private fun MainActivityBinding.setupAppBar() =
        AppBarConfiguration(
            setOf(
                R.id.title_screen,
                R.id.rules_screen,
                R.id.about_screen,
            ),
            drawerLayout
        ).apply {
            appBarConfiguration = this
        }.let {
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }

    override fun onCreateOptionsMenu(menu: Menu) =
        menuInflater.inflate(R.menu.main, menu).let { true }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.settings_screen)?.isVisible =
            navController.currentDestination?.id != R.id.settings_screen
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}