package barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.NativePaint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CambiaDietasBackgroundViewModel : ViewModel() {
    private val _pattern = MutableLiveData<ImageBitmap>()
    val pattern: LiveData<ImageBitmap> get() = _pattern

    private val _paint = MutableLiveData<NativePaint>()
    val paint: LiveData<NativePaint> get() = _paint
}