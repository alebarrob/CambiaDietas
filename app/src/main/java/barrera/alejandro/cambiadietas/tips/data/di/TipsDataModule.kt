package barrera.alejandro.cambiadietas.tips.data.di

import barrera.alejandro.cambiadietas.core.data.database.CambiaDietasRoomDataBase
import barrera.alejandro.cambiadietas.tips.data.dao.TipDao
import barrera.alejandro.cambiadietas.tips.data.repository.TipRepositoryImpl
import barrera.alejandro.cambiadietas.tips.domain.repository.TipsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TipsDataModule {
    @Singleton
    @Provides
    fun provideTipDao(dataBase: CambiaDietasRoomDataBase): TipDao {
        return dataBase.tipDao()
    }

    @Singleton
    @Provides
    fun provideTipsRepository(tipDao: TipDao): TipsRepository {
        return TipRepositoryImpl(tipDao)
    }
}