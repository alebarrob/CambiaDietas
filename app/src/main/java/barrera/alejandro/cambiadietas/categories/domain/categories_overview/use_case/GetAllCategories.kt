package barrera.alejandro.cambiadietas.categories.domain.categories_overview.use_case

import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategories(private val foodRepository: FoodRepository) {
    operator fun invoke(): Flow<List<String>> {
        return foodRepository.getAllCategories()
    }
}