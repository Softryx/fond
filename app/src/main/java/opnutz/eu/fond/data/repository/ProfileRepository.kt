package opnutz.eu.fond.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.transform
import opnutz.eu.fond.data.FondDataStore
import opnutz.eu.fond.data.db.ProfileDao
import opnutz.eu.fond.data.vo.Profile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao, private val fondDataStore: FondDataStore
) {

    val currentProfile by lazy {
        fondDataStore.watchCurrentProfilId().distinctUntilChanged().transform {
            it?.let {
                profileDao.watchProfileWatchProfileFromId(it).distinctUntilChanged().collect {
                    emit(it)
                }
            } ?: kotlin.run {
                emit(null)
            }
        }.distinctUntilChanged()
    }

    val currentProfileWithAccountsAndOperations by lazy {
        fondDataStore.watchCurrentProfilId().distinctUntilChanged().transform {
            it?.let {
                profileDao.watchProfileWatchProfileWithAccountsAndOperationsFromId(it)
                    .distinctUntilChanged().collect {
                        emit(it)
                    }
            } ?: kotlin.run {
                emit(null)
            }
        }.distinctUntilChanged()
    }

    fun watchProfiles(): Flow<List<Profile>> = profileDao.watchProfiles().distinctUntilChanged()

    suspend fun createProfile(name: String) {
        profileDao.insertProfile(Profile(name = name))
    }

    suspend fun setCurrentProfile(profile: Profile?) {
        fondDataStore.setCurrentProfil(profile)
    }
}