package barrera.alejandro.cambiadietas.tips.domain.repository

import barrera.alejandro.cambiadietas.tips.domain.model.Tip
import kotlinx.coroutines.flow.Flow

interface TipsRepository {
    fun getAllTips(): Flow<List<Tip>>
}