package barrera.alejandro.cambiadietas.core.data.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.cambiadietas.core.data.dao.FoodDao
import barrera.alejandro.cambiadietas.core.data.database.CambiaDietasRoomDataBase
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import barrera.alejandro.cambiadietas.core.data.repository.FoodRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): CambiaDietasRoomDataBase {
        return Room.databaseBuilder(
            context,
            CambiaDietasRoomDataBase::class.java,
            "cambiadietas_database"
        ).fallbackToDestructiveMigration()
            .createFromAsset("database/cambiadietas_database.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideFoodDao(dataBase: CambiaDietasRoomDataBase): FoodDao {
        return dataBase.foodDao()
    }

    @Singleton
    @Provides
    fun provideFoodRepository(foodDao: FoodDao): FoodRepository {
        return FoodRepositoryImpl(foodDao)
    }
}