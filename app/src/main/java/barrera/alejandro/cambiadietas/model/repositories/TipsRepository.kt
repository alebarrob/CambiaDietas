package barrera.alejandro.cambiadietas.model.repositories

import barrera.alejandro.cambiadietas.model.daos.TipDao
import barrera.alejandro.cambiadietas.model.entities.Tip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TipRepository @Inject constructor(
    private val tipDao: TipDao
) {
    val allTips: Flow<List<Tip>> get() = tipDao.getAllTips()
}