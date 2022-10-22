package barrera.alejandro.cambiadietas.model.entities

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "food")
data class Food(
    @DrawableRes val imageId: Int,
    @StringRes val nameId: Int,
    val equivalentAmount: Double
) : Parcelable