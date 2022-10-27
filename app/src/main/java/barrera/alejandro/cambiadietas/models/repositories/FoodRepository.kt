package barrera.alejandro.cambiadietas.models.repositories

import barrera.alejandro.cambiadietas.models.daos.FoodDao
import barrera.alejandro.cambiadietas.models.entities.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodDao: FoodDao
) {
    val categories: Flow<List<String>> get() = foodDao.getCategories()

    fun getFoodByName(name: String): Flow<Food> = foodDao.getFoodByName(name)
    fun getFoodByCategory(category: String): Flow<List<Food>> = foodDao.getFoodByCategory(category)
}