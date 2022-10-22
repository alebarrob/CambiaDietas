package barrera.alejandro.cambiadietas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartScreenViewModel : ViewModel() {
    private val _expanded = MutableLiveData<Boolean>()
    val expanded: LiveData<Boolean> get() = _expanded

    fun onExpandedChange(expanded: Boolean) {
        _expanded.value = expanded
    }
}