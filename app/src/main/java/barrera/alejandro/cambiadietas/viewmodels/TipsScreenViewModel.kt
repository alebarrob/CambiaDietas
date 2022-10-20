package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.models.Tip

class TipsScreenViewModel: ViewModel() {
    private val _tips = MutableLiveData<List<Tip>>()
    val tips: LiveData<List<Tip>> get() = _tips
}