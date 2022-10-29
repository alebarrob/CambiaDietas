package barrera.alejandro.cambiadietas.models.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import barrera.alejandro.cambiadietas.models.database.CambiaDietasRoomDataBase
import barrera.alejandro.cambiadietas.models.entities.Food
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class FoodDaoTest {
    private lateinit var cambiaDietasRoomDataBase: CambiaDietasRoomDataBase
    private lateinit var foodDao: FoodDao
    private lateinit var avocado: Food

    @BeforeClass
    fun setUp() {
        cambiaDietasRoomDataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CambiaDietasRoomDataBase::class.java
        ).allowMainThreadQueries().build()
        foodDao = cambiaDietasRoomDataBase.foodDao()
        avocado = Food(
            id = 49,
            drawableName = "fats_avocado",
            name = "Aguacate",
            equivalentAmountForCalculations = 50.0,
            category = "Grasas",
            unit = "gr."
        )
    }

    @AfterClass
    fun tearDown() {
        cambiaDietasRoomDataBase.close()
    }

    @Test
    fun getCategoriesTest() = runTest {
        val category = "Frutas"
        lateinit var allCategories: List<String>

        launch {
            foodDao.getCategories().collect {
                allCategories = it
            }
        }
        Assert.assertTrue(allCategories.contains(category))
    }

    @Test
    fun getFoodByNameTest() = runTest {
        val foodName = "Aguacate"
        lateinit var foodByName: Food

        launch {
            foodDao.getFoodByName(foodName).collect {
                foodByName = it
            }
        }
        Assert.assertEquals(avocado, foodByName)
    }

    @Test
    fun getFoodByCategoryTest() = runTest {
        val foodCategory = "Grasas"
        lateinit var foodByCategory: List<Food>

        launch {
            foodDao.getFoodByCategory(foodCategory).collect {
                foodByCategory = it
            }
        }
        Assert.assertTrue(foodByCategory.contains(avocado))
    }
}