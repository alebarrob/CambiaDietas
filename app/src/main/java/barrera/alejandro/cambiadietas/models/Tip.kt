package barrera.alejandro.cambiadietas.models

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "tipTitleId")
    @StringRes val tipTitleId: Int,

    @ColumnInfo(name = "tipBodyId")
    @StringRes val tipBodyId: Int
)