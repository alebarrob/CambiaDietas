package barrera.alejandro.cambiadietas.models.daos

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.cambiadietas.models.entities.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT DISTINCT category FROM food")
    fun getCategories(): Flow<List<String>>

    @Query("SELECT * FROM food WHERE category = :category")
    fun getFoodByCategory(category: String): Flow<List<Food>>

    @Query("SELECT * FROM food WHERE name = :name LIMIT 1")
    fun getFoodByName(name: String): Flow<Food>
}