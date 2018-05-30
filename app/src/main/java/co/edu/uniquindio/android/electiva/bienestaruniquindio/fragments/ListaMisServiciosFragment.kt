package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.AdaptadorServicio
import kotlinx.android.synthetic.main.fragment_lista_mis_servicios.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Clase que representa el fragmento de la lista de los srvicios del cliente
 */
class ListaMisServiciosFragment : Fragment(), AdaptadorServicio.OnClickAdapatadorDeServicio {
    /**
     * Variable que representa la lista de servicios del cliente
     */
    lateinit var mis_servicios: ArrayList<Servicio>
    /**
     * Variable que representa el adaptador de servicios del cliente
     */
    lateinit var adaptador: AdaptadorServicio
    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnServicioSeleccionadoListener

    /**
     * Interface que soporta las funciones del fragmento
     */
    interface OnServicioSeleccionadoListener {
        fun onServicioSeleccionado(pos: Int)

    }

    /**
     * Funcion en la creacion del fragmento
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    /**
     * Funcion en la creacion de la vista
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_mis_servicios, container, false)
    }

    /**
     * Funcion en la creacion de la actividad
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adaptador = AdaptadorServicio(mis_servicios, this)
        lista_mis_servicios.adapter = adaptador
        lista_mis_servicios.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    /**
     * Funcion onAttach
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnServicioSeleccionadoListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnServicioSeleccionadoListener")
            }
        }
    }

    /**
     * Funcion de la posicin del click
     */
    override fun onClickPosition(pos: Int) {
        listener.onServicioSeleccionado(pos)
    }

}
