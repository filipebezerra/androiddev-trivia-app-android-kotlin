package dev.filipebezerra.android.androiddevtrivia.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dev.filipebezerra.android.androiddevtrivia.R

class SettingsScreen : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.invalidateOptionsMenu()
    }
}