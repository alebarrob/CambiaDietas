package barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.model.data.*

class FoodColumnViewModel : ViewModel() {
    private val _foodItems = MutableLiveData<List<FoodDrawableStringAmountTriple>>()
    val foodItems: LiveData<List<FoodDrawableStringAmountTriple>> get() = _foodItems

    fun onFoodItemsChange(foodItems: List<FoodDrawableStringAmountTriple>) {
        _foodItems.value = foodItems
    }
}