package barrera.alejandro.cambiadietas.tips.domain.di

import barrera.alejandro.cambiadietas.tips.domain.repository.TipsRepository
import barrera.alejandro.cambiadietas.tips.domain.use_case.GetAllTips
import barrera.alejandro.cambiadietas.tips.domain.use_case.TipsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TipsDomainModule {
    @ViewModelScoped
    @Provides
    fun provideTipsUseCases(
        tipsRepository: TipsRepository
    ): TipsUseCases {
        return TipsUseCases(
            getAllTips = GetAllTips(tipsRepository)
        )
    }
}