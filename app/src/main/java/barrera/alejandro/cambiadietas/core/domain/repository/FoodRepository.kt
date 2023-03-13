package barrera.alejandro.cambiadietas.core.domain.repository

import barrera.alejandro.cambiadietas.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAllCategories(): Flow<List<String>>

    fun getFoodByCategory(category: String): Flow<List<Food>>

    fun getFoodByName(name: String): Flow<Food>
}