package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import kotlinx.android.synthetic.main.fragment_categoria.view.*

class AdaptadorEncargado(var encargados: ArrayList<Encargado>, var fragmento: Fragment) : RecyclerView.Adapter<AdaptadorEncargado.EncargadoViewHolder>() {


    private lateinit var listener: OnClickAdaptadorDeEncargado

    init {
        listener = fragmento as OnClickAdaptadorDeEncargado
    }

    interface OnClickAdaptadorDeEncargado {
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
            listener = fragmento as OnClickAdaptadorDeEncargado
        }

        fun bindEncargado(encargado: Encargado) {
            nombre.text = encargado.nombre
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            Log.d("PERSONA", "Elemento " + adapterPosition + " clickeado. " + nombre.text)
            listener.onClickPosition(adapterPosition)
        }
    }
}