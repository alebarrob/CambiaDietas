package barrera.alejandro.cambiadietas.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.data.tipsData
import barrera.alejandro.cambiadietas.ui.theme.Aquamarine
import barrera.alejandro.cambiadietas.ui.theme.KellyGreen

@Composable
fun TipsScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine,
        elevation = (1.5).dp,
        border = BorderStroke((0.5).dp, KellyGreen),
        modifier = modifier.padding(
            start = 15.dp,
            top = 15.dp,
            end = 15.dp,
            bottom = paddingValues.calculateBottomPadding() + 15.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(6.dp)
        ) {
            AdvicesCard()
            Divider(
                color = KellyGreen,
                thickness = (0.5).dp
            )
            ContactCard()
        }
    }
}

@Composable
fun AdvicesCard(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(10.dp)
    ) {
        items(tipsData) {
            Text(text = stringResource(id = it))
        }
    }
}

@Composable
fun ContactCard() {
    Card {
        Image(painter = painterResource(id = R.drawable.nutricionist), contentDescription = null)
    }
}