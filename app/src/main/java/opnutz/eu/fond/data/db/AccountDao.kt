package opnutz.eu.fond.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import opnutz.eu.fond.data.vo.Account
import opnutz.eu.fond.data.vo.relations.AccountWithOperations

@Dao
interface AccountDao {

    @Insert
    suspend fun insertAccount(account: Account)

    @Query("SELECT * FROM account WHERE id = :id")
    fun watchAccountWithOperationsFromId(id: Long): Flow<AccountWithOperations?>
}