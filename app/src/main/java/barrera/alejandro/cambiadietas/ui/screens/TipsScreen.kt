package barrera.alejandro.cambiadietas.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(6.dp)
        ) {
            TipsRow()
            ContactCard()
        }
    }
}

@Composable
fun TipsRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(5.dp)
    ) {
        items(tipsData) {
            TipsCard(
                tipTitle = stringResource(id = it.tipTitle),
                tipBody = stringResource(id = it.tipBody)
            )
        }
    }
}

@Composable
fun TipsCard(tipTitle: String, tipBody: String, modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke((0.5).dp, KellyGreen),
        elevation = 2.dp,
        modifier = modifier
            .width(280.dp)
            .height(200.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp),
            ) {
                Text(
                    text = tipTitle,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = tipBody,
                    modifier = Modifier.width(300.dp)
                )
            }
        }
    }
}

@Composable
fun ContactCard(modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke((0.5).dp, KellyGreen),
        elevation = (1.5).dp,
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nutricionist),
                    contentDescription = null
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = R.string.contact_message))
            }
        }
    }
}