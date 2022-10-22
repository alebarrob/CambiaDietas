package barrera.alejandro.cambiadietas.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class StartScreenViewModel : ViewModel() {
    private val _expanded = MutableStateFlow(false)
    val expanded: Flow<Boolean> get() = _expanded

    fun onExpandedChange(expanded: Boolean) {
        _expanded.value = expanded
    }
}