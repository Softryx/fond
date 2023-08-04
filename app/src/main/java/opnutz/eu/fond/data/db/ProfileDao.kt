package opnutz.eu.fond.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import opnutz.eu.fond.data.vo.Profile
import opnutz.eu.fond.data.vo.relations.ProfileWithAccountsAndOperations

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    fun watchProfiles(): Flow<List<Profile>>

    @Query("SELECT * FROM profile WHERE id = :id")
    fun watchProfileWatchProfileFromId(id: Long): Flow<Profile?>

    @Query("SELECT * FROM profile WHERE id = :id")
    fun watchProfileWithAccountsAndOperationsFromId(id: Long): Flow<ProfileWithAccountsAndOperations?>


    @Insert
    suspend fun insertProfile(profile: Profile)
}