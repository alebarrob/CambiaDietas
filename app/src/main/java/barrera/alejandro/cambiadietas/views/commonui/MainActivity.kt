package barrera.alejandro.cambiadietas.views.commonui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import barrera.alejandro.cambiadietas.viewmodels.commonuiviewmodels.CambiaDietasAppViewModel
import barrera.alejandro.cambiadietas.views.theme.CambiaDietasAppTheme

class MainActivity : ComponentActivity() {
    private val cambiaDietasAppViewModel: CambiaDietasAppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasAppTheme {
                CambiaDietasApp(cambiaDietasAppViewModel)
            }
        }
    }
}