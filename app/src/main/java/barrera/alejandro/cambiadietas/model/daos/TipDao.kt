package barrera.alejandro.cambiadietas.model.daos

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.cambiadietas.model.entities.Tip
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Query("SELECT * FROM tips")
    fun getAllTips(): Flow<List<Tip>>
}