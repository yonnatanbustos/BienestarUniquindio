package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Categoria
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import kotlinx.android.synthetic.main.fragment_categoria.view.*

/**
 * Clase que representa el adapatador generico
 */
class AdaptadorGenerico(var fragmento: Fragment) : RecyclerView.Adapter<AdaptadorGenerico.EncargadoViewHolder>() {


    /**
     * Variable que representa la lista de categorias del apadatador
     */
    lateinit var categorias: ArrayList<Categoria>
    /**
     * Variable que representa la lista de encargados del adaptador
     */
    lateinit var encargados: ArrayList<Encargado>

    /**
     * Variable que representa el listener de encargados
     */
    private lateinit var listenerEncargado: OnClickAdaptadorDeEncargado
    /**
     * Variable que representa el listener de categoria
     */
    private lateinit var listenerCategoria: OnClickAdaptadorDeCategoria

    init {
        listenerEncargado = fragmento as OnClickAdaptadorDeEncargado
    }

    init {
        listenerCategoria = fragmento as OnClickAdaptadorDeCategoria

    }

    /**
     * Interface para el adapatador de encrgadi
     */
    interface OnClickAdaptadorDeEncargado {
        fun onClickPosition(pos: Int)
    }

    /**
     * Interface para el adapadot de categoria
     */
    interface OnClickAdaptadorDeCategoria {
        fun onClickPosition(pos: Int)
    }


    /**
     * Funcion en la creacion del view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            EncargadoViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_encargado, parent, false)
        return EncargadoViewHolder(v)
    }

    /**
     * Funcion que retorna el tamaño de la lista
     */
    override fun getItemCount(): Int {
        return encargados.size
    }

    /**
     * Funcion del bin view holder
     */
    override fun onBindViewHolder(holder: EncargadoViewHolder, position: Int) {
        holder?.bindEncargado(encargados.get(position))
    }

    inner class EncargadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val nombre: TextView = itemView.nombre

        /**
         * se asigna el evento al item
         */
        init {
            listenerEncargado = fragmento as OnClickAdaptadorDeEncargado
        }

        fun bindEncargado(encargado: Encargado) {
            nombre.text = encargado.nombre
        }


        override fun onClick(v: View?) {
            Log.d("PERSONA", "Elemento " + adapterPosition + " clickeado. " + nombre.text)
            listenerEncargado.onClickPosition(adapterPosition)
        }
    }

    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val nombre: TextView = itemView.nombre

        /**
         * se asigna el evento al item
         */
        init {
            listenerCategoria = fragmento as OnClickAdaptadorDeCategoria
        }

        fun bindCategoria(categoria: Categoria) {
            nombre.text = categoria.nombre
        }


        override fun onClick(v: View?) {
            Log.d("PERSONA", "Elemento " + adapterPosition + " clickeado. " + nombre.text)
            listenerCategoria.onClickPosition(adapterPosition)
        }
    }


}