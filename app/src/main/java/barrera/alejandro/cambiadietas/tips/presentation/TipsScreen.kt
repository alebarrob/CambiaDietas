package barrera.alejandro.cambiadietas.tips.presentation

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.core.presentation.components.AdaptableColumn
import barrera.alejandro.cambiadietas.core.presentation.theme.LocalSpacing
import barrera.alejandro.cambiadietas.core.presentation.theme.Typography
import barrera.alejandro.cambiadietas.tips.domain.model.Tip
import barrera.alejandro.cambiadietas.tips.util.sendMail

@Composable
fun TipsScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: TipsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(TipsEvent.LoadTips)
    }

    AdaptableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        bottomBarPadding = paddingValues.calculateBottomPadding()
    ) {
        TipsRow(tips = state.tips)
        ContactCard(
            email = stringResource(R.string.email),
            subject = stringResource(R.string.subject),
            context = context
        )
    }
}

@Composable
fun TipsRow(
    modifier: Modifier = Modifier,
    tips: List<Tip>
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier.padding(spacing.spaceExtraSmall),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, colorScheme.primary)
    ) {
        LazyRow(
            modifier = modifier.padding(spacing.spaceSmall),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(tips) { tip ->
                TipsCard(
                    title = tip.title,
                    text = tip.text
                )
            }
        }
    }
}

@Composable
fun TipsCard(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .width(280.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
        border = BorderStroke((0.5).dp, colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                textDecoration = TextDecoration.Underline,
                style = typography.displayMedium
            )
            Text(
                text = text,
                style = typography.displaySmall
            )
        }
    }
}

@Composable
fun ContactCard(
    modifier: Modifier = Modifier,
    email: String,
    subject: String,
    context: Context
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .padding(horizontal = spacing.spaceSmall)
            .clickable {
                context.sendMail(
                    to = email,
                    subject = subject
                )
            },
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
        border = BorderStroke(2.dp, colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Row(
            modifier = modifier
                .height(170.dp)
                .padding(
                start = spacing.spaceSmall,
                top = spacing.spaceSmall
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.click),
                    contentDescription = stringResource(id = R.string.click_image_description)
                )
                Image(
                    painter = painterResource(id = R.drawable.nutricionist),
                    contentDescription = stringResource(id = R.string.dietist_image_description)
                )
            }
            Text(
                modifier = Modifier.padding(spacing.spaceMedium),
                text = stringResource(id = R.string.contact_message),
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

