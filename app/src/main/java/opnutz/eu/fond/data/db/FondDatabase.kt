package opnutz.eu.fond.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import opnutz.eu.fond.data.vo.Account
import opnutz.eu.fond.data.vo.Operation
import opnutz.eu.fond.data.vo.Profile

@Database(
    entities = [
        Profile::class,
        Account::class,
        Operation::class
    ], version = 3
)
abstract class FondDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    abstract fun accountDao(): AccountDao

    abstract fun operationDao(): OperationDao

    companion object {
        fun databaseBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext, FondDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration()

        private val DATABASE_NAME = "fond.db"
    }
}
