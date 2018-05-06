package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_detalle_servicio.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetalleServicioFragment : Fragment(), View.OnClickListener {

    lateinit var listener: onClickDetalleServicio
    var modificable = false

    /**
     * Interface para el fragmnto detalle servicio, permite acceder a la funciones del fragmento
     */
    interface onClickDetalleServicio {
        fun seleccionarFoto()
        fun guardarCambiosDetalleServicio()
        fun cambiarEstado(estado: Boolean)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_servicio, container, false)
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_modificar_servicio.setOnClickListener(this)
        btn_cancelar.setOnClickListener(this)
        btn_guardar_cambios.setOnClickListener(this)
        btn_modificar_foto.setOnClickListener(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as onClickDetalleServicio
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz onClickDetalleServicio")
            }
        }
    }

    /**
     * Funcion en la cual se añaden las funcionalidades en los botones
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_modificar_servicio -> {
                modificable = true
                listener.cambiarEstado(true)
            }
            R.id.btn_modificar_foto -> {
                listener.seleccionarFoto()
            }
            R.id.btn_guardar_cambios -> {
                listener.guardarCambiosDetalleServicio()
            }
            R.id.btn_cancelar -> {
                modificable = false
                listener.cambiarEstado(false)
            }

        }
    }

}
