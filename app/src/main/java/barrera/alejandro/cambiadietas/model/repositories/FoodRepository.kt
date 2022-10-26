package barrera.alejandro.cambiadietas.model.repositories

import barrera.alejandro.cambiadietas.model.daos.FoodDao
import barrera.alejandro.cambiadietas.model.entities.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodDao: FoodDao
) {
    val food: Flow<List<Food>> get() = foodDao.getAllFood()
    val categories: Flow<List<String>> get() = foodDao.getCategories()

    fun getFoodByName(name: String): Flow<Food> = foodDao.getFoodByName(name)
    fun getFoodByCategory(category: String): Flow<List<Food>> = foodDao.getFoodByCategory(category)
}