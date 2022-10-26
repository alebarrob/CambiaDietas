package barrera.alejandro.cambiadietas.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "drawable_name")
    val drawableName: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "equivalent_amount_for_calculations")
    val equivalentAmountForCalculations: Double,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "unit")
    val unit: String
)