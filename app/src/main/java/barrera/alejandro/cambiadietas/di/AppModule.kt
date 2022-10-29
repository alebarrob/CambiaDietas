package barrera.alejandro.cambiadietas.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.cambiadietas.models.daos.FoodDao
import barrera.alejandro.cambiadietas.models.database.CambiaDietasRoomDataBase
import barrera.alejandro.cambiadietas.models.repositories.FoodRepository
import barrera.alejandro.cambiadietas.models.repositories.FoodRepositoryImpl
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
    ) = Room.databaseBuilder(
        context,
        CambiaDietasRoomDataBase::class.java,
        "cambiadietas_database"
    ).fallbackToDestructiveMigration()
        .createFromAsset("database/cambiadietas_database.db")
        .build()

    @Singleton
    @Provides
    fun provideTipDao(dataBase: CambiaDietasRoomDataBase) = dataBase.tipDao()

    @Singleton
    @Provides
    fun provideFoodDao(dataBase: CambiaDietasRoomDataBase) = dataBase.foodDao()

    @Singleton
    @Provides
    fun foodRepositoryImpl(foodDao: FoodDao): FoodRepository = FoodRepositoryImpl(foodDao)
}