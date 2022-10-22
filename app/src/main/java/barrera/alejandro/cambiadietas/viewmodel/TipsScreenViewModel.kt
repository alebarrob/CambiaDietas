package barrera.alejandro.cambiadietas.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import barrera.alejandro.cambiadietas.model.database.CambiaDietasRoomDataBase
import barrera.alejandro.cambiadietas.model.entities.Tip
import barrera.alejandro.cambiadietas.model.repositories.TipRepository
import kotlinx.coroutines.flow.Flow

class TipsScreenViewModel(application: Application): ViewModel() {
    private var tipsRepository: TipRepository
    val tips: Flow<List<Tip>>

    init {
        val dataBase = CambiaDietasRoomDataBase.getInstance(application)
        val tipDao = dataBase.tipDao()

        tipsRepository = TipRepository(tipDao)
        tips = tipsRepository.allTips
    }
}