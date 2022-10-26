package barrera.alejandro.cambiadietas.view.commonui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun CambiaDietasContainer(
    modifier: Modifier = Modifier,
    configuration: Configuration,
    paddingValues: PaddingValues,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = paddingValues.calculateBottomPadding())
            else -> modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}