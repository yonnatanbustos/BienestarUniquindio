package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.util.selecionarIdioma
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Categoria
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Cliente
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments.RegistrarseFragment
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.Singleton
import co.edu.uniquindio.android.electiva.bienestaruniquindio.vo.Dependencia
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_iniciar_sesion.*
import kotlinx.android.synthetic.main.fragment_registrarse.*

private const val SELECT_FILE = 1

class LoginActivity : AppCompatActivity(),View.OnClickListener, RegistrarseFragment.OnClickRegistrarse {

    /**
     * Funcion que permite crear la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        setTitle(R.string.title_activity_login)
        //Se instacian las categorias
        instanciarCategorias()
        //Se instancias las dependencias
        instanciarDependencias()
        //Se instancian algunos servicios
        instanciarServicios()
        btnIniciarSesion.setOnClickListener(this)
        labelRegistrarse.setOnClickListener(this)
    }

    fun instanciarServicios(){
        Singleton.servicios.add(Servicio("Psicologia", "Cita con psicologo", "Bienestar universtario", "Aula de cita", "Lunes de 2pm a 6pm"))
        Singleton.servicios.add(Servicio("Futbol", "Asistencia para clases de futbol","Cancha de la universidad", "", "Martes de 11am a 1pm"))
    }

    /**
     * Metodo que crea las categorias por defecto de la aplicacion
     */
    fun instanciarCategorias(){
        Singleton.categorias.add(Categoria("Deporte"))
        Singleton.categorias.add(Categoria("Salud"))
        Singleton.categorias.add(Categoria("Cultural"))
        Singleton.categorias.add(Categoria("Otros"))
    }

    /**
     * Metodo que crea las dependencias por defecto de la aplicacion
     */
    private fun instanciarDependencias(){
        Singleton.dependencias.add(Dependencia("FACULTAD DE INGENIERIA"))
        Singleton.dependencias.add(Dependencia("FACULTAD DE CIENCIAS BASICAS Y TECNOLOGIAS"))
        Singleton.dependencias.add(Dependencia("FACULTAD DE EDUCACION"))
        Singleton.dependencias.add(Dependencia("FACULTAD DE CIENCIAS HUMANAS Y BELLAS ARTES"))
        Singleton.dependencias.add(Dependencia("FACULTAD DE CIENCIAS ECONOMICAS Y ADMINISTRATIVAS"))
        Singleton.dependencias.add(Dependencia("INGENIERIA DE CIENCIAS DE LA SALUD"))
        Singleton.dependencias.add(Dependencia("FACULTAD DE CIENCIAS AGROINDUSTRIALES"))
        //-----------------------------------------------------------------------------------------
        Singleton.dependencias.add(Dependencia("VICERECTORIA ACADEMICA"))
        Singleton.dependencias.add(Dependencia("VICERECTORIA ADMINISTRATIVA"))
        Singleton.dependencias.add(Dependencia("VICERECTORIA EXTENSION Y DESARROLLO SOCIAL"))
    }

    /**
     * Funcion que crea el menu de opciones
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.login, menu)
        return true
    }

    /**
     * Funcion que escucha el click sobre los componentes de la actvidad
     */
    override fun onClick(v: View) {
        when (v.id) {
          R.id.btnIniciarSesion -> {
              iniciarSesion(txtUsuario.text.toString(), txtContrasena.text.toString())
              txtUsuario.text = null
              txtContrasena.text = null

          }
          R.id.labelRegistrarse -> {
              supportFragmentManager.beginTransaction().replace(R.id.contenedor_login, RegistrarseFragment()).addToBackStack(null).commit()
          }
        }
    }

    /**
     * Funcion que escucha cuando un item del menu de opciones es seleccionado
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
     * Funcion que permite iniciar sesion
     */
    fun iniciarSesion(cedula: String, password: String){
        if(cedula.equals("") and password.equals("")){
            val intent = Intent(this, AdministradorActivity::class.java)
            startActivity(intent)
        }
        else{
            var cliente: Cliente? = buscarCliente(cedula)
            if(cliente != null){
                if(cliente.password.equals(password)){
                    val intent = Intent(this, ClienteActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }else{
                var encargado: Encargado? = buscarEncargado(cedula)
                if(encargado != null){
                    if(encargado.password.equals(password)){
                        val intent = Intent(this, EncargadoActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Contraseña incorrecta", Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(this,"El usuario no existe", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    /**
     * Funcion que permite buscar un cliente
     */
    fun buscarCliente(cedula: String): Cliente?{
        for(cliente in Singleton.clientes){
            if(cliente.cedula.equals(cedula)){
                return cliente
            }
        }
        return null
    }

    /**
     * Funcion que permite buscar un encargado
     */
    fun buscarEncargado(cedula: String): Encargado?{
        for(encargado in Singleton.encargados){
            if(encargado.cedula.equals(cedula)){
                return encargado
            }
        }
        return null
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

    /**
     * Funcion del resultado de la actividad
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_FILE) {
            val imageUri: Uri = data.data
            foto_registro_usuario.setImageURI(imageUri)
        }

    }

    /**
     * Funcion que permite registrar un cliente en la aplicacion desde el fragmento de RegistrarFragment
     */
    override fun registrarCliente(cliente: Cliente) {
        if (cliente.cedula.length != 0 && cliente.nombres.length != 0 && cliente.telefono.length != 0 && cliente.password.length != 0) {
            if (buscarCliente(cliente.cedula) == null) {
                Singleton.clientes.add(cliente)
                Toast.makeText(this, cliente.nombres + " ha sido usted registrado exitosamente", Toast.LENGTH_SHORT).show()

            } else {
                throw Exception("El numero de cedula ya ha sido registrado")
            }
        } else {
            throw Exception("Faltan datos por llenar")
        }
    }

    /**
     * Funcion que carga las dependencias al spinner de depencias del fragmento de registro de cliente
     */
    fun cargarSpinner (){
        var nameDependencias : ArrayList<String> = ArrayList()
        for(dependencia in Singleton.dependencias){
            nameDependencias.add(dependencia.nombre)
        }

        var adaptadorDependencias: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, nameDependencias)
        adaptadorDependencias.setDropDownViewResource(android.R.layout.simple_spinner_item)
        comboDependencia.adapter = adaptadorDependencias

    }

}//Cierre de la actividad
