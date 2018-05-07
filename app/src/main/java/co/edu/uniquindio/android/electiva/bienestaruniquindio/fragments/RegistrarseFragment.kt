package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_registrarse.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegistrarseFragment : Fragment(), View.OnClickListener {

    lateinit var listener: OnClickRegistrarse

    interface OnClickRegistrarse {
        fun seleccionarFotoRegistrarse()
        fun registrarUsuario(nombre:String)
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
                listener.registrarUsuario(txtNombres.text.toString())
            }
        }
    }


}//Cierre del fragmento
