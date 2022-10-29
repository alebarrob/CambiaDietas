package barrera.alejandro.cambiadietas.models.repositories

import barrera.alejandro.cambiadietas.models.entities.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFoodRepository : FoodRepository {
    private val food = mutableListOf<Food>()

    override val categories: Flow<List<String>>
        get() = flow { listOf<String>() }

    override fun getFoodByName(name: String): Flow<Food> {
        return flow { emit(food.find { it.name == name }!!) }
    }

    override fun getFoodByCategory(category: String): Flow<List<Food>> {
        return flow { emit(food.filter { it.category == category }) }
    }

}