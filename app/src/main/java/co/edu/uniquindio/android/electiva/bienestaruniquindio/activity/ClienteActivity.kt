package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.ListaCategoriaFragment
import kotlinx.android.synthetic.main.activity_cliente.*
import kotlinx.android.synthetic.main.app_bar_cliente.*

class ClienteActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ListaCategoriaFragment.OnCategoriaSeleccionadoListener {


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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.cliente, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCategoriaSeleccionado(pos: Int) {
    }

    override fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String) {
        val transaccion = supportFragmentManager.beginTransaction().replace(R.id.contenedor_cliente, fragment)
        if (estado) {
            transaccion.addToBackStack(null)
        }
        transaccion.commit()
    }

    fun remplazarFragmento(fragment: Fragment, guardarPila: Boolean) {

        val transacion = supportFragmentManager.beginTransaction()
        transacion.replace(R.id.contenedor_cliente,
                fragment)
        if (guardarPila) {
            transacion.addToBackStack(null)
        }
        transacion.commit()

    }


}
