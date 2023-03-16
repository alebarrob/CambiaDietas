package barrera.alejandro.cambiadietas.change_diet.domain.di

import barrera.alejandro.cambiadietas.change_diet.domain.use_case.ChangeDietUseCases
import barrera.alejandro.cambiadietas.change_diet.domain.use_case.GetAlternativeFoodAmount
import barrera.alejandro.cambiadietas.change_diet.domain.use_case.GetFoodByName
import barrera.alejandro.cambiadietas.change_diet.domain.use_case.ValidateFoodAmount
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
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
            getFoodByName = GetFoodByName(foodRepository),
            validateFoodAmount = ValidateFoodAmount(),
            getAlternativeFoodAmount = GetAlternativeFoodAmount()
        )
    }
}