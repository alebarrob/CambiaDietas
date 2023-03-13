package barrera.alejandro.cambiadietas.tips.presentation

import barrera.alejandro.cambiadietas.tips.domain.model.Tip

data class TipsState(
    val tips: List<Tip> = emptyList()
)
