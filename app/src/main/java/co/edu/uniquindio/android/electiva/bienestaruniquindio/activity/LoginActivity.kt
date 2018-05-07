package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.selecionarIdioma
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.RegistrarseFragment
import kotlinx.android.synthetic.main.app_bar_iniciar_sesion.*
import kotlinx.android.synthetic.main.fragment_registrarse.*

private const val SELECT_FILE = 1

class LoginActivity : AppCompatActivity(), RegistrarseFragment.OnClickRegistrarse {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        setTitle(R.string.title_activity_login)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {

            R.id.menu_cambiar_idioma -> {
                selecionarIdioma(this)
                val intent = this.intent
                intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK)
                this.finish()
                this.startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * Funcion que permite abrir la actividad de administrador
     */
    fun abrirVentanaAdministrador(v: View?) {
        val intent = Intent(this, AdministradorActivity::class.java)
        startActivity(intent)

    }

    /**
     * Funcion que permite abrir la actividad de cliente
     */
    fun abrirVentanaCliente(v: View?) {
        val intent = Intent(this, ClienteActivity::class.java)
        startActivity(intent)

    }

    /**
     * Funcion que permite abrir la actividad de cliente
     */
    fun abrirVentanaEncargado(v: View?) {
        val intent = Intent(this, EncargadoActivity::class.java)
        startActivity(intent)

    }

    /**
     * Funcion que permite abrir la ventana de registro de usuario
     */
    fun abrirVentanaRegistrarse(v: View?) {
        supportFragmentManager.beginTransaction().replace(R.id.contenedor_login, RegistrarseFragment()).addToBackStack(null).commit()
    }

    /**
     * Funcion que permite cargar una imagen al registro de usuario
     */
    override fun seleccionarFotoRegistrarse() {
        val intent = Intent()
        intent.setType("image/**")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Seleccione Imagen"), SELECT_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_FILE) {
            val imageUri: Uri = data.data
            foto_registro_usuario.setImageURI(imageUri)
        }

    }

    /**
     * Funcion que permite registrar un usuario en la aplicacion desde el fragmento de RegistrarFragment
     */
    override fun registrarUsuario() {

    }


}//Cierre de la actividad
