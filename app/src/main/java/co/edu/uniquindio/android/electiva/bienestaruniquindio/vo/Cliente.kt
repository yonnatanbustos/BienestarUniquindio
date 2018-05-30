package co.edu.uniquindio.android.electiva.bienestaruniquindio.activity.vo

import android.widget.ImageView
import co.edu.uniquindio.android.electiva.bienestaruniquindio.vo.Dependencia
import co.edu.uniquindio.android.electiva.bienestaruniquindio.vo.Tipo

/**
 * Clase que representa un cliente
 */
class Cliente(var cedula: String, var nombres: String, var apellidos: String, var telefono: String, var email: String, var password: String, var tipoCliente: Tipo, var dependencia: Dependencia) {

}