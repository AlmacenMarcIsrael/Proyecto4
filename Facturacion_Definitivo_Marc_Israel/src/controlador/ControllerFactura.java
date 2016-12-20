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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;

import modelo.Conexion;
import modelo.Producto;
import modelo.Stock;

/**
 *
 * @author Usuario
 */
public class ControllerFactura {

    public ControllerFactura() {
    }
    
    public DefaultTableModel buscador(String y, String z){
        
         DefaultTableModel tabla = null;

        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();

        //SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min 
        //FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id 
        //INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id 
        //WHERE tbl_producte.prod_nom LIKE '%Blanc%' OR tbl_categoria.categoria_nom LIKE '%Dorm%' 
        String sql = null;
        if (y == "Nombre Producto"){
            
        sql = "SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id WHERE prod_nom LIKE '%"+z+"%' ";

        }else if(y == "Precio superior a"){
         sql = "SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id WHERE prod_preu >= "+z;

        }else if(y == "Precio inferior a"){
         sql = "SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id WHERE prod_preu <= "+z;

        }else if(y == "Nombre Categoria"){
           sql = "SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id WHERE categoria_nom LIKE '%"+z+"%' ";
        }
        
        
        //el primer interrogante será el campo de la tabla quue queremos filtras y el segundo interrogante sera lo que hemos introducido en el buscador
       
        //PreparedStatement pst = null;
        Statement st = null;
 
        
        //Creamos un veector string para almacenar los datos que obtenemos del select y lo meteremos en la tabla
        String vector[] = new String[6];
        String vectorCabecera[] = new String[6];

        vectorCabecera[0] = "ID Producto";
        vectorCabecera[1] = "Nombre Producto";
        vectorCabecera[2] = "Precio Producto";
        vectorCabecera[3] = "Categoria";
        vectorCabecera[4] = "Stock Máximo";
        vectorCabecera[5] = "Stock Mínimo";
       

        tabla =  new DefaultTableModel(null, vectorCabecera);
        
            
        
        try {
 
              st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);
           //pst = cn.prepareStatement(sql);
   
            //if (y == "prod_preu"){
               
              //  Double x = Double.parseDouble(z);
                //pst.setDouble(1, x);
            //}
            
            
            //z = "%"+z+"%";
           // pst.setString(1, z);
         
            //ResultSet rs = pst.executeQuery(sql);
            int cont = 0;
            while (rs.next()) {

                vector[0] = String.valueOf(rs.getInt("prod_id"));
                vector[1] = rs.getString("prod_nom");
                vector[2] = String.valueOf(rs.getDouble("prod_preu"));
                vector[3] = rs.getString("categoria_nom");
                vector[4] = String.valueOf(rs.getInt("estoc_q_max"));
                vector[5] = String.valueOf(rs.getInt("estoc_q_min"));
                 tabla.addRow(vector);
                 cont ++;
            }
            if (cont==0){
               
           JOptionPane.showMessageDialog(null, "No hay coincidencias de busqueda"); 
            }
        } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error, No se ha ejecutado la busqueda, contacte con el administrador"); 
           
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }

        return tabla;
    }
    
    public void eliminar(int id){
        
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        
          String sql = "DELETE FROM tbl_producte WHERE prod_id = ?";
          String sql2 = "DELETE FROM tbl_estoc WHERE prod_id = ?";
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            //se podran instertar cosas
            pst = cn.prepareStatement(sql);
            //montar tabla para insertar en la BBDD
            pst.setInt(1, id);
            pst2 = cn.prepareStatement(sql2);
            //montar tabla para insertar en la BBDD
            pst2.setInt(1, id);
           
            //ejecutar la consulta del pst prepared statement
            //el executeUpdate devuelve un int, si funciona devuelve 1, si no, 0
            int n = pst.executeUpdate();
        
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el registro del producto.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido eliminar el producto.");
            }
             int n2 = pst2.executeUpdate();
            if (n2 != 0) {
                JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el registro de stock ");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido eliminar el stock.");
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
    
    public void llenarCombo(JComboBox box){
        DefaultComboBoxModel value;
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();

        String sql = "Select * From tbl_categoria";
        
        Statement st = null;
        ResultSet rs=null;

        try {
            st = cn.createStatement();
             
            rs = st.executeQuery(sql);
            
            //creamos un nuevo modelo de combo box
            value=new DefaultComboBoxModel();
            // a la caja que nos pasan como parametro le ponemos el modelo que hemos creado
            box.setModel(value);
            while (rs.next()) {
            //y a continuacion le añadimos los elemenos que encontramos de la consulta, al combo box 
             value.addElement(new Categoria(rs.getInt("categoria_id"),rs.getString("categoria_nom")));
            
            }
            cn.close();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error Conexion");
          
        }
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
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }
         
        if (ok == 1){
            return true;
        }else{
            return false;
        }
       
    }
    
    

     public DefaultTableModel mostrarProducto() {

        DefaultTableModel tabla = null;

        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();

        //SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min 
        //FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id 
        //INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id 
        //WHERE tbl_producte.prod_nom LIKE '%Blanc%' OR tbl_categoria.categoria_nom LIKE '%Dorm%' 
        String sql = "SELECT tbl_producte.prod_id, tbl_producte.prod_nom, tbl_producte.prod_preu, tbl_categoria.categoria_nom, tbl_estoc.estoc_q_max, tbl_estoc.estoc_q_min FROM tbl_producte INNER JOIN tbl_estoc ON tbl_estoc.prod_id = tbl_producte.prod_id INNER JOIN tbl_categoria ON tbl_producte.categoria_id = tbl_categoria.categoria_id ORDER BY `prod_id` ASC";
       

        Statement st = null;
        //Creamos un veector string para almacenar los datos que obtenemos del select y lo meteremos en la tabla
        String vector[] = new String[6];
        String vectorCabecera[] = new String[6];

        vectorCabecera[0] = "ID Producto";
        vectorCabecera[1] = "Nombre Producto";
        vectorCabecera[2] = "Precio Producto";
        vectorCabecera[3] = "Categoria";
        vectorCabecera[4] = "Stock Máximo";
        vectorCabecera[5] = "Stock Mínimo";
       

        tabla =  new DefaultTableModel(null, vectorCabecera);

        
        try {

            st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                vector[0] = String.valueOf(rs.getInt("prod_id"));
                vector[1] = rs.getString("prod_nom");
                vector[2] = String.valueOf(rs.getDouble("prod_preu"));
                vector[3] = rs.getString("categoria_nom");
                vector[4] = String.valueOf(rs.getInt("estoc_q_max"));
                vector[5] = String.valueOf(rs.getInt("estoc_q_min"));
                 tabla.addRow(vector);
            }
            
        } catch (SQLException ex) {

        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }

        return tabla;
    }
     
     
    public int buscar_categoria_id(String c){
        
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        int categoria_id = 0 ;
        
         String sql = "Select categoria_id From tbl_categoria where categoria_nom = '"+c+"'";
        
        Statement st = null;
        ResultSet rs=null;
        
       try {
            //System.out.println("conexion realizada");
            st = cn.prepareCall(sql);

          
             rs = st.executeQuery(sql);

            if(rs.next()) {
                //si entra aqui significa que hay coincidencias
                
               categoria_id = rs.getInt("categoria_id");
            }
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no se ha realizado correctamente la consulta");
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }

        
        return categoria_id;
    } 
     
    public void anadirProductoStock(Producto p, Stock s) {
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //crear la consulta, los ? smulan  las variables
        
        String sql1 = "INSERT INTO tbl_producte (Prod_nom, Prod_preu, categoria_id) VALUES (?,?,?)";
      
         String sql2= "select distinct last_insert_id() from  tbl_producte";
         
         String sql3 = "INSERT INTO tbl_estoc (estoc_q_max, estoc_q_min, prod_id) VALUES (?,?,?)";
         
        PreparedStatement pst1 = null;
        Statement st=null;
        PreparedStatement pst2 = null;
        ResultSet rs=null;

        try {
            //cuando esta en false ejecuta los 2 o mas sql como si fuera una sola sentencia
            cn.setAutoCommit(false);
            //se podran instertar cosas
                pst1 = cn.prepareStatement(sql1);

            pst1.setString(1, p.getProd_nom());
            pst1.setDouble(2, p.getProd_preu());
            pst1.setInt(3, p.getCategoria_id());
            pst1.executeUpdate();
           

            pst2 = cn.prepareStatement(sql2);
           
          
            //recuperamos el ultimo registro
            st = cn.createStatement(); 
            rs = st.executeQuery(sql2);
            int idst=0;
            while(rs.next()){
            idst=rs.getInt(1);
            }
            
              pst2 = cn.prepareStatement(sql3);

           
            
            pst2.setInt(1,s.getEstoc_max());
            pst2.setInt(2, s.getEstoc_min());    
            pst2.setInt(3,idst );
           
            //pst1.executeUpdate();       
            pst2.executeUpdate();
  
            cn.commit();
           // cn.close();
            //pst1.close();
            //pst2.close();
            //rs.close();
              JOptionPane.showMessageDialog(null, "Se ha agregado correctamente el producto al stock");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se ha creado el elemento");
            try {
                //si no se ha ejecutado algun de los dos sql, este comando deshace la unica sentencia sql
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }

    }

    public void modificar(Producto p, Stock s){
    
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //crear la consulta, los ? smulan  las variables
        String sql1 = "UPDATE tbl_producte SET prod_nom = ?, prod_preu = ?, categoria_id = ? WHERE prod_id = ?";
        
        String sql2 = "UPDATE tbl_estoc SET estoc_q_max = ?, estoc_q_min = ? WHERE prod_id = ?";
        
        
        
        PreparedStatement pst1 = null;
        Statement st=null;
        PreparedStatement pst2 = null;
        ResultSet rs=null;

        try {
            //cuando esta en false ejecuta los 2 o mas sql como si fuera una sola sentencia
            cn.setAutoCommit(false);
            //se podran instertar cosas
            pst1 = cn.prepareStatement(sql1);

            pst1.setString(1, p.getProd_nom());
            pst1.setDouble(2, p.getProd_preu());
            pst1.setInt(3, p.getCategoria_id());            
            pst1.setInt(4, p.getProd_id());
            pst1.executeUpdate();
           

            pst2 = cn.prepareStatement(sql2);
            pst2.setInt(1, s.getEstoc_max());
            pst2.setInt(2, s.getEstoc_min());    
            pst2.setInt(3, p.getProd_id() );
           
            //pst1.executeUpdate();       
            pst2.executeUpdate();
  
            cn.commit();
           //cn.close();
            //pst1.close();
            //pst2.close();
            //rs.close();
              JOptionPane.showMessageDialog(null, "Se ha modificado correctamente el producto ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se ha modificado el elemento");
            try {
                //si no se ha ejecutado algun de los dos sql, este comando deshace la unica sentencia sql
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexi�n");
            }
        }
        
        
    }
    
    public void anadirProducto(Producto p) {
        Conexion conecControl = new Conexion();
        Connection cn = conecControl.conectar();
        //crear la consulta, los ? smulan  las variables
        String sql = "INSERT INTO tbl_producto (pro_nom, pro_precio) VALUES (?,?)";
        //pasar parametros a la consulta
        PreparedStatement pst = null;
        try {
            //se podran instertar cosas
            pst = cn.prepareStatement(sql);
            //montar tabla para insertar en la BBDD
            pst.setString(1, p.getProd_nom());
            pst.setDouble(2, p.getProd_preu());
           
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
