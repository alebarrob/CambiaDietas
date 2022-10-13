package barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.model.data.FoodDrawableStringAmountTriple

class CambiaDietasAppViewModel : ViewModel() {
    private val _screen = MutableLiveData<String>()
    val screen: LiveData<String> get() = _screen

    private val _foodCategory = MutableLiveData<String>()
    val foodCategory: LiveData<String> get() = _foodCategory

    private val _food = MutableLiveData<FoodDrawableStringAmountTriple>()
    val food: LiveData<FoodDrawableStringAmountTriple> get() = _food

    fun onScreenChange(screen: String) {
        _screen.value = screen
    }

    fun onFoodCategoryChange(foodCategory: String) {
        _foodCategory.value = foodCategory
    }

    fun onFoodChange(food: FoodDrawableStringAmountTriple) {
        _food.value = food
    }
}