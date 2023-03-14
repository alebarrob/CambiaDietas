package barrera.alejandro.cambiadietas.change_diet.data.domain.use_case

import barrera.alejandro.cambiadietas.core.domain.model.Food
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodByName(private val foodRepository: FoodRepository) {
    operator fun invoke(name: String): Flow<Food> {
        return foodRepository.getFoodByName(name)
    }
}