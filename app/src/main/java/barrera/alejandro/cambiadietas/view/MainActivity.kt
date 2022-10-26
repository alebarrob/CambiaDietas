package barrera.alejandro.cambiadietas.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietas
import barrera.alejandro.cambiadietas.view.theme.CambiaDietasAppTheme
import barrera.alejandro.cambiadietas.viewmodel.CategoriesScreenViewModel
import barrera.alejandro.cambiadietas.viewmodel.CommonUiViewModel
import barrera.alejandro.cambiadietas.viewmodel.StartScreenViewModel
import barrera.alejandro.cambiadietas.viewmodel.TipsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasAppTheme {
                val commonUiViewModel = hiltViewModel<CommonUiViewModel>()
                val startScreenViewModel = hiltViewModel<StartScreenViewModel>()
                val categoriesScreenViewModel = hiltViewModel<CategoriesScreenViewModel>()
                val tipsScreenViewModel = hiltViewModel<TipsScreenViewModel>()

                CambiaDietas(
                    commonUiViewModel,
                    startScreenViewModel,
                    categoriesScreenViewModel,
                    tipsScreenViewModel,
                )
            }
        }
    }
}