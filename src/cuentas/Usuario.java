package cuentas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AndresG-8
 */
public class Usuario {
    
    String url, usuario, psw;
    int id;
    
    public Usuario() {
        this.url = "";
        this.usuario = "";
        this.psw = "";
        this.id = 0;
    }
    
    public Usuario(String url, String usuario, String psw) {
        this.url = url;
        this.usuario = usuario;
        this.psw = psw;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
//    public boolean guardar(Usuario usuario){
//         boolean resultado = false;
//     
//    }
    public boolean eliminar(Connection con){
        boolean resultado = false;
        
        try{
           String sql = "delete from Account where id = ('"+ getId() +"')";
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

    boolean modificar(Connection conn) {
        boolean resultado = false;
        
        try{
           String sql = "UPDATE Account set url = '"+ getUrl() +"', usuario = '"+ getUsuario() +"', password = '"+ getPsw() +"' where id = ('"+ getId() +"')";
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.executeUpdate();
           resultado = true;
        }catch(SQLException e1){
           JOptionPane.showMessageDialog(null, "ERROR SQL: " + e1.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    boolean buscar(Connection con){
        boolean respuesta = false;
        try {
            String sql = "";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
