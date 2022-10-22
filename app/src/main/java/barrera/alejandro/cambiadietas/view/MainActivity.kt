package barrera.alejandro.cambiadietas.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.viewmodel.*
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietas
import barrera.alejandro.cambiadietas.view.theme.CambiaDietasAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasAppTheme {
                val commonUiViewModel: CommonUiViewModel by viewModels()
                val startScreenViewModel: StartScreenViewModel by viewModels()
                val tipsScreenViewModel = hiltViewModel<TipsScreenViewModel>()

                CambiaDietas(
                    commonUiViewModel,
                    startScreenViewModel,
                    tipsScreenViewModel
                )
            }
        }
    }
}