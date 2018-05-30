package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_registrar_servicio.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Clase que representa el registro de un servicio
 *
 */
class RegistrarServicioFragment : Fragment(), View.OnClickListener {

    /**
     * Variable que representa el listener del fragmento
     */
    lateinit var listener: OnClickCalendario

    /**
     * Interface que soporta los metodos del calendario
     */
    interface OnClickCalendario {
        fun abrirCalendario(fragment: Fragment)
    }

    /**
     * Funcion en la creacio de la vista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_servicio, container, false)
    }

    /**
     * Funcion en la creacion de la actividad
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnCalendario.setOnClickListener(this)
    }


    /**
     * Funcion onAttach
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnClickCalendario
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnPersonajeSeleccionadoListener")
            }
        }
    }

    /**
     * Funcion cuando da click en el fragmento
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnCalendario -> {
                listener.abrirCalendario(this)
            }
        }
    }


}
