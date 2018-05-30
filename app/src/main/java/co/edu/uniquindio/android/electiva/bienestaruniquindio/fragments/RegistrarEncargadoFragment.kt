package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Administrador
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.ManagerFireBase
import kotlinx.android.synthetic.main.fragment_lista_encargado.*
import kotlinx.android.synthetic.main.fragment_lista_encargado.view.*
import kotlinx.android.synthetic.main.fragment_registrar_encargado.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val SELECT_FILE = 1

/**
 * Clase que representa el fragmento del registro de encargado
 */
class RegistrarEncargadoFragment : Fragment(), View.OnClickListener {

    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnClickRegistrarEncargado
    /**
     * Variable que representa el administrador de la base de datos
     */
    var managerFB: ManagerFireBase? = null

    /**
     * Interface que soporta los metoso del fragmento
     */
    interface OnClickRegistrarEncargado {
        fun agregarEncargado(encargado: Encargado)
        fun seleccionarFoto()
        fun buscarServicio(nombre: String): Servicio?
    }

    /**
     * Funcion en la creacion de la ista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        ManagerFireBase.instanciar(this)
        managerFB = ManagerFireBase.instant
        return inflater.inflate(R.layout.fragment_registrar_encargado, container, false)

    }

    /**
     * Funcion en la creacion de la actividad
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_registrar_encargado.setOnClickListener(this)
        btn_seleccionar_foto_encargado_registrar.setOnClickListener(this)
        ManagerFireBase.instanciar(this)
        managerFB = ManagerFireBase.instant


    }


    /**
     * Funcion onAttach
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnClickRegistrarEncargado
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnPersonajeSeleccionadoListener")
            }
        }

    }

    /**
     * Funcion cuando se da click en el framento
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_registrar_encargado -> {
                try {

                    var nombre = txt_nombre_encargado_registrar.text.toString()
                    var cedula = txt_cedula_encargado_registrar.text.toString()
                    var telefono = txt_telefono_encargado_registrar.text.toString()
                    var password = txt_contrasena_encargado_registrar.text.toString()
                    var nombre_servicio = combo_servicios_gestionar_encargado_registrar.selectedItem.toString()

                   val servicio: Servicio? = listener.buscarServicio(nombre_servicio)

                    var foto: ImageView = foto_encargado_registrar.findViewById(R.id.foto_encargado_registrar)
                    var encargado = Encargado(cedula, nombre, telefono, password, null)
                    //listener.agregarEncargado(encargado)
                    managerFB!!.insertarEncargado(encargado)

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            R.id.btn_seleccionar_foto_encargado_registrar -> {
                listener.seleccionarFoto()


            }
        }

    }


}//Cierre del fragmento
