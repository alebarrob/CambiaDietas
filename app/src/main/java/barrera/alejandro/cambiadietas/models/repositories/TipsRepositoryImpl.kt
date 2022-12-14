package barrera.alejandro.cambiadietas.models.repositories

import barrera.alejandro.cambiadietas.models.daos.TipDao
import barrera.alejandro.cambiadietas.models.entities.Tip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(
    private val tipDao: TipDao
) : TipsRepository {
    override val allTips: Flow<List<Tip>> get() = tipDao.getAllTips()
}