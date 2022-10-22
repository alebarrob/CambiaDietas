package barrera.alejandro.cambiadietas.view.commonui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource
import barrera.alejandro.cambiadietas.R

@Composable
fun CambiadietasBackground(modifier: Modifier = Modifier) {
    val pattern = ImageBitmap.imageResource(R.drawable.background)
    val paint = Paint().asFrameworkPaint()

    Canvas(modifier = modifier.fillMaxSize()) {
        paint.apply {
            isAntiAlias = true
            shader = ImageShader(pattern, TileMode.Repeated, TileMode.Repeated)
        }
        drawIntoCanvas { it.nativeCanvas.drawPaint(paint) }
        paint.reset()
    }
}