package opnutz.eu.fond.data.vo.relations

import androidx.room.Embedded
import androidx.room.Relation
import opnutz.eu.fond.data.vo.Account
import opnutz.eu.fond.data.vo.Profile


data class ProfileWithAccountsAndOperations(
    @Embedded val profile: Profile,
    @Relation(
        entity = Account::class,
        parentColumn = "id",
        entityColumn = "profileId"
    )
    val accounts: List<AccountWithOperations>
) {
    fun profilAmount(): Double = accounts.sumOf { it.accountAmount() }
}