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

class AdaptadorGenerico(var fragmento: Fragment) : RecyclerView.Adapter<AdaptadorGenerico.EncargadoViewHolder>() {


    lateinit var categorias: ArrayList<Categoria>
    lateinit var encargados: ArrayList<Encargado>

    private lateinit var listenerEncargado: OnClickAdaptadorDeEncargado
    private lateinit var listenerCategoria: OnClickAdaptadorDeCategoria

    init {
        listenerEncargado = fragmento as OnClickAdaptadorDeEncargado
    }

    init {
        listenerCategoria = fragmento as OnClickAdaptadorDeCategoria

    }

    interface OnClickAdaptadorDeEncargado {
        fun onClickPosition(pos: Int)
    }

    interface OnClickAdaptadorDeCategoria {
        fun onClickPosition(pos: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            EncargadoViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_encargado, parent, false)
        return EncargadoViewHolder(v)
    }


    override fun getItemCount(): Int {
        return encargados.size
    }

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