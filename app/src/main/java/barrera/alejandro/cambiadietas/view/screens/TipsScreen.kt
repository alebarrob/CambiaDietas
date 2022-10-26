package barrera.alejandro.cambiadietas.view.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barrera.alejandro.cambiadietas.R
import barrera.alejandro.cambiadietas.model.entities.Tip
import barrera.alejandro.cambiadietas.view.theme.Aquamarine
import barrera.alejandro.cambiadietas.view.theme.KellyGreen
import barrera.alejandro.cambiadietas.viewmodel.TipsScreenViewModel

@Composable
fun TipsScreen(
    modifier: Modifier = Modifier,
    tipsScreenViewModel: TipsScreenViewModel,
    paddingValues: PaddingValues,
    configuration: Configuration,
    context: Context
) {
    val tips by tipsScreenViewModel.tips.collectAsState(initial = listOf())

    Card(
        modifier = modifier.padding(
            start = 15.dp,
            top = 15.dp,
            end = 15.dp,
            bottom = paddingValues.calculateBottomPadding() + 15.dp
        ),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Aquamarine,
        elevation = (1.5).dp,
        border = BorderStroke((0.5).dp, KellyGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TipsRow(tips = tips)
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Divider(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    color = KellyGreen,
                    thickness = (0.5).dp
                )
            }
            ContactCard(context = context)
        }
    }
}

@Composable
fun TipsRow(
    modifier: Modifier = Modifier,
    tips: List<Tip>
) {
    LazyRow(
        modifier = modifier.padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(tips) { tip ->
            TipsCard(
                tipTitle = tip.tipTitle,
                tipBody = tip.tipBody
            )
        }
    }
}

@Composable
fun TipsCard(
    modifier: Modifier = Modifier,
    tipTitle: String,
    tipBody: String
) {
    Card(
        modifier = modifier
            .width(280.dp)
            .height(200.dp),
        border = BorderStroke((0.5).dp, KellyGreen),
        elevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tipTitle,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.width(300.dp),
                    text = tipBody
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactCard(
    modifier: Modifier = Modifier,
    context: Context
) {
    val email = stringResource(R.string.email)
    val subject = stringResource(R.string.subject)

    Card(
        border = BorderStroke((0.5).dp, KellyGreen),
        elevation = (1.5).dp,
        onClick = {
            context.sendMail(
                to = email,
                subject = subject
            )
        }
    ) {
        Row {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = modifier.padding(
                        start = 5.dp,
                        top = 5.dp
                    ),
                    painter = painterResource(id = R.drawable.click),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.nutricionist),
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier.padding(
                    top = 30.dp,
                    end = 15.dp,
                    bottom = 30.dp
                ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.contact_message),
                    fontSize = 15.sp
                )
            }
        }
    }
}

fun Context.sendMail(
    to: String,
    subject: String
) {
    try {
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "vnd.android.cursor.item/email"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "La aplicación seleccionada no está disponible", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "Se ha producido un error", Toast.LENGTH_SHORT).show()
    }
}

