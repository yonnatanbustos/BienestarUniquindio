package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_detalle_encargado.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetalleEncargadoFragment : Fragment(), View.OnClickListener {

    lateinit var listener : onClickDetalleEncargado

    interface onClickDetalleEncargado{
        fun cambiarEstadoVentanaDetalleEncargado(estado : Boolean)
        fun guardarCambiosDetalleEncargado()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab_btn_editar_encargado.setOnClickListener(this)
        fab_btn_eliminar_detalle_encargado.setOnClickListener(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as onClickDetalleEncargado
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz onClickDetalleEncargado")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_encargado, container, false)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.fab_btn_editar_encargado -> {
                listener.cambiarEstadoVentanaDetalleEncargado(true)
            }
        }
    }

}
