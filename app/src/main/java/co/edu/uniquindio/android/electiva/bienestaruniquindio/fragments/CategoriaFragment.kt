package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import kotlinx.android.synthetic.main.fragment_categoria.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CategoriaFragment : Fragment() {

    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnCategoriaListenr

    /**
     * Interface que soporta los metodos del fragmento
     */
    interface OnCategoriaListenr {
        fun OnCategoria(fragment: Fragment)
    }

    /**
     * Funcion de la creacion de la vista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categoria, container, false)
    }

    /**
     * Funcion en la creacion de la actividad
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * Funcion onAttach
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnCategoriaListenr
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnCategoriaListenr")
            }
        }
    }

}//Cierre del fragmento
