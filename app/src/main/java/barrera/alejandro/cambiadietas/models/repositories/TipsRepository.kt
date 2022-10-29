package barrera.alejandro.cambiadietas.models.repositories

import barrera.alejandro.cambiadietas.models.entities.Tip
import kotlinx.coroutines.flow.Flow

interface TipsRepository {
    val allTips: Flow<List<Tip>>
}