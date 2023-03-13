package barrera.alejandro.cambiadietas.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.cambiadietas.core.data.dao.FoodDao
import barrera.alejandro.cambiadietas.tips.data.dao.TipDao
import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.tips.domain.model.Tip

@Database(entities = [Tip::class, Food::class], version = 1)
abstract class CambiaDietasRoomDataBase : RoomDatabase() {
    abstract fun tipDao() : TipDao
    abstract fun foodDao() : FoodDao
}