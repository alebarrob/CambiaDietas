package barrera.alejandro.cambiadietas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import barrera.alejandro.cambiadietas.model.entities.Tip
import barrera.alejandro.cambiadietas.model.daos.TipDao

@Database(
    entities = [(Tip::class)],
    version = 1
)
abstract class CambiaDietasRoomDataBase : RoomDatabase() {
    abstract fun tipDao() : TipDao

    companion object {
        private var INSTANCE: CambiaDietasRoomDataBase? = null

        fun getInstance(context: Context): CambiaDietasRoomDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CambiaDietasRoomDataBase::class.java,
                        "cambiadietas_database"
                    ).fallbackToDestructiveMigration()
                        .createFromAsset("database/cambiadietas_database.db")
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}