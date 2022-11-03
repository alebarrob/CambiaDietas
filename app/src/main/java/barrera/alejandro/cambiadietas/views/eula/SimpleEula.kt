package barrera.alejandro.cambiadietas.views.eula

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import barrera.alejandro.cambiadietas.R

@Composable
fun EulaAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExit: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Red.copy(alpha = 0.8F)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.background_image),
                        contentDescription = "Exit app",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Text(
                    modifier = Modifier.padding(8.dp), fontSize = 20.sp,
                    text = stringResource(id = R.string.license_agreement_title)
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.license_agreement_body)
                )
                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        onClick = onExit
                    ) {
                        Text(text = "No acepto")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        onClick = onDismiss
                    ) {
                        Text(text = "Acepto")
                    }
                }
            }
        }
    }
}