/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Producto;

/**
 *
 * @author Usuario
 */
public class ControllerFactura {

    public ControllerFactura() {
    }

    public boolean login(String usuario, String pass){
        
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        
        //1. consulta SQL para mirar todos los usuarios
        //2. comparar los datos obtenidos del sql con los datos que ha escrito el usuario
   
        //3. si hay conicidencias entonces es correcto
        
        String SQL = "SELECT * FROM tbl_usuari WHERE login_usuari = '"+ usuario +"' && pass_usuari = '"+ pass +"'";

        Statement st = null;
        int ok = 0;
        try {
            //System.out.println("conexion realizada");
            st = cn.prepareCall(SQL);

          
            ResultSet rs = st.executeQuery(SQL);

            if(rs.next()) {
                //si entra aqui significa que hay coincidencias
                JOptionPane.showMessageDialog(null, "Login Correcto");
                ok = 1;
            }
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no se ha realizado correctamente la consulta");
            
            //si falla sgnifica que no hay coincidencias
            
            ok = 0;
        }
         
        if (ok == 1){
            return true;
        }else{
            return false;
        }
       
    }
    
    public void rutinaCrearCliente(Cliente c) {

        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //llamar a una rutina creada en la base de datos
        try {
            CallableStatement llamar = cn.prepareCall("{call spcrearCliente(?,?)}");

            llamar.setString(1, "david");
            llamar.setString(2, "656565415");

        } catch (SQLException ex) {

        }

    }

    public DefaultTableModel mostrarProducto() {

        DefaultTableModel tabla = null;

        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();

        String sql = "SELECT * FROM tbl_producto";

        Statement st = null;
        //Creamos un veector string para almacenar los datos que obtenemos del select y lo meteremos en la tabla
        String vector[] = new String[4];
        String vectorCabecera[] = new String[4];

        vectorCabecera[0] = "pro_id";
        vectorCabecera[1] = "pro_nom";
        vectorCabecera[2] = "pro_precio";
        vectorCabecera[3] = "pro_stock";

        tabla =  new DefaultTableModel(null, vectorCabecera);

        
        try {

            st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                vector[0] = String.valueOf(rs.getInt("pro_id"));
                vector[1] = rs.getString("pro_nom");
                vector[2] = String.valueOf(rs.getDouble("pro_precio"));
                vector[3] = String.valueOf(rs.getInt("pro_stock"));

               
                 tabla.addRow(vector);
            }
            
        } catch (SQLException ex) {

        }

        return tabla;
    }

    public void anadirProductoCliente(Producto p, Cliente c) {
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //crear la consulta, los ? smulan  las variables
        String sql1 = "INSERT INTO tbl_producto (pro_nom, pro_precio, pro_stock) VALUES (?,?,?)";
        String sql2 = "INSER INTO tbl_cliente (cli_NIF, cli_nom) VALUES (?,?)";
        //pasar parametros a la consulta
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;

        try {
            //cuando esta en false ejecuta los 2 sql como si fuera una sola sentencia
            cn.setAutoCommit(false);
            //se podran instertar cosas
            pst1 = cn.prepareStatement(sql1);
            //montar tabla para insertar en la BBDD
            pst1.setString(1, p.getPro_nom());
            pst1.setDouble(2, p.getPro_precio());
            pst1.setString(3, p.getPro_stock());

            pst2 = cn.prepareStatement(sql2);
            //montar tabla para insertar en la BBDD
            pst2.setString(1, c.getCli_NIF());
            pst2.setString(2, c.getCli_nom());

            int n2 = pst2.executeUpdate();

            int n1 = pst1.executeUpdate();

            cn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPPS! no se ha podido ejecutar la consulta");
            try {
                //si no se ha ejecutado algun de los dos sql, este comando deshace la unica sentencia sql
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void anadirProducto(Producto p) {
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //crear la consulta, los ? smulan  las variables
        String sql = "INSERT INTO tbl_producto (pro_nom, pro_precio, pro_stock) VALUES (?,?,?)";
        //pasar parametros a la consulta
        PreparedStatement pst = null;
        try {
            //se podran instertar cosas
            pst = cn.prepareStatement(sql);
            //montar tabla para insertar en la BBDD
            pst.setString(1, p.getPro_nom());
            pst.setDouble(2, p.getPro_precio());
            pst.setString(3, p.getPro_stock());
            //ejecutar la consulta del pst prepared statement
            //el executeUpdate devuelve un int, si funciona devuelve 1, si no, 0
            int n = pst.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Enhorabuena! se ha insertado un nuevo registro.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido insertar el registro.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPPS! no se ha podido conectar a la base de datos");
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }

    }

    public void nuevoCliente(Cliente c) {
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //crear la consulta, los ? smulan  las variables
        String sql = "INSERT INTO tbl_cliente (cli_NIF, cli_nom) VALUES (?,?)";
        //pasar parametros a la consulta
        PreparedStatement pst = null;
        try {
            //se podran instertar cosas
            pst = cn.prepareStatement(sql);
            //montar tabla para insertar en la BBDD
            pst.setString(1, c.getCli_NIF());
            pst.setString(2, c.getCli_nom());
            //ejecutar la consulta del pst prepared statement
            //el executeUpdate devuelve un int, si funciona devuelve 1, si no, 0
            int n = pst.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Enhorabuena! se ha insertado un nuevo registro.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido insertar el registro.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPPS! no se ha podido conectar a la base de datos");
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }
    }

}
