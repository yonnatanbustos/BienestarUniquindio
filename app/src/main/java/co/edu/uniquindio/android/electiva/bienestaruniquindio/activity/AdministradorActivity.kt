package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity


import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.*
import kotlinx.android.synthetic.main.activity_administrador.*
import kotlinx.android.synthetic.main.app_bar_administrador.*
import kotlinx.android.synthetic.main.fragment_detalle_encargado.*
import kotlinx.android.synthetic.main.fragment_detalle_servicio.*
import kotlinx.android.synthetic.main.fragment_registrar_encargado.*
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList

private const val SELECT_FILE = 1

class AdministradorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ListaEncargadoFragment.OnEncargadoSeleccionadoListener, ListaCategoriaFragment.OnCategoriaSeleccionadoListener, IniciarAdministradorFragment.OnClickIniciarAdministrador, RegistrarServicioFragment.OnClickCalendario, RegistrarEncargadoFragment.OnClickRegistrarEncargado, ListaServicioFragment.OnServicioSeleccionadoListener, DetalleServicioFragment.onClickDetalleServicio, DetalleEncargadoFragment.onClickDetalleEncargado {

    lateinit var calendario: Calendar
    lateinit var selectorFecha: DatePickerDialog
    lateinit var selectedImageUri: Uri
    lateinit var selectedImage: Uri
    var encargados = ArrayList<Encargado>()
    var imageStream = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.itemIconTintList = null
        nav_view.setNavigationItemSelectedListener(this)

        remplazarFragmento(IniciarAdministradorFragment(), true)
        encargados = ArrayList()
        encargados.add(Encargado("Yonnatan"))
        encargados.add(Encargado("El Flaco"))
        encargados.add(Encargado("Alzate"))

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
        menuInflater.inflate(R.menu.administrador, menu)
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
            R.id.item_agregar_servicio -> {
                abrirFragmento(RegistrarServicioFragment(), true, "RegistrarServicio")
            }
            R.id.item_agregar_encargado -> {
                abrirFragmento(RegistrarEncargadoFragment(), true, "RegistrarEncargado")
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun remplazarFragmento(fragment: Fragment, guardarPila: Boolean) {

        val transacion = supportFragmentManager.beginTransaction()
        transacion.replace(R.id.contenedor_administrador,
                fragment)
        if (guardarPila) {
            transacion.addToBackStack(null)
        }
        transacion.commit()
    }


    override fun abrirCalendario(fragment: Fragment) {
        calendario = Calendar.getInstance()
        val year = calendario.get(Calendar.YEAR)
        val month = calendario.get(Calendar.MONTH)
        val day = calendario.get(Calendar.DAY_OF_MONTH)
        selectorFecha = DatePickerDialog(fragment.context, DatePickerDialog.OnDateSetListener { v, year, month, day ->

        }, year, month, day)
        selectorFecha.show()
    }


    override fun onEncargadoSeleccionado(pos: Int) {
        abrirFragmento(DetalleEncargadoFragment(), true, "")

    }

    override fun onCategoriaSeleccionado(pos: Int) {
        abrirFragmento(ListaServicioFragment(), true, "")
    }

    override fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String) {

        if (tipo.equals("AbrirEncargados")) {
            val fragmentList = fragment as
                    ListaEncargadoFragment

            fragmentList.encargados = encargados
        }
        val transaccion = supportFragmentManager.beginTransaction().replace(R.id.contenedor_administrador, fragment)
        if (estado) {
            transaccion.addToBackStack(null)
        }
        transaccion.commit()


    }

    override fun agregarEncargado(nombre: String) {
        encargados.add(0, Encargado(nombre))


    }

    override fun onServicioSeleccionado(pos: Int) {
        abrirFragmento(DetalleServicioFragment(), true, "")
    }


    /**
     * Funcion que cambio el estado de los elementos del fragmento DetalleServicioFragment
     */
    override fun cambiarEstado(estado: Boolean) {

        if (estado) {
            layout_btns_detalle_servicio.visibility = View.VISIBLE
            btn_modificar_foto.visibility = View.VISIBLE
            btn_modificar_servicio.visibility = View.INVISIBLE
        } else {
            layout_btns_detalle_servicio.visibility = View.INVISIBLE
            btn_modificar_foto.visibility = View.INVISIBLE
            btn_modificar_servicio.visibility = View.VISIBLE
        }

        txt_informacion_servicio.isEnabled = estado
        label_titulo_servicio.isEnabled = estado


    }

    override fun guardarCambiosDetalleServicio() {

    }

    /**
     * Funcion que permite seleccionar una foto
     */
    override fun seleccionarFoto() {
        val intent = Intent()
        intent.setType("image/**")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "seleccione una imagen"), SELECT_FILE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_FILE) {
            selectedImage = data!!.data
            foto_encargado_registrar.setImageURI(selectedImage)

        }

    }

    /**
     * Funcion que permite habilitar los campos de la ventana DetalleEncargado
     */
    override fun cambiarEstadoVentanaDetalleEncargado(estado: Boolean) {
        if (estado) {
            txtNombreEncargado.isEnabled = estado
            txtCedulaEncargado.isEnabled = estado
            txtContrasenaEncargado.isEnabled = estado
            comboServicioGestionar.isEnabled = estado
            layouts_btns_detalle_encargado.visibility = View.VISIBLE
            fab_menu_detalle_encargado.visibility = View.INVISIBLE
        } else {
            txtNombreEncargado.isEnabled = estado
            txtCedulaEncargado.isEnabled = estado
            txtContrasenaEncargado.isEnabled = estado
            comboServicioGestionar.isEnabled = estado
            layouts_btns_detalle_encargado.visibility = View.INVISIBLE
            fab_menu_detalle_encargado.visibility = View.VISIBLE
        }
    }

    override fun guardarCambiosDetalleEncargado() {

    }

}//Cierre de la actividad
