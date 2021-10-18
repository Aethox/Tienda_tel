/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;
import com.mysql.cj.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
        
 /*
 * @author PrSala408
 */
public class ConexionDB {
    private String driver;
    private String url;
    private String user;
    private String psw;
    private Connection conexion = null;
    public ConexionDB(){
        
    }

    public ConexionDB(String driver, String url, String user, String psw) {
        try {
            this.driver = driver;
            this.url = url;
            this.user = user;
            this.psw = psw;
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexion establecida!");
            conexion = DriverManager.getConnection(url,user,psw);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error!");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPsw() {
        return psw;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
   public ResultSet consultarBD(String sql){
       ResultSet registro = null; 
       try {
            Statement comando = conexion.createStatement();
            registro = comando.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           return registro;
       }
   }
   public int modificarBD(String sql){ 
       int nroRegistros = 0; 
       try {
            Statement comando = conexion.createStatement();
            comando.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           return nroRegistros;
       }
   }
   public void CerrarConexion(){
        try {
            conexion.close();
            System.out.println("Conexion cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
