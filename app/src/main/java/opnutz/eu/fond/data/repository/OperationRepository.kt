package opnutz.eu.fond.data.repository

import opnutz.eu.fond.data.db.OperationDao
import opnutz.eu.fond.data.vo.Operation
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OperationRepository @Inject constructor(
    private val operationDao: OperationDao
) {

    suspend fun createOperation(accountId: Long, name: String, amount: Double, date: String) {
        operationDao.insertOperation(
            Operation(
                accountId = accountId,
                name = name,
                amount = amount,
                date = date
            )
        )
    }

}