package barrera.alejandro.cambiadietas.change_diet.presentation.select_alternative_food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.cambiadietas.change_diet.domain.use_case.ChangeDietUseCases
import barrera.alejandro.cambiadietas.change_diet.domain.use_case.ValidateFoodAmount
import barrera.alejandro.cambiadietas.core.domain.use_case.CoreUseCases
import barrera.alejandro.cambiadietas.core.util.UiEvent
import barrera.alejandro.cambiadietas.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import barrera.alejandro.cambiadietas.R

@HiltViewModel
class SelectAlternativeFoodViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val changeDietUseCases: ChangeDietUseCases,
    private val coreUseCases: CoreUseCases
): ViewModel() {
    var state by mutableStateOf(
        SelectAlternativeFoodState(
            selectedFoodName = savedStateHandle.get<String>("foodName")!!,
            selectedFoodCategory = savedStateHandle.get<String>("foodCategory")!!
        )
    )
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SelectAlternativeFoodEvent) {
        when (event) {
            is SelectAlternativeFoodEvent.LoadSelectedFood -> {
                viewModelScope.launch {
                    changeDietUseCases.getFoodByName(state.selectedFoodName).collect { food ->
                        state = state.copy(selectedFood = food)
                    }
                }
            }
            is SelectAlternativeFoodEvent.LoadFoodsByCategory -> {
                viewModelScope.launch {
                    coreUseCases.getFoodByCategory(
                        state.selectedFoodCategory
                    ).collect { foodsByCategory ->
                        state = state.copy(foodsByCategory = foodsByCategory)
                    }
                }
            }
            is SelectAlternativeFoodEvent.OnSelectedFoodAmountChange -> {
                val result = changeDietUseCases
                    .validateFoodAmount(foodAmount = event.selectedFoodAmount)

                when (result) {
                    is ValidateFoodAmount.Result.Success -> {
                        state = state.copy(
                            selectedFoodAmount = event.selectedFoodAmount,
                            alternativeFoodAmount = changeDietUseCases
                                .getAlternativeFoodAmount(
                                    selectedFood = state.selectedFood,
                                    alternativeFood = state.alternativeFood,
                                    selectedFoodAmount = event.selectedFoodAmount
                                )
                        )
                    }
                    is ValidateFoodAmount.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.food_amount_error)
                                )
                            )
                        }
                    }
                }
            }
            is SelectAlternativeFoodEvent.OnFoodClick -> {
                viewModelScope.launch {
                    changeDietUseCases.getFoodByName(event.foodName).collect { food ->
                        state = state.copy(
                            alternativeFood = food,
                            alternativeFoodAmount = changeDietUseCases
                                .getAlternativeFoodAmount(
                                    selectedFood = state.selectedFood,
                                    alternativeFood = food,
                                    selectedFoodAmount = state.selectedFoodAmount
                                )
                        )
                    }
                }
            }
        }
    }

    fun getDrawableId(
        foodCategory: String,
        foodName: String
    ): Int {
        return coreUseCases.getDrawableId(
            foodCategory = foodCategory,
            foodName = foodName
        )
    }
}