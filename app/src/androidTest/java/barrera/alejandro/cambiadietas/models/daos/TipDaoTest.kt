package barrera.alejandro.cambiadietas.models.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import barrera.alejandro.cambiadietas.models.database.CambiaDietasRoomDataBase
import barrera.alejandro.cambiadietas.models.entities.Tip
import org.junit.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class TipDaoTest {
    private lateinit var cambiaDietasRoomDataBase: CambiaDietasRoomDataBase
    private lateinit var tipDao: TipDao

    @Before
    fun setUp() {
        cambiaDietasRoomDataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CambiaDietasRoomDataBase::class.java
        ).allowMainThreadQueries().build()
        tipDao = cambiaDietasRoomDataBase.tipDao()
    }

    @After
    fun tearDown() {
        cambiaDietasRoomDataBase.close()
    }

    @Test
    fun getAllTipsTest() = runTest {
        val tip = Tip(
            id = 1,
            tipTitle = "CONSEJO 1",
            tipBody = "\"Recuerda beber 2 litros de agua cada día.\""
        )
        lateinit var allTips: List<Tip>

        launch {
            tipDao.getAllTips().collect { allTips = it }
        }
        assertTrue(allTips.contains(tip))
    }
}