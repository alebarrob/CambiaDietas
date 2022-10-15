package barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CambiaDietasBottomBarViewModel : ViewModel() {
    private val _startButtonIsSelected = MutableLiveData<Boolean>()
    val startButtonIsSelected: LiveData<Boolean> get() = _startButtonIsSelected

    private val _categoriesButtonIsSelected = MutableLiveData<Boolean>()
    val categoriesButtonIsSelected: LiveData<Boolean> get() = _categoriesButtonIsSelected

    private val _advicesButtonIsSelected = MutableLiveData<Boolean>()
    val advicesButtonIsSelected: LiveData<Boolean> get() = _advicesButtonIsSelected

    fun onStartButtonIsSelectedChange(startButtonIsSelected: Boolean) {
        _startButtonIsSelected.value = startButtonIsSelected
    }

    fun onCategoriesButtonIsSelectedChange(categoriesButtonIsSelected: Boolean) {
        _categoriesButtonIsSelected.value = categoriesButtonIsSelected
    }

    fun onAdvicesButtonIsSelectedChange(advicesButtonIsSelected: Boolean) {
        _advicesButtonIsSelected.value = advicesButtonIsSelected
    }
}