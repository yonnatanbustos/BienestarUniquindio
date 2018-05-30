package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Clase que representa el fragmento con el detalle del servico del cliente
 */
class DetalleServicioCliente : Fragment(), View.OnClickListener {

    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnClickDetalleServicioCliente

    /**
     * Interface que soporta las funciones del fragmento
     */
    interface OnClickDetalleServicioCliente{
        fun solicitarServicio()
    }

    /**
     * Funcion que permite la creacion de la ista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_servicio_cliente, container, false)
    }

    /**
     * Funcion onAttach
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnClickDetalleServicioCliente
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnClickDetalleServicioCliente")
            }
        }
    }

    /**
     * Funcion cuando se click en el fragmento
     */
    override fun onClick(v: View) {

        when(v.id){
            R.id.fab_btn_solicitar_servicio ->{
                listener.solicitarServicio()
            }
        }

    }

}
