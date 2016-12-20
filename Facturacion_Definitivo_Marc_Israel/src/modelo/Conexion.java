/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Conexion {
     public String db = "bd_estoc";
    // el puerto 3306 no es necesario ponerlo cuando es el por defecto 
    public String url = "jdbc:mysql://localhost:3306/"+db;
    public String user = "root";
    public String password = "";
    
    //se conecta y establece el puente entre la bbdd y el programa de java
    public Connection conectar(){
        Connection conexion = null;
        //indicar la ruta donde esta el driver
        try {
            Class.forName("org.gjt.mm.mysql.Driver");//indica donde esta el driver
            conexion = DriverManager.getConnection(url, user, password);//define la conexion
            System.out.println("Conexión establecida con éxito");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error en la conexión");
            System.out.println(e);
        }finally{
            return conexion;
        }
        
    }

    public Conexion() {
    }
    
}
