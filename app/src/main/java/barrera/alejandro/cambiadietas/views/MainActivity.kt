package barrera.alejandro.cambiadietas.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import barrera.alejandro.cambiadietas.viewmodels.*
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietasApp
import barrera.alejandro.cambiadietas.views.theme.CambiaDietasAppTheme

class MainActivity : ComponentActivity() {
    private val commonUiViewModel: CommonUiViewModel by viewModels()
    private val startScreenViewModel: StartScreenViewModel by viewModels()
    private val tipsScreenViewModel: TipsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasAppTheme {
                CambiaDietasApp(
                    commonUiViewModel,
                    startScreenViewModel,
                    tipsScreenViewModel
                )
            }
        }
    }
}