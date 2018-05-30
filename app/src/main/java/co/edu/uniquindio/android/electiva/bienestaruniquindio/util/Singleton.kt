package co.edu.uniquindio.android.electiva.bienestaruniquindio.util

import co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo.*
import co.edu.uniquindio.android.electiva.bienestaruniquindio.vo.Dependencia

/**
 * Clase que representa el singleton
 */
object Singleton{

        var administrador = Administrador("1094962045", "Camilo", "Alzate Bustos", "1234")
        var clientes: ArrayList<Cliente> = ArrayList()
        var servicios: ArrayList<Servicio> = ArrayList()
        var encargados: ArrayList<Encargado> = ArrayList()
        var dependencias: ArrayList<Dependencia> = ArrayList()
        var categorias: ArrayList<Categoria> = ArrayList()


}//Cierre de la clase