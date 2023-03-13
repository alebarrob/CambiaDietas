package barrera.alejandro.cambiadietas.tips.data.repository

import barrera.alejandro.cambiadietas.tips.data.dao.TipDao
import barrera.alejandro.cambiadietas.tips.domain.model.Tip
import barrera.alejandro.cambiadietas.tips.domain.repository.TipsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(
    private val tipDao: TipDao
) : TipsRepository {
    override fun getAllTips(): Flow<List<Tip>> {
        return tipDao.getTips()
    }
}