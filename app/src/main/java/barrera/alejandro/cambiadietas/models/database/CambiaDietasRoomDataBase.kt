package barrera.alejandro.cambiadietas.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.cambiadietas.models.daos.FoodDao
import barrera.alejandro.cambiadietas.models.daos.TipDao
import barrera.alejandro.cambiadietas.models.entities.Food
import barrera.alejandro.cambiadietas.models.entities.Tip

@Database(entities = [Tip::class, Food::class], version = 1)
abstract class CambiaDietasRoomDataBase : RoomDatabase() {
    abstract fun tipDao() : TipDao
    abstract fun foodDao() : FoodDao
}