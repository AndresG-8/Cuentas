/**
 * Programa desarrollado para la administración de cuentas de manera básica y rápida
 * se hace más que todo para aclarar conceptos en cuanto a clases y conexión con base de datos
 * SQLite, así mismo como para el uso de ésta desde Java
 */
package cuentas;

/**
 * CLASE PRINCIPAL 
 * Version: 1.0.0
 * @author AndresG-8
 */
public class PpalCuentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se crea un objeto de la clase JFrameCuenta
        JFrameCuenta form = new JFrameCuenta();
        //Se llama al metodo setVisible para que el frame se muestre e inicie todo desde el constructor
        form.setVisible(true);
    }
    
}

/**
 * TODO:
 * Hacer que el sistema identifique si ya tiene la Base de datos creada y sino que lance error
 * Poner un check para que el usuario diga si quiere ver o no la contraseña que escribe
 * Encriptar las contraseñas en la base de datos
 * Crear una especie de login para el usuario... será el usuario maestro por decirlo de algún modo
 * 
 */

/**
 * PD: en vez de verificar el texto se pueden poner valores booleanos que digan si el boton esta en un estado u otro
 * así: si el boton originalemnte de "nuevo" estác on "nuevo" o con "guardar" seria algo como
 * boolean btnNuevo = false;
 * boolean btnGuardar = false;
 * y cuando se altere el funcionamiento es solo cambiar los valores
 */