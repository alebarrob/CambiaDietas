package barrera.alejandro.cambiadietas.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.cambiadietas.model.database.CambiaDietasRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CambiaDietasRoomDataBase::class.java,
        "cambiadietas_database"
    ).createFromAsset("database/cambiadietas_database.db").build()

    @Singleton
    @Provides
    fun provideTipDao(dataBase: CambiaDietasRoomDataBase) = dataBase.tipDao()
}