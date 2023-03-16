package barrera.alejandro.cambiadietas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.cambiadietas.core.presentation.CoreViewModel
import barrera.alejandro.cambiadietas.core.presentation.components.Background
import barrera.alejandro.cambiadietas.core.presentation.components.BottomBar
import barrera.alejandro.cambiadietas.core.presentation.components.TopBar
import barrera.alejandro.cambiadietas.core.presentation.components.UiController
import barrera.alejandro.cambiadietas.core.presentation.navigation.NavGraph
import barrera.alejandro.cambiadietas.core.presentation.navigation.NavigationScreen.SelectFoodScreen
import barrera.alejandro.cambiadietas.core.presentation.theme.CambiaDietasTheme
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CambiaDietasTheme {
                val context = LocalContext.current

                val viewModel: CoreViewModel by viewModels()
                val state = viewModel.state

                val navController = rememberNavController()
                val currentDestination =
                    navController.currentBackStackEntryAsState().value?.destination

                // Ads initialization
                MobileAds.initialize(context)

                UiController(
                    viewModel = viewModel,
                    currentDestination = currentDestination
                )

                Box {
                    Background()
                    Scaffold(
                        topBar = {
                            TopBar(
                                onBackClick = { navController.navigateUp() },
                                topBarState = state.isTopBarVisible
                            )
                        },
                        bottomBar = {
                            BottomBar(
                                onItemClick = { screen ->
                                    navController.navigate(screen.route) {
                                        popUpTo(SelectFoodScreen.route) { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                                bottomBarState = state.isBottomBarVisible,
                                currentDestination = currentDestination
                            )
                        },
                        containerColor = Color.Transparent,
                    ) { paddingValues ->
                        NavGraph(
                            navController = navController,
                            paddingValues = paddingValues
                        )
                    }
                }
            }
        }
    }
}