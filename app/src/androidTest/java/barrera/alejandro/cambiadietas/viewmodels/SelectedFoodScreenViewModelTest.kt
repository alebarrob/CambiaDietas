package barrera.alejandro.cambiadietas.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.test.filters.SmallTest
import barrera.alejandro.cambiadietas.models.entities.Food
import barrera.alejandro.cambiadietas.models.repositories.FakeFoodRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@SmallTest
class SelectedFoodScreenViewModelTest {
    private lateinit var selectedFoodScreenViewModel: SelectedFoodScreenViewModel
    private lateinit var fakeSavedStateHandle: SavedStateHandle
    private lateinit var fakeFoodRepository: FakeFoodRepository

    @Before
    fun setUp() {
        fakeSavedStateHandle = SavedStateHandle().apply {
            set(key = "selectedFoodName", value = "Coco")
            set(key = "selectedCategory", value = "Grasas")
        }
        fakeFoodRepository = FakeFoodRepository()
        selectedFoodScreenViewModel = SelectedFoodScreenViewModel(
            fakeSavedStateHandle,
            fakeFoodRepository
        )
    }

    @Test
    fun calculateFoodAmountEquivalenceTest() {
        val avocado = Food(
            id = 49,
            drawableName = "fats_avocado",
            name = "Aguacate",
            equivalentAmountForCalculations = 50.0,
            category = "Grasas",
            unit = "gr."
        )
        val coconut = Food(
            id = 51,
            drawableName = "fats_coconut",
            name = "Coco",
            equivalentAmountForCalculations = 30.0,
            category = "Grasas",
            unit = "gr."
        )
        val alternativeFoodAmountExpected = "12.00"
        val alternativeFoodAmountResult = selectedFoodScreenViewModel.calculateFoodAmountEquivalence(
            selectedFood = avocado,
            alternativeFood = coconut,
            selectedFoodAmount = 20.0
        )

        assertEquals(alternativeFoodAmountExpected, alternativeFoodAmountResult)
    }
}