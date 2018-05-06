package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.AdaptadorEncargado
import kotlinx.android.synthetic.main.fragment_lista_encargado.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListaEncargadoFragment : Fragment(), AdaptadorEncargado.OnClickAdaptadorDeEncargado, View.OnClickListener {

    lateinit var encargados: ArrayList<Encargado>
    lateinit var adaptador: AdaptadorEncargado
    lateinit var listener: OnEncargadoSeleccionadoListener

    interface OnEncargadoSeleccionadoListener {
        fun onEncargadoSeleccionado(pos: Int)
        fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        encargados.add(Encargado("Yonnatan"))
        encargados.add(Encargado("El Flaco"))
        encargados.add(Encargado("Alzate"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_encargado, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAgregarEncargado.setOnClickListener(this)

        adaptador = AdaptadorEncargado(encargados, this)
        lista_encargado.adapter = adaptador
        lista_encargado.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnEncargadoSeleccionadoListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnPersonajeSeleccionadoListener")
            }
        }
    }

    override fun onClickPosition(pos: Int) {
        listener.onEncargadoSeleccionado(pos)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAgregarEncargado -> {
                listener.abrirFragmento(RegistrarEncargadoFragment(), true, "RegistrarEncargado")
            }
        }
    }
}
