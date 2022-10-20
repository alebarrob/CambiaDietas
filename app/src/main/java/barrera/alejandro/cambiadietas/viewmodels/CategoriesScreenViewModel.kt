package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesScreenViewModel: ViewModel() {
    private val _foodCategoriesId = MutableLiveData<List<Int>>()
    val foodCategoriesId: LiveData<List<Int>> get() = _foodCategoriesId
}