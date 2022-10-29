package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.models.entities.Tip
import barrera.alejandro.cambiadietas.models.repositories.TipRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TipsScreenViewModel @Inject constructor(
    tipsRepository: TipRepositoryImpl
): ViewModel() {
    val tips: Flow<List<Tip>> = tipsRepository.allTips
}