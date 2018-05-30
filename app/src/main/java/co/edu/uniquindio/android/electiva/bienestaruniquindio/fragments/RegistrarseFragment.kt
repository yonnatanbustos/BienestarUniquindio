package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Cliente
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.Singleton
import co.edu.uniquindio.android.electiva.bienestaruniquindio.vo.Tipo
import kotlinx.android.synthetic.main.fragment_registrarse.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Clase que representa el registro de un cliente
 *
 */
class RegistrarseFragment : Fragment(), View.OnClickListener {

    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnClickRegistrarse

    /**
     * Interface que soporta los metodos del fragmento
     */
    interface OnClickRegistrarse {
        fun seleccionarFotoRegistrarse()
        fun registrarCliente(cliente: Cliente)
    }

    /**
     * Se añade funcionalidad a los botones del fragmento
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_seleccionar_foto_registro.setOnClickListener(this)
        btn_registrar_usuario.setOnClickListener(this)
    }

    /**
     * Funcion que inicializa el listener del fragmento
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnClickRegistrarse
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnClickRegistrarse")
            }
        }
    }

    /**
     * Funcion en la creacio de la vista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrarse, container, false)
    }

    /**
     * Funcion que permite añadir acciones a los botones del fragmento DetalleEncargado
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_seleccionar_foto_registro -> {
                listener.seleccionarFotoRegistrarse()
            }
            R.id.btn_registrar_usuario -> {
                val cedula = txtCedula.text.toString()
                val nombres = txtNombres.text.toString()
                val apellidos = txtApellidos.text.toString()
                val email = txtEmail.text.toString()
                val telefono = txtTelefono.text.toString()

                val tipoCliente = comboTipo.selectedItem.toString()
                var tipo = Tipo.ESTUDIANTE
                if(tipoCliente.equals("PROFESOR") || tipoCliente.equals("TEACHER")){
                    tipo = Tipo.DOCENTE
                }
                if(tipoCliente.equals("ADMINISTRATIVO") || tipoCliente.equals("ADMINISTRATIVE")){
                    tipo = Tipo.DOCENTE
                }

                //listener.registrarUsuario(txtNombres.text.toString())
            }
        }
    }

    fun cargarSpinner (){
        var nameDependencias : ArrayList<String> = ArrayList()
        for(dependencia in Singleton.dependencias){
            nameDependencias.add(dependencia.nombre)
        }


        //var adaptadorDependencias:ArrayAdapter<String> = ArrayAdapter<String>(this, R.id.comboDependencia, nameDependencias)

    }

}//Cierre del fragmento
