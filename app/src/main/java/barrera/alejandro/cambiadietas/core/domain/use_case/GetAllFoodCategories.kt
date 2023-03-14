package barrera.alejandro.cambiadietas.core.domain.use_case

import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetAllFoodCategories(private val foodRepository: FoodRepository) {
    operator fun invoke(): Flow<List<String>> {
        return foodRepository.getAllFoodCategories()
    }
}