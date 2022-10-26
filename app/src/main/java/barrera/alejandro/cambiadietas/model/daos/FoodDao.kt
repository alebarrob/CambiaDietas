package barrera.alejandro.cambiadietas.model.daos

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.cambiadietas.model.entities.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAllFood(): Flow<List<Food>>

    @Query("SELECT DISTINCT category FROM food")
    fun getCategories(): Flow<List<String>>

    @Query("SELECT * FROM food WHERE category = :category")
    fun getFoodByCategory(category: String): Flow<List<Food>>

    @Query("SELECT * FROM food WHERE name = :name LIMIT 1")
    fun getFoodByName(name: String): Flow<Food>
}