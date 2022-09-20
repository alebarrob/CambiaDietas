package barrera.alejandro.cambiadietas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource

@Composable
fun CambiadietasBackground() {
    val pattern = ImageBitmap.imageResource(R.drawable.background)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val paint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            shader = ImageShader(
                pattern,
                TileMode.Repeated,
                TileMode.Repeated
            )
        }

        drawIntoCanvas { it.nativeCanvas.drawPaint(paint) }
        paint.reset()
    }
}