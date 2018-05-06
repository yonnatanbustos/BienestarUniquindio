package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_iniciar_administrador.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class IniciarAdministradorFragment : Fragment(), View.OnClickListener {

    private lateinit var listener: OnClickIniciarAdministrador

    interface OnClickIniciarAdministrador {
        fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_iniciar_administrador, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnEncargados.setOnClickListener(this)
        btnServicios.setOnClickListener(this)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Activity) {
            try {
                listener = context as OnClickIniciarAdministrador
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnServicios -> {
                listener.abrirFragmento(ListaCategoriaFragment(), true, "AbrirCategorias")

            }
            R.id.btnEncargados -> {
                listener.abrirFragmento(ListaEncargadoFragment(), true, "AbrirEncargados")
            }
        }
    }


}
