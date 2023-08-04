package opnutz.eu.fond.data.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Account::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("accountId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE

    )]
)
data class Operation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val accountId: Long,
    val amount: Double,
    val date: String,
    val name: String
)