package barrera.alejandro.cambiadietas.categories.domain.category_detail.use_case

import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodByCategory(private val foodRepository: FoodRepository) {
    operator fun invoke(category: String): Flow<List<Food>> {
        return foodRepository.getFoodByCategory(category)
    }
}