package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import android.support.v4.app.Fragment
import android.util.Log
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Categoria
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Cliente
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Encargado
import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.Servicio
import com.google.firebase.database.*

/**
 * Objeto administrador de la base de datos
 */
object ManagerFireBase {

    /**
     * Variable que representa la base de datos
     */
    var database: FirebaseDatabase? = null
    /**
     * Variable que referencia la base de datos
     */
    var dataRef: DatabaseReference? = null
    /**
     * Variable que permite administrar la base de datos
     */
    var instant: ManagerFireBase? = null
    /**
     * Variable que representa un fragmento
     */
    var fragment: Fragment? = null
    /**
     * Variabler que representa un listener
     */
    var listener: onActualizarAdaptador? = null

    /**
     * Interface para actualizar los adaptadores
     */
    interface onActualizarAdaptador {
        fun actualizarAdaptadorServicio(servicio: Servicio)
        fun actualizarAdaptadorCliente(cliente: Cliente)
        fun actualizarAdaptadorEncargado(encargado: Encargado)
        fun actualizarAdaptadorCategoria(categoria: Categoria)
    }

    /**
     * Funcion que permite inicializar un fragmento
     */
    private fun inicializar(fragment: Fragment): ManagerFireBase {
        val instant = ManagerFireBase
        instant!!.database = FirebaseDatabase.getInstance()
        instant!!.dataRef = database!!.reference
        instant!!.fragment = fragment
        //listener= fragment as onActualizarAdaptador
        return instant!!
    }

    /**
     * Funcion que permite instaciar un fragmento
     */
    fun instanciar(fragment: Fragment) {
        if (instant == null) {
            instant = inicializar(fragment)
        }
    }

    /**
     * Funcion que permite insertar un cliente a la base de datos
     */
    fun insertarCliente(cliente: Cliente) {
        dataRef!!.push().setValue(cliente)
    }

    /**
     * Funcion que permite insertar un encargado a la base de datos
     */
    fun insertarEncargado(encargado: Encargado) {
        dataRef!!.push().setValue(encargado)
    }



    /**
     * Funcion que permite insertar un servicio a la base de datos
     */
    fun insertarServicio(servicio: Servicio) {
        dataRef!!.push().setValue(servicio)
    }

    /**
     * Funcion que permite eliminar un encargado a la base de datos
     */
    fun eliminarEncargado(id: String) {
        dataRef!!.child(id).removeValue()
    }

    /**
     * Funcion que permite eliminar un servicio a la base de datos
     */
    fun eliminarServicio(id: String) {
        dataRef!!.child(id).removeValue()
    }

    /**
     * Funcion que permite escuchar un evento de la base de datos
     */
    fun escucharEventoFireBase() {
        dataRef!!.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.v("ManagerFire", "onCancelled")
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildMoved")
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildChanged")
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildAdded")
                val cliente: Cliente =
                        p0!!.getValue(Cliente::class.java)!!
                cliente.cedula = p0!!.key
                listener!!.actualizarAdaptadorCliente(cliente)
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                Log.v("ManagerFire", "onChildRemoved")
            }
        })
    }
}