package barrera.alejandro.cambiadietas.model.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDrawableStringAmountTriple(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    val equivalentAmount: Double
) : Parcelable