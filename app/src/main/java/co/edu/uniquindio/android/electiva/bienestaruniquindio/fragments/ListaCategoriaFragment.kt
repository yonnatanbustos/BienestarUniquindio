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
 * Clase que representa el fragmento de la lista de categoria
 */
class ListaCategoriaFragment : Fragment(), AdaptadorCategoria.OnClickAdaptadorDeCategoria, View.OnClickListener {

    /**
     * Variable que representa la lista de categorias
     */
    lateinit var categorias: ArrayList<Categoria>
    /**
     * Variable que representa el adaptador de la clase
     */
    lateinit var adaptador: AdaptadorCategoria
    /**
     * Variable que representa el listener de la clase
     */
    lateinit var listener: OnCategoriaSeleccionadoListener

    /**
     * Interface que soporta las funciones del fragmento, necesario para cominucar con la actividad
     */
    interface OnCategoriaSeleccionadoListener {
        fun onCategoriaSeleccionado(pos: Int)
        fun abrirFragmento(fragment: Fragment, estado: Boolean, tipo: String)
    }

    /**
     * Funcion en la creacion del fragmento
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        categorias = ArrayList<Categoria>()
        categorias.add(Categoria("Deportes"))
        categorias.add(Categoria("Salud"))
        categorias.add(Categoria("Cultural"))


    }

    /**
     * Funcion en la creacion de la vista del fragmento
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_categoria, container, false)
    }

    /**
     * Funcion en la creacion de la actividad
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAgregarServicio.setOnClickListener(this)

        adaptador = AdaptadorCategoria(categorias, this)
        lista_categoria.adapter =adaptador
        lista_categoria.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    /**
     * Funcion del¿ la creacion de las opciones del menu
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

    }

    /**
     * Funcion onAttach
     */
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

    /**
     * Funcion de la posicion del click
     */
    override fun onClickPosition(pos: Int) {
        listener.onCategoriaSeleccionado(pos)
    }

    /**
     * Funcion que se abre cuando hay un click
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAgregarServicio -> {
                listener.abrirFragmento(RegistrarServicioFragment(), true, "RegistrarServicio")

            }
        }
    }


}
