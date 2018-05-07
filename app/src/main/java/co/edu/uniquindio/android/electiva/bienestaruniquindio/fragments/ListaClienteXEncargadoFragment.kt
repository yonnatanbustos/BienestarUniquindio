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
 * A simple [Fragment] subclass.
 *
 */
class ListaClienteXEncargadoFragment : Fragment(), AdaptadorCliente.OnClickAdaptadorDeCliente {

    lateinit var clientes_x_encargado: ArrayList<Cliente>
    lateinit var adaptador: AdaptadorCliente
    lateinit var listener: OnClickClienteXEncargadoSeleccionadoListener

    interface OnClickClienteXEncargadoSeleccionadoListener {
        fun OnClienteXEncargadoSeleccionado(pos: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_cliente_x_encargado, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        clientes_x_encargado = ArrayList<Cliente>()
        clientes_x_encargado.add(Cliente("Deportes"))
        clientes_x_encargado.add(Cliente("Salud"))
        clientes_x_encargado.add(Cliente("Cultural"))


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adaptador = AdaptadorCliente(clientes_x_encargado, this)
        lista_clientes_x_encargado.adapter = adaptador
        lista_clientes_x_encargado.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

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

    override fun onClickPosition(pos: Int) {
        listener.OnClienteXEncargadoSeleccionado(pos)

    }


}
