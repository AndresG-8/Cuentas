/**
 * Clase encargada de la conexión con SQLite y de pasarle la conexion a la clase usuario para las operaciones,
 * esto puede ser conocido como "Pool de conexiones"
 */

package cuentas;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AndresG-8
 */
public class Conexion {
    
    private final String carpetaDB = "db";
    private final String nombreDB = "cuentas.db";
    //se usa una ruta relativa para que la aplicacion se ejecute sin problemas desde donde se invoque
    private String dir;
    //donde este la ruta que en la carpeta db se guarde el archivo de BD con la info
    private String url;
    
    //instancias con las cuales se prepara y se hacen operacion en la Base de datos
    private Connection conn = null; 
    
    Statement st;
    ResultSet rs;
    PreparedStatement pstm;
    
    public Conexion(){ 
        //se crea el objeto para la conexión
        preliminar();
        //realiza la conexion a la base de datos
        conectar();
        
        if (crearTabla(conn) )
            JOptionPane.showMessageDialog(null,"Base de datos lista");
    } 
    
    private void preliminar(){
        //se crea un objeto que representa la carpeta donde ira la base se datos en caso de qeu no exista ya
        File folder = new File( carpetaDB );
        try {
            //miro la ruta donde esta esa carpeta para crear alli el archivo
            dir =  folder.getCanonicalPath();
            System.out.println("Dir:" + dir);
            //System.out.println ("absolute: " + folder.getAbsolutePath());
            url = dir + File.separator + nombreDB;
            System.out.println("Url:" + url);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        //crea el objeto de la base de datos
        File archivoDB = new File(url);
        //System.out.println("absolute path:" + archivoDB.getAbsolutePath());
        JOptionPane.showMessageDialog(null, "absolute path:" + archivoDB.getAbsolutePath());
        //se le pregunta si la carpeta existe
        if (folder.exists()){
            //System.out.println("el directorio ya existe " + folder.exists() + ". Verificando archivo de base de datos");
            JOptionPane.showMessageDialog(null, "el directorio ya existe " + folder.exists() + ". Verificando archivo de base de datos");
            if (!archivoDB.exists()){
                //System.out.println("Hay que crear el archivo");
                JOptionPane.showMessageDialog(null, "Hay que crear el archivo");
                try {
                    archivoDB.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
               // System.out.println("Archivo creado: " + archivoDB.exists());
                JOptionPane.showMessageDialog(null, "Archivo creado: " + archivoDB.exists());
            }
        }else{
            if (folder.mkdir()){
               // System.out.println("Directorio creado: " + folder.exists() + " - hay que crear el archivo");
                JOptionPane.showMessageDialog(null, "Directorio creado: " + folder.exists() + " - hay que crear el archivo");
                try {
                    archivoDB.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Archivo creado: " + archivoDB.exists());
                JOptionPane.showMessageDialog(null, "Archivo creado: " + archivoDB.exists());
            }
        }
         
    }
    
    private void conectar (){
        try{          
            Class.forName("org.sqlite.JDBC");          
            conn = DriverManager.getConnection("jdbc:sqlite:" + url); 
        }catch(SQLException | ClassNotFoundException e){ 
            JOptionPane.showMessageDialog(null,"Conexión fallida"); 
            e.printStackTrace();
        } 
    }
    
    public Connection getConnection(){ 
      return conn; 
    }
    
    public void desconectar(){
        try{
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo cerrar la conexión" + e); 
        }
    }
    
    public String getUri(){
        return url;
    }
    
    public boolean eliminar(Usuario usuario){
        return usuario.eliminar(conn);
    }

    boolean modificar(Usuario usuario) {
        return usuario.modificar(conn);
    }
    
    boolean buscar(Usuario usuario){
        return usuario.buscar(conn);
    }
    
    private boolean crearTabla(Connection con){
        boolean resultado = false;
        
        try{
           String sql = "CREATE TABLE IF NOT EXISTS Account( id integer primary key autoincrement not null, url varchar(50) not null, usuario varchar(50) not null, password varchar(25) not null)";
           PreparedStatement pstm = con.prepareStatement(sql);
           pstm.executeUpdate();
           resultado = true;
        }catch(SQLException e1){
           JOptionPane.showMessageDialog(null, "ERROR SQL: " + e1.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
}
