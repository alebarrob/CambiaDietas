package barrera.alejandro.cambiadietas.view

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import barrera.alejandro.cambiadietas.viewmodel.*
import barrera.alejandro.cambiadietas.view.commonui.CambiaDietasApp
import barrera.alejandro.cambiadietas.view.theme.CambiaDietasAppTheme

class MainActivity : ComponentActivity() {
    private val commonUiViewModel: CommonUiViewModel by viewModels()
    private val startScreenViewModel: StartScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasAppTheme {
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val tipsScreenViewModel: TipsScreenViewModel = viewModel(
                        it,
                        "TipsScreenViewModel",
                        TipScreenViewModelFactory(LocalContext.current.applicationContext as Application)
                    )

                    CambiaDietasApp(
                        commonUiViewModel,
                        startScreenViewModel,
                        tipsScreenViewModel
                    )
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class TipScreenViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TipsScreenViewModel(application) as T
    }
}