package barrera.alejandro.cambiadietas

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine
import barrera.alejandro.cambiadietas.ui.theme.Cadet
import barrera.alejandro.cambiadietas.ui.theme.RaisinBlack

@Composable
fun CambiaDietasApp() {
    val pattern = ImageBitmap.imageResource(R.drawable.background)

    Box {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val paint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                shader = ImageShader(
                    pattern,
                    TileMode.Repeated,
                    TileMode.Repeated
                )
            }

            drawIntoCanvas {
                it.nativeCanvas.drawPaint(paint)
            }
            paint.reset()
        }
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = { CambiaDietasBottomBar() },
            content = { StartScreen() }
        )
    }
}

@Composable
private fun StartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.logo_cambiadietas),
            contentDescription = null,
            alignment = Alignment.Center
        )
        Spacer(Modifier.height(16.dp))
        FoodPicker()
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun FoodPicker(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, bottom = 40.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine
    ) {
        Column(
            modifier = modifier
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FoodCategoryMenu()
            Spacer(Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.foodpicker_food_question))
            Spacer(Modifier.height(16.dp))
            FoodRow()
        }
    }
}

@Composable
private fun FoodCategoryMenu(modifier: Modifier = Modifier) {
    val items = categoriesData
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("Elige una categoría") }

    Box(modifier = modifier) {
        Surface(shape = MaterialTheme.shapes.small) {
            Text(
                text = selectedCategory,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .clickable(onClick = { expanded = true })
                    .background(Color.White)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            items.forEach { item ->
                val category = stringResource(id = item)

                DropdownMenuItem(onClick = {
                    selectedCategory = category
                    expanded = false
                }) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = item))
                    }
                }
            }
        }
    }
}

@Composable
private fun FoodRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = modifier
    ) {
        items(foodData) {item ->
            FoodImage(item.drawable, item.text)
        }
    }
}

@Composable
private fun FoodImage(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 10.dp,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(text),
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
    }
}

@Composable
private fun CambiaDietasBottomBar(modifier: Modifier = Modifier) {
    BottomNavigation {
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = true,
            onClick = { TODO() },
            drawable = R.drawable.ic_start,
            text = R.string.bottom_navigation_start,
            fontWeight = FontWeight.Bold
        )
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = false,
            onClick = { TODO() },
            drawable = R.drawable.ic_food_categories,
            text = R.string.bottom_navigation_food_categories,
            fontWeight = FontWeight.Light
        )
        CambiaDietasBottomNavigationItem(
            rowScope = this,
            selected = false,
            onClick = { TODO() },
            drawable = R.drawable.ic_nutrition_advices,
            text = R.string.bottom_navigation_nutrition_advices,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun CambiaDietasBottomNavigationItem(
    rowScope: RowScope,
    selected: Boolean,
    onClick: () -> Unit,
    fontWeight: FontWeight,
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    rowScope.BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = null
            )
        },
        label = { Text(
            text = stringResource(text),
            fontWeight = fontWeight
        ) },
        selectedContentColor = RaisinBlack,
        unselectedContentColor = Cadet,
    )
}

// Data

private val categoriesData = listOf(
    R.string.fruit_category,
    R.string.protein_category,
    R.string.lacteos_category
)

private val foodData = listOf(
    R.drawable.pinneaple to R.string.pinneaple_card_text,
    R.drawable.orange to R.string.orange_card_text,
    R.drawable.apple to R.string.apple_card_text,
    R.drawable.grapes to R.string.grapes_card_text,
    R.drawable.chocolate to R.string.chocolate_card_text
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

