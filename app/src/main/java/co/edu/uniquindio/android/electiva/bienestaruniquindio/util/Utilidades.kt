package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

const val MIS_PREFERENCIAS = "MisPreferencias"
const val LENGUAJE_DE_PREFERENCIA = "languaje_preferences"
const val LENGUAJE_ES = "es"
const val LENGUAJE_EN = "en"

/**
 * Funcion que permite cambiar el idioma de la aplicacion
 */
fun selecionarIdioma(context: Context) {

    val preferences =
            context.getSharedPreferences(MIS_PREFERENCIAS,
                    Context.MODE_PRIVATE)
    var idioma = preferences.getString(LENGUAJE_DE_PREFERENCIA,
            LENGUAJE_ES)
    if (idioma.equals(LENGUAJE_EN)) {
        idioma = LENGUAJE_ES
    } else {
        idioma = LENGUAJE_EN
    }
    val editor = preferences.edit()
    editor.putString(LENGUAJE_DE_PREFERENCIA, idioma)
    editor.apply()
    cambiarIdioma(context)
}

@Suppress("DEPRECATION")
fun cambiarIdioma(context: Context) {
    val preferences =
            context.getSharedPreferences(MIS_PREFERENCIAS,
                    Context.MODE_PRIVATE)
    var idioma = preferences.getString(LENGUAJE_DE_PREFERENCIA,
            LENGUAJE_ES)
    val local = Locale(idioma)
    Locale.setDefault(local)
    val config = Configuration()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocale(local)
    } else {
        config.locale = local
    }
    context.resources.updateConfiguration(config, null)
}

/**
 * Funcion que permite generar el codigo
 */
fun getKeyHash(context: Context) {
    try {
        val info =
                context.getPackageManager().getPackageInfo(context.getPackageName(),
                        PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            val sign = Base64.encodeToString(md.digest(), Base64.DEFAULT)
            Log.e("Mi clave HASH:", sign)
        }
    } catch (e: PackageManager.NameNotFoundException) {
        Log.d("prueba", "1 KeyHash Error: " + e.message)
    } catch (e: NoSuchAlgorithmException) {
        Log.d("prueba", "2 KeyHash Error: " + e.message)
    }
}