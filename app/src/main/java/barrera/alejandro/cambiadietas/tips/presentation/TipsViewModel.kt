package barrera.alejandro.cambiadietas.tips.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.tips.domain.use_case.TipsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    private val tipsUseCases: TipsUseCases
): ViewModel() {
    var state by mutableStateOf(TipsState())
        private set

    fun onEvent(event: TipsEvent) {
        when (event) {
            is TipsEvent.LoadTips -> {
                viewModelScope.launch {
                    tipsUseCases.getAllTips().collect { tips ->
                        state = state.copy(tips = tips)
                    }
                }
            }
        }
    }
}