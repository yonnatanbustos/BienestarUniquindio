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


import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Cliente
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.AdaptadorCliente
import kotlinx.android.synthetic.main.fragment_lista_cliente_x_encargado.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Clase que representa el fragmento de la lista de clientes por encargado
 */
class ListaClienteXEncargadoFragment : Fragment(), AdaptadorCliente.OnClickAdaptadorDeCliente {

    /**
     * Variable que representa la lista de cliente por encargador
     */
    lateinit var clientes_x_encargado: ArrayList<Cliente>
    /**
     * Variable que representa el adaptador de la clase
     */
    lateinit var adaptador: AdaptadorCliente
    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnClickClienteXEncargadoSeleccionadoListener

    /**
     * Interface que soporta las funciones del fragmento
     */
    interface OnClickClienteXEncargadoSeleccionadoListener {
        fun OnClienteXEncargadoSeleccionado(pos: Int)
    }

    /**
     * Funcion en la creacion de la vista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_cliente_x_encargado, container, false)
    }

    /**
     * Funcion en la creacion del fragmento
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        //clientes_x_encargado = ArrayList<Cliente>()
        //clientes_x_encargado.add(Cliente("Faber"))
        //clientes_x_encargado.add(Cliente("Einer"))
        //clientes_x_encargado.add(Cliente("El Flaco"))


    }

    /**
     * Funcion en la creacion de ala activisad
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adaptador = AdaptadorCliente(clientes_x_encargado, this)
        lista_clientes_x_encargado.adapter = adaptador
        lista_clientes_x_encargado.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    /**
     * Funcion onAttcah
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnClickClienteXEncargadoSeleccionadoListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnClickClienteXEncargadoSeleccionadoListener")
            }
        }
    }

    /**
     * Funcion de la posicion del click
     */
    override fun onClickPosition(pos: Int) {
        listener.OnClienteXEncargadoSeleccionado(pos)

    }


}
