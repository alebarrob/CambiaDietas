package barrera.alejandro.cambiadietas.tips.domain.use_case

import barrera.alejandro.cambiadietas.tips.domain.model.Tip
import barrera.alejandro.cambiadietas.tips.domain.repository.TipsRepository
import kotlinx.coroutines.flow.Flow

class GetAllTips(private val tipsRepository: TipsRepository) {
    operator fun invoke(): Flow<List<Tip>> {
        return tipsRepository.getAllTips()
    }
}