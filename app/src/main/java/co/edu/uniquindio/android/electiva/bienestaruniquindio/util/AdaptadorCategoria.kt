package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Categoria

import kotlinx.android.synthetic.main.fragment_categoria.view.*

class AdaptadorCategoria(var categorias: ArrayList<Categoria>, var fragmento: Fragment) : RecyclerView.Adapter<AdaptadorCategoria.CategoriaViewHolder>() {


    private lateinit var listener: OnClickAdaptadorDeCategoria

    init {
        listener = fragmento as OnClickAdaptadorDeCategoria
    }

    interface OnClickAdaptadorDeCategoria {
        fun onClickPosition(pos: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CategoriaViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_categoria, parent, false)
        return CategoriaViewHolder(v)
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder?.bindCategoria(categorias.get(position))

    }


    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val nombre: TextView = itemView.nombre

        /**
         * se asigna el evento al item
         */
        init {
            listener = fragmento as OnClickAdaptadorDeCategoria
        }

        fun bindCategoria(categoria: Categoria) {
            nombre.text = categoria.nombre
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Clickeado de ${categoria.nombre}", Toast.LENGTH_LONG).show()
            }
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            Log.d("PERSONA", "Elemento " + adapterPosition + " clickeado. " + nombre.text)
            listener.onClickPosition(adapterPosition)
        }
    }
}