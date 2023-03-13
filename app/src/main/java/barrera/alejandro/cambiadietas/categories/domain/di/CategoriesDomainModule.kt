package barrera.alejandro.cambiadietas.categories.domain.di

import barrera.alejandro.cambiadietas.categories.domain.categories_overview.use_case.CategoriesOverviewUseCases
import barrera.alejandro.cambiadietas.categories.domain.categories_overview.use_case.GetAllCategories
import barrera.alejandro.cambiadietas.categories.domain.category_detail.use_case.CategoryDetailUseCases
import barrera.alejandro.cambiadietas.categories.domain.category_detail.use_case.GetFoodByCategory
import barrera.alejandro.cambiadietas.core.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CategoriesDomainModule {
    @ViewModelScoped
    @Provides
    fun provideCategoriesOverviewUseCases(
        foodRepository: FoodRepository
    ): CategoriesOverviewUseCases {
        return CategoriesOverviewUseCases(
            getAllCategories = GetAllCategories(foodRepository)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideCategoryDetailUseCases(
        foodRepository: FoodRepository
    ): CategoryDetailUseCases {
        return CategoryDetailUseCases(
            getFoodByCategory = GetFoodByCategory(foodRepository)
        )
    }
}