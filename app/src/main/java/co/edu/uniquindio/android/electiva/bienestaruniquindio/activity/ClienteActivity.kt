package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.selecionarIdioma
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.*
import kotlinx.android.synthetic.main.activity_cliente.*
import kotlinx.android.synthetic.main.app_bar_cliente.*
import kotlinx.android.synthetic.main.fragment_lista_encargado.*
import java.util.ArrayList

/**
 * Clase que representa la actividad del cliente
 */
class ClienteActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ListaCategoriaFragment.OnCategoriaSeleccionadoListener, ListaServicioFragment.OnServicioSeleccionadoListener, ListaMisServiciosFragment.OnServicioSeleccionadoListener, DetalleServicioCliente.OnClickDetalleServicioCliente {

    /**
     * Lista de servicios solicitados por el cliente
     */
    var misServiciosSolicitados = ArrayList<Servicio>()
    /**
     * Lista de servicios consumidos por el cliente
     */
    var misServiciosConsumidos = ArrayList<Servicio>()

    /**
     * Funcion en la creacion de la clase
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.itemIconTintList = null

        nav_view.setNavigationItemSelectedListener(this)
        remplazarFragmento(ListaCategoriaFragment(), false)


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
        menuInflater.inflate(R.menu.cliente, menu)
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
            R.id.nav_configurarperfil -> {
                abrirFragmento(VerPerfilFragment(), true, "")

            }
            R.id.nav_cerrarsesion -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.onDestroy()


            }

            R.id.nav_mis_servicios -> {
                abrirFragmento(ListaMisServiciosFragment(), true, "AbrirMisServicios")

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * Funcion que abre el fragmento de categoria
     */
    override fun onCategoriaSeleccionado(pos: Int) {
        abrirFragmento(ListaServicioFragment(), true, "")
    }

    /**
     * Funcion que permite abrir fragmentos
     */
    override fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String) {
        if (tipo.equals("AbrirMisServicios")) {
            val fragmentList = fragment as
                    ListaMisServiciosFragment
            misServiciosSolicitados = ArrayList()
            //misServiciosSolicitados.add(Servicio("Futbol"))
            //misServiciosSolicitados.add(Servicio("Natacion"))
            //misServiciosSolicitados.add(Servicio("Tenis"))
            fragmentList.mis_servicios = misServiciosSolicitados
        }
        val transaccion = supportFragmentManager.beginTransaction().replace(R.id.contenedor_cliente, fragment)
        if (estado) {
            transaccion.addToBackStack(null)
        }
        transaccion.commit()
    }

    /**
     * Funcion que permite rempelazar fragmentos añadiendo y sacandolos de la pila
     */
    fun remplazarFragmento(fragment: Fragment, guardarPila: Boolean) {


        val transacion = supportFragmentManager.beginTransaction()
        transacion.replace(R.id.contenedor_cliente,
                fragment)
        if (guardarPila) {
            transacion.addToBackStack(null)
        }
        transacion.commit()
        btnAgregarEncargado?.visibility = View.INVISIBLE

    }

    /**
     * Funcion que abre el fragmento del servicio
     */
    override fun onServicioSeleccionado(pos: Int) {
        abrirFragmento(DetalleServicioCliente(), true, "")
    }

    /**
     * Funcion cuando se solicita un servicio
     */
    override fun solicitarServicio() {
    }


}
