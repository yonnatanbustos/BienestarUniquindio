package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.selecionarIdioma
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.ListaClienteXEncargadoFragment
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.ListaServicioFragment
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.VerPerfilFragment
import kotlinx.android.synthetic.main.activity_encargado.*
import kotlinx.android.synthetic.main.app_bar_encargado.*

/**
 * Actividad que soporta todos los fragmentos del Encargado
 */
class EncargadoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ListaServicioFragment.OnServicioSeleccionadoListener, ListaClienteXEncargadoFragment.OnClickClienteXEncargadoSeleccionadoListener {


    /**
     * Funcion en la creacion de la clase
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encargado)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.itemIconTintList = null
        nav_view.setNavigationItemSelectedListener(this)

        remplazarFragmento(ListaServicioFragment(), false)
    }

    /**
     * Funcion que retorna a la actividad anterior
     */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Funcion en la creacion de la opcines del menu
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.encargado, menu)
        return true
    }

    /**
     * Funcion en las opciones del menu seleccionada
     */
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
     * Funcion cuando se selecciona un item en la navegacion
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_configurarperfil_encargado -> {
                remplazarFragmento(VerPerfilFragment(), true)
            }
            R.id.nav_cerrarsesion_encargado -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.onDestroy()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * Funcion que abre el fragmeto de servicios
     */
    override fun onServicioSeleccionado(pos: Int) {
        remplazarFragmento(ListaClienteXEncargadoFragment(), true)

    }

    /**
     * Funcion que permite reemplazar un fragmento
     */
    fun remplazarFragmento(fragment: Fragment, guardarPila: Boolean) {

        val transacion = supportFragmentManager.beginTransaction()
        transacion.replace(R.id.contenedor_encargado,
                fragment)
        if (guardarPila) {
            transacion.addToBackStack(null)
        }
        transacion.commit()

    }

    /**
     * Funcion que permite abrir el fragmento de cliente x encargado
     */
    override fun OnClienteXEncargadoSeleccionado(pos: Int) {
        remplazarFragmento(ListaClienteXEncargadoFragment(), true)
    }


}//Cierre de la actividad
