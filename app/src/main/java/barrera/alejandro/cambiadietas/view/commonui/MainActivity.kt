package barrera.alejandro.cambiadietas.view.commonui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import barrera.alejandro.cambiadietas.view.theme.CambiaDietasAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CambiaDietasAppTheme { CambiaDietasApp() } }
    }
}