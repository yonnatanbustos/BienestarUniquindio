package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import kotlinx.android.synthetic.main.fragment_servicio.view.*

/**
 * Clase que representa el adapatador de servicio
 */
class AdaptadorServicio(var servicios: ArrayList<Servicio>, var fragmento: Fragment) : RecyclerView.Adapter<AdaptadorServicio.ServicioViewHolder>() {

    /**
     * Variable que representa el listenerdel adaptador
     */
    private lateinit var listener: OnClickAdapatadorDeServicio

    init {
        listener = fragmento as OnClickAdapatadorDeServicio
    }

    /**
     * Interface para el adaptador de servicio
     */
    interface OnClickAdapatadorDeServicio {
        fun onClickPosition(pos: Int)
    }


    /**
     * Funcion en la creacion de view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ServicioViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_servicio, parent, false)
        return ServicioViewHolder(v)
    }

    /**
     * Funcion que permite obtener el item
     */
    override fun getItemCount(): Int {
        return servicios.size
    }

    /**
     * Funcion del bind view holder
     */
    override fun onBindViewHolder(holder: ServicioViewHolder, position: Int) {
        holder?.bindServicio(servicios.get(position))

    }


    inner class ServicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val nombre: TextView = itemView.label_nombre

        /**
         * se asigna el evento al item
         */
        init {
            listener = fragmento as OnClickAdapatadorDeServicio
        }

        fun bindServicio(encargado: Servicio) {
            nombre.text = encargado.nombre
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            Log.d("PERSONA", "Elemento " + adapterPosition + " clickeado. " + nombre.text)
            listener.onClickPosition(adapterPosition)
        }
    }
}