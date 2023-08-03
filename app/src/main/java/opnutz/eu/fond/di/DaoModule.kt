package opnutz.eu.fond.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import opnutz.eu.fond.data.db.FondDatabase

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun profileDao(fondDb: FondDatabase) =
        fondDb.profileDao()

    @Provides
    fun accountDao(fondDb: FondDatabase) =
        fondDb.accountDao()

    @Provides
    fun operationDao(fondDb: FondDatabase) =
        fondDb.operationDao()
}