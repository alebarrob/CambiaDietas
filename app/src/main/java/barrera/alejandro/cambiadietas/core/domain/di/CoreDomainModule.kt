package barrera.alejandro.cambiadietas.core.domain.di

import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import barrera.alejandro.cambiadietas.core.domain.use_case.CoreUseCases
import barrera.alejandro.cambiadietas.core.domain.use_case.GetAllFoodCategories
import barrera.alejandro.cambiadietas.core.domain.use_case.GetDrawableId
import barrera.alejandro.cambiadietas.core.domain.use_case.GetFoodByCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CoreDomainModule {
    @ViewModelScoped
    @Provides
    fun provideCoreUseCases(
        foodRepository: FoodRepository
    ): CoreUseCases {
        return CoreUseCases(
            getDrawableId = GetDrawableId(),
            getAllFoodCategories = GetAllFoodCategories(foodRepository),
            getFoodByCategory = GetFoodByCategory(foodRepository)
        )
    }
}