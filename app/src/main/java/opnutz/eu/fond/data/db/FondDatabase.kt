package opnutz.eu.fond.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import opnutz.eu.fond.data.vo.Profile

@Database(
    entities = [
        Profile::class
    ],
    version = 1
)
abstract class FondDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {
        fun databaseBuilder(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FondDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()

        private val DATABASE_NAME = "fond.db"
    }
}
