package barrera.alejandro.cambiadietas.models.repositories

import barrera.alejandro.cambiadietas.models.daos.FoodDao
import barrera.alejandro.cambiadietas.models.entities.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao
) : FoodRepository {
    override val categories: Flow<List<String>> get() = foodDao.getCategories()

    override fun getFoodByName(name: String): Flow<Food> = foodDao.getFoodByName(name)
    override fun getFoodByCategory(category: String): Flow<List<Food>> = foodDao.getFoodByCategory(category)
}