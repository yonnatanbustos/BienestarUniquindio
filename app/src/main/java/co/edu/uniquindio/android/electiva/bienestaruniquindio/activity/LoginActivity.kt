package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.selecionarIdioma
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.RegistrarseFragment
import kotlinx.android.synthetic.main.app_bar_iniciar_sesion.*

class LoginActivity : AppCompatActivity() {

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
     *
     */
    fun abrirVentanaRegistrarse(v: View?) {
        supportFragmentManager.beginTransaction().replace(R.id.contenedor_login, RegistrarseFragment()).addToBackStack(null).commit()
    }


}
