package opnutz.eu.fond.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)