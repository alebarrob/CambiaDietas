package barrera.alejandro.cambiadietas.model.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    @DrawableRes val imageId: Int,
    @StringRes val nameId: Int,
    val equivalentAmount: Double
) : Parcelable