package barrera.alejandro.cambiadietas.tips.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Context.sendMail(
    to: String,
    subject: String
) {
    try {
        val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
        val emailIntent = Intent(Intent.ACTION_SEND)

        emailSelectorIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        emailIntent.selector = emailSelectorIntent
        startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "La aplicación seleccionada no está disponible", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "Se ha producido un error", Toast.LENGTH_SHORT).show()
    }
}