package barrera.alejandro.cambiadietas.viewmodel

import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.model.entities.Tip
import barrera.alejandro.cambiadietas.model.repositories.TipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TipsScreenViewModel @Inject constructor(
    tipsRepository: TipRepository
): ViewModel() {
    val tips: Flow<List<Tip>> = tipsRepository.allTips
}