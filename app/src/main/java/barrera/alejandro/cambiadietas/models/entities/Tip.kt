package barrera.alejandro.cambiadietas.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "tip_title")
    val tipTitle: String,

    @ColumnInfo(name = "tip_body")
    val tipBody: String
)