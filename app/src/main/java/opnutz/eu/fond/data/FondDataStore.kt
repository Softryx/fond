package opnutz.eu.fond.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import opnutz.eu.fond.data.vo.Profile
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "fond_datastore")

class FondDataStore @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.dataStore

    fun watchCurrentProfilId(): Flow<Long?> =
        dataStore.data.map { prefs ->
            prefs[PROFIL_ID]
        }


    suspend fun setCurrentProfil(profile: Profile?) {
        dataStore.edit { prefs ->
            if (profile == null) {
                prefs.remove(PROFIL_ID)
            } else {
                prefs[PROFIL_ID] = profile.id
            }
        }
    }

    companion object {
        private val PROFIL_ID = longPreferencesKey("PROFIL_ID")
    }
}