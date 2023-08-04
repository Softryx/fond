package opnutz.eu.fond.data.repository

import kotlinx.coroutines.flow.distinctUntilChanged
import opnutz.eu.fond.data.db.AccountDao
import opnutz.eu.fond.data.vo.Account
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {

    fun accountWitOperation(id: Long) =
        accountDao.watchAccountWithOperationsFromId(id).distinctUntilChanged()

    suspend fun createAccount(profileId: Long, name: String, ceiling: Double) {
        accountDao.insertAccount(
            Account(
                profileId = profileId,
                name = name,
                ceiling = ceiling
            )
        )
    }

}