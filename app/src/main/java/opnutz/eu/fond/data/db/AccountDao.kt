package opnutz.eu.fond.data.db

import androidx.room.Dao
import androidx.room.Insert
import opnutz.eu.fond.data.vo.Account

@Dao
interface AccountDao {

    @Insert
    suspend fun insertAccount(account: Account)
}