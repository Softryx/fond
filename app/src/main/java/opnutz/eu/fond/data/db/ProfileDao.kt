package opnutz.eu.fond.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import opnutz.eu.fond.data.vo.Profile

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    fun watchProfiles(): Flow<List<Profile>>

    @Insert
    suspend fun insertProfile(profile: Profile)
}