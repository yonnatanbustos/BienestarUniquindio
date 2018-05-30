package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
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
import android.widget.Toast
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.getKeyHash
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.selecionarIdioma
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Cliente
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.*
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.ManagerFireBase
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.Singleton
import kotlinx.android.synthetic.main.activity_administrador.*
import kotlinx.android.synthetic.main.app_bar_administrador.*
import kotlinx.android.synthetic.main.fragment_detalle_encargado.*
import kotlinx.android.synthetic.main.fragment_detalle_servicio.*
import kotlinx.android.synthetic.main.fragment_registrar_encargado.*
import java.util.*

private const val SELECT_FILE = 1

/**
 * Acividad que representa el inicio del admnistrador
 */
class AdministradorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ListaEncargadoFragment.OnEncargadoSeleccionadoListener, ListaCategoriaFragment.OnCategoriaSeleccionadoListener, IniciarAdministradorFragment.OnClickIniciarAdministrador, RegistrarServicioFragment.OnClickRegistrarServicio, RegistrarEncargadoFragment.OnClickRegistrarEncargado, ListaServicioFragment.OnServicioSeleccionadoListener, DetalleServicioFragment.onClickDetalleServicio, DetalleEncargadoFragment.onClickDetalleEncargado {


    /**
     * Variable que representa un calendario
     */
    lateinit var calendario: Calendar
    /**
     * Variable que representa el selector de la fecha
     */
    lateinit var selectorFecha: DatePickerDialog
    /**
     * Variable que repesenta la imagen seleccionada
     */
    lateinit var selectedImage: Uri
    /**
     * Variable que representa la lista de fragmentos de encargado
     */
    lateinit var fragmentListEncargados: ListaEncargadoFragment
    /**
     * Variable que representa la lista de fragmentos de servicio
     */
    lateinit var fragmentListServicios: ListaServicioFragment


    /**
     * Funcion en la creacion de la actividad
     */
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
        getKeyHash(this)
        fragmentListServicios = ListaServicioFragment()
        fragmentListServicios.servicios = Singleton.servicios


    }


    /**
     * Funcion para vlver atras
     */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Funcion en la creacion del menu options
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.administrador, menu)
        return true
    }

    /**
     * Funcion de las opciones del menu seleccionadas
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
     * Funcion que permite hacer la correspondiente navegacion por el drawer de administrador
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.item_agregar_servicio -> {
                abrirFragmento(RegistrarServicioFragment(), true, "")
            }
            R.id.item_agregar_encargado -> {
                abrirFragmento(RegistrarEncargadoFragment(), true, "RegistrarEncargado")
            }
            R.id.item_ver_perfil_fragment_administrador -> {
                abrirFragmento(VerPerfilFragment(), true, "")
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    /**
     * Funcion que permite reemplzar un fragmento
     */
    fun remplazarFragmento(fragment: Fragment, guardarPila: Boolean) {

        val transacion = supportFragmentManager.beginTransaction()
        transacion.replace(R.id.contenedor_administrador,
                fragment)
        if (guardarPila) {
            transacion.addToBackStack(null)
        }
        transacion.commit()
    }


    /**
     * Funcion que permite abrir un calendario
     */
    override fun abrirCalendario(fragment: Fragment) {
        calendario = Calendar.getInstance()
        val year = calendario.get(Calendar.YEAR)
        val month = calendario.get(Calendar.MONTH)
        val day = calendario.get(Calendar.DAY_OF_MONTH)
        selectorFecha = DatePickerDialog(fragment.context, DatePickerDialog.OnDateSetListener { v, year, month, day ->

        }, year, month, day)
        selectorFecha.show()
    }


    /**
     * Funcion que permite abrir el fragmento detalle encargado
     */
    override fun onEncargadoSeleccionado(pos: Int) {
        abrirFragmento(DetalleEncargadoFragment(), true, "")

    }

    /**
     * Funcion que permite abrir el fragmento de categoria seleccionada
     */
    override fun onCategoriaSeleccionado(pos: Int) {
        abrirFragmento(ListaServicioFragment(), true, "RegistrarServicio")
    }

    /**
     * Funcion que permite abrr fragmentos
     */
    override fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String) {


        if (tipo.equals("AbrirEncargados")) {
            fragmentListEncargados = fragment as
                    ListaEncargadoFragment

            fragmentListEncargados.encargados = Singleton.encargados
        }

        //else if (tipo.equals("RegistrarServicio")){
       //     fragmentListServicios = fragment as ListaServicioFragment
         //   fragmentListServicios.servicios = Singleton.servicios

     //   }
        val transaccion = supportFragmentManager.beginTransaction().replace(R.id.contenedor_administrador, fragment)
        if (estado) {
            transaccion.addToBackStack(null)
        }
        transaccion.commit()


    }

    /**
     * Funcion que permite agregar un encargado
     */
    override fun agregarEncargado(encargado: Encargado) {
        if (encargado.cedula.length != 0 && encargado.nombre.length != 0 && encargado.telefono.length != 0 && encargado.password.length != 0) {
            if (buscarEncargado(encargado.cedula) == null) {
                Singleton.encargados.add(encargado)
                fragmentListEncargados.adaptador.notifyItemInserted(Singleton.encargados.size - 1)
                Toast.makeText(this,"Encargado registrado con exito", Toast.LENGTH_SHORT).show()


            } else {
                throw Exception("El encargado ya existe con el mismo numero de cedula")
            }
        } else {
            throw Exception("Faltan datos por llenar")
        }

    }

    /**
     * Funcion que permite registrar un servico
     */
    override fun registrarServicio(servicio: Servicio) {
        if (servicio.nombre.length != 0 && servicio.ubicacion.length != 0 && servicio.horario.length != 0) {
            if (buscarServicio(servicio.nombre) == null) {
                Singleton.servicios.add(servicio)
                fragmentListServicios.adaptador.notifyItemInserted(Singleton.servicios.size - 1)
                Toast.makeText(this,"Servicio Registrado con exito", Toast.LENGTH_SHORT).show()

            } else {
                throw Exception("El nombre del servicio ya existe")
            }
        } else {
            throw Exception("Faltan datos por llenar")
        }
    }

    /**
     * Funcion que abre el fragmento de detalle servicio
     */
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

    /**
     * Funcion que permite guardar los cambios en el detalle del servicio
     */
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

    /**
     * Funcion que permite guardar los cambios en el detalle del encargado
     *
     */
    override fun guardarCambiosDetalleEncargado() {

    }

    /**
     * Metodo para buscar un servicio por el nombre
     */
    override fun buscarServicio(nombre: String): Servicio? {
        for (servicio in Singleton.servicios) {
            if (servicio.nombre.equals(nombre)) {
                Log.v("buscarservicio","no es null");
                return servicio
            }
        }
        Log.v("buscarservicio","es null");
        return null
    }

    /**
     * Metodo para buscar un encargado por la cedula
     */
    fun buscarEncargado(cedula: String): Encargado? {
        for (encargado in Singleton.encargados) {
            if (encargado.cedula.equals(cedula)) {
                return encargado
            }
        }
        return null

    }

    /**
     * Metodo para buscar un cliente por la cedula
     */
    fun buscarCliente(cedula: String): Cliente? {
        for (cliente in Singleton.clientes) {
            if (cliente.cedula.equals(cedula)) {
                return cliente
            }
        }
        return null
    }

}//Cierre de la actividad
