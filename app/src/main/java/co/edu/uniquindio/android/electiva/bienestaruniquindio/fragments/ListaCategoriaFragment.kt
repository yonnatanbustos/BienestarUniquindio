package co.edu.uniquindio.android.electiva.bienestaruniquindio.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.ListAdapter

import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Categoria
import co.edu.uniquindio.android.electiva.bienestaruniquindio.util.AdaptadorCategoria
import kotlinx.android.synthetic.main.fragment_lista_categoria.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListaCategoriaFragment : Fragment(), AdaptadorCategoria.OnClickAdaptadorDeCategoria, View.OnClickListener {


    lateinit var categorias: ArrayList<Categoria>
    lateinit var adaptador: AdaptadorCategoria
    lateinit var listener: OnCategoriaSeleccionadoListener

    /*
* Intergaz para realizar la conexion con la actividad
*/

    interface OnCategoriaSeleccionadoListener {
        fun onCategoriaSeleccionado(pos: Int)
        fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        categorias = ArrayList<Categoria>()
        categorias.add(Categoria("Deportes"))
        categorias.add(Categoria("Salud"))
        categorias.add(Categoria("Cultural"))


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_categoria, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAgregarServicio.setOnClickListener(this)

        adaptador = AdaptadorCategoria(categorias, this)
        lista_categoria.adapter =adaptador
        lista_categoria.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            try {
                listener = context as OnCategoriaSeleccionadoListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnCategoriaSeleccionadoListener")
            }
        }
    }

    override fun onClickPosition(pos: Int) {
        listener.onCategoriaSeleccionado(pos)
    }




    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAgregarServicio -> {
                listener.abrirFragmento(RegistrarServicioFragment(), true, "RegistrarServicio")

            }
        }
    }


}
