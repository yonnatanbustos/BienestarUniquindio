package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.android.electiva.bienestaruniquindio.R
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Cliente
import kotlinx.android.synthetic.main.fragment_cliente.view.*

/**
 * Clase que representa el adaptador de cliente
 */
class AdaptadorCliente(var clientes_x_encargado: ArrayList<Cliente>, var fragmento: Fragment) : RecyclerView.Adapter<AdaptadorCliente.ClienteViewHolder>() {

    /**
     * Variable que repreenta el listener del adaptador
     */
    private lateinit var listener: OnClickAdaptadorDeCliente

    /**
     * Inicializacion del listener de la clase
     */
    init {
        listener = fragmento as OnClickAdaptadorDeCliente
    }

    /**
     * Interface del adaptador de cliente
     */
    interface OnClickAdaptadorDeCliente {
        fun onClickPosition(pos: Int)
    }

    /**
     * Funcion en la creacion del view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ClienteViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_cliente, parent, false)
        return ClienteViewHolder(v)
    }

    /**
     * Funcion que retorna el tamaño de la lista
     */
    override fun getItemCount(): Int {
        return clientes_x_encargado.size
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        holder?.bindCliente(clientes_x_encargado.get(position))

    }

    /**
     * Inner de la clase
     */
    inner class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val nombre: TextView = itemView.nombre_cliente

        /**
         * se asigna el evento al item
         */
        init {
            listener = fragmento as OnClickAdaptadorDeCliente
        }

        fun bindCliente(cliente: Cliente) {
            nombre.text = cliente.nombres
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            Log.d("PERSONA", "Elemento " + adapterPosition + " clickeado. " + nombre.text)
            listener.onClickPosition(adapterPosition)
        }
    }
}