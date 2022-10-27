package barrera.alejandro.cambiadietas.models.daos

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.cambiadietas.models.entities.Tip
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Query("SELECT * FROM tips")
    fun getAllTips(): Flow<List<Tip>>
}