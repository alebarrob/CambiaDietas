package barrera.alejandro.cambiadietas.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.cambiadietas.model.daos.FoodDao
import barrera.alejandro.cambiadietas.model.daos.TipDao
import barrera.alejandro.cambiadietas.model.entities.Food
import barrera.alejandro.cambiadietas.model.entities.Tip

@Database(entities = [Tip::class, Food::class], version = 1)
abstract class CambiaDietasRoomDataBase : RoomDatabase() {
    abstract fun tipDao() : TipDao
    abstract fun foodDao() : FoodDao
}