package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_lista_encargado.*
import kotlinx.android.synthetic.main.fragment_lista_encargado.view.*
import kotlinx.android.synthetic.main.fragment_registrar_encargado.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegistrarEncargadoFragment : Fragment(), View.OnClickListener {


    lateinit var listener: OnClickRegistrarEncargado

    interface OnClickRegistrarEncargado {
        fun agregarEncargado(nombre: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registrar_encargado, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnRegistrar.setOnClickListener(this)
    }

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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegistrar -> {
                var nombre = txtNombreEncargado.text
                listener.agregarEncargado(nombre.toString())

            }
        }

    }


}
