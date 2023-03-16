package barrera.alejandro.cambiadietas.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.cambiadietas.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT DISTINCT category FROM food")
    fun getAllFoodCategories(): Flow<List<String>>

    @Query("SELECT * FROM food WHERE category = :category")
    fun getFoodByCategory(category: String): Flow<List<Food>>

    @Query("SELECT * FROM food WHERE name = :name")
    fun getFoodByName(name: String): Flow<Food>
}