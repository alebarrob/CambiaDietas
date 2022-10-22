package barrera.alejandro.cambiadietas.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.cambiadietas.model.daos.TipDao
import barrera.alejandro.cambiadietas.model.entities.Tip

@Database(entities = [(Tip::class)], version = 1, exportSchema = true)
abstract class CambiaDietasRoomDataBase : RoomDatabase() {
    abstract fun tipDao() : TipDao
}