package barrera.alejandro.cambiadietas.change_diet.data.domain.di

import barrera.alejandro.cambiadietas.change_diet.data.domain.use_case.ChangeDietUseCases
import barrera.alejandro.cambiadietas.change_diet.data.domain.use_case.GetFoodByName
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
object ChangeDietDomainModule {
    @ViewModelScoped
    @Provides
    fun provideChangeDietUseCases(
        foodRepository: FoodRepository
    ): ChangeDietUseCases {
        return ChangeDietUseCases(
            getFoodByName = GetFoodByName(foodRepository)
        )
    }
}