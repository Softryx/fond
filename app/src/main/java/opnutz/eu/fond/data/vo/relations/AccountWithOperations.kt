package opnutz.eu.fond.data.vo.relations

import androidx.room.Embedded
import androidx.room.Relation
import opnutz.eu.fond.data.vo.Account
import opnutz.eu.fond.data.vo.Operation

data class AccountWithOperations(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "id",
        entityColumn = "accountId"
    )
    val operations: List<Operation>
) {
    fun accountAmount(): Double = operations.sumOf { it.amount }
}