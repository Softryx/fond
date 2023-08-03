package opnutz.eu.fond.data.db

import androidx.room.Dao
import androidx.room.Insert
import opnutz.eu.fond.data.vo.Operation

@Dao
interface OperationDao {

    @Insert
    suspend fun insertOperation(operation: Operation)
}