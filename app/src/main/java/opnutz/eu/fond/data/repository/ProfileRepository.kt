package opnutz.eu.fond.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import opnutz.eu.fond.data.db.ProfileDao
import opnutz.eu.fond.data.vo.Profile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao
) {

    fun watchProfiles(): Flow<List<Profile>> =
        profileDao.watchProfiles().distinctUntilChanged()

    suspend fun createProfile(name: String) {
        profileDao.insertProfile(Profile(name = name))
    }
}