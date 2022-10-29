package barrera.alejandro.cambiadietas.models.repositories

import barrera.alejandro.cambiadietas.models.entities.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    val categories: Flow<List<String>>

    fun getFoodByName(name: String): Flow<Food>
    fun getFoodByCategory(category: String): Flow<List<Food>>
}