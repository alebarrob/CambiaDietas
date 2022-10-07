package barrera.alejandro.cambiadietas.ui.commonui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import barrera.alejandro.cambiadietas.ui.theme.CambiaDietasAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CambiaDietasAppTheme { CambiaDietasApp() } }
    }
}