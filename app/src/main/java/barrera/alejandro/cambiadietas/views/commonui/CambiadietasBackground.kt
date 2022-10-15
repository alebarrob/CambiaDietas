package barrera.alejandro.cambiadietas.views.commonui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasBackgroundViewModel

@Composable
fun CambiadietasBackground(cambiaDietasBackgroundViewModel: CambiaDietasBackgroundViewModel) {
    val pattern by cambiaDietasBackgroundViewModel.pattern.observeAsState(
        initial = ImageBitmap.imageResource(R.drawable.background)
    )
    val paint by cambiaDietasBackgroundViewModel.paint.observeAsState(initial = Paint().asFrameworkPaint())

    Canvas(modifier = Modifier.fillMaxSize()) {
        paint.apply {
            isAntiAlias = true
            shader = ImageShader(pattern, TileMode.Repeated, TileMode.Repeated)
        }
        drawIntoCanvas { it.nativeCanvas.drawPaint(paint) }
        paint.reset()
    }
}