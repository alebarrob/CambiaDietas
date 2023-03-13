package barrera.alejandro.cambiadietas.categories.presentation.categories_overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.categories.presentation.components.AdvertView
import barrera.alejandro.cambiadietas.core.presentation.components.AdaptableColumn
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing

@Composable
fun CategoriesOverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesOverviewViewModel = hiltViewModel(),
    onNavigateToCategoryDetail: (category: String) -> Unit,
    paddingValues: PaddingValues
) {
    val spacing = LocalSpacing.current
    val categories = viewModel.categories

    LaunchedEffect(key1 = Unit) {
        viewModel.loadCategories()
    }

    AdaptableColumn(
        modifier = modifier.padding(horizontal = spacing.spaceSmall),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        bottomBarPadding = paddingValues.calculateBottomPadding()
    ) {
        AdvertView()
        categories.forEach { category ->
            CategoryButton(
                category = category,
                onClick = {
                    onNavigateToCategoryDetail(category)
                }
            )
        }
        AdvertView()
    }
}

@Composable
fun CategoryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    category: String
) {
    val spacing = LocalSpacing.current

    Button(
        onClick = onClick,
        modifier = modifier
            .padding(spacing.spaceExtraSmall)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.secondary),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        border = BorderStroke(2.dp, colorScheme.primary),
        contentPadding = PaddingValues(spacing.spaceMedium)
    ) {
        Text(
            text = category.uppercase(),
            style = typography.displayMedium
        )
    }
}