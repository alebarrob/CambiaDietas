package barrera.alejandro.cambiadietas.core.data.repository

import barrera.alejandro.cambiadietas.core.data.dao.FoodDao
import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao
) : FoodRepository {
    override fun getAllFoodCategories(): Flow<List<String>> {
        return foodDao.getAllFoodCategories()
    }

    override fun getFoodByCategory(category: String): Flow<List<Food>> {
        return foodDao.getFoodByCategory(category)
    }

    override fun getFoodByName(name: String): Flow<Food> {
        return foodDao.getFoodByName(name)
    }

}