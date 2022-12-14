/*
* Container used in StartScreen and SelectedFoodScreen that adapts to the screen rotation.
* When in landscape mode it is possible to scroll down to see all content.
 */

package barrera.alejandro.cambiadietas.views.commonui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CambiaDietasContainer(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    configuration: Configuration,
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