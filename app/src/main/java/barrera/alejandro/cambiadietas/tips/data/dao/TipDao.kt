package barrera.alejandro.cambiadietas.tips.data.dao

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.cambiadietas.tips.domain.model.Tip
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Query("SELECT * FROM tips")
    fun getTips(): Flow<List<Tip>>
}