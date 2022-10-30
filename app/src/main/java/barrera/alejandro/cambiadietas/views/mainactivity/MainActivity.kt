package barrera.alejandro.cambiadietas.views.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.viewmodels.CategoriesScreenViewModel
import barrera.alejandro.cambiadietas.viewmodels.StartScreenViewModel
import barrera.alejandro.cambiadietas.viewmodels.TipsScreenViewModel
import barrera.alejandro.cambiadietas.views.commonui.CambiaDietas
import barrera.alejandro.cambiadietas.views.theme.CambiaDietasAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasAppTheme {
                val startScreenViewModel = hiltViewModel<StartScreenViewModel>()
                val categoriesScreenViewModel = hiltViewModel<CategoriesScreenViewModel>()
                val tipsScreenViewModel = hiltViewModel<TipsScreenViewModel>()

                CambiaDietas(startScreenViewModel, categoriesScreenViewModel, tipsScreenViewModel)
            }
        }
    }
}