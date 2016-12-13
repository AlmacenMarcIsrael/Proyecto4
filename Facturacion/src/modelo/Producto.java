/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Producto {
    private int pro_id;
    private String pro_nom;
    private double pro_precio;
    private String pro_stock;

    public Producto() {
    }

    public Producto(String pro_nom, double pro_precio, String pro_stock) {
        this.pro_nom = pro_nom;
        this.pro_precio = pro_precio;
        this.pro_stock = pro_stock;
    }

    
    
    public Producto(int pro_id, String pro_nom, double pro_precio, String pro_stock) {
        this.pro_id = pro_id;
        this.pro_nom = pro_nom;
        this.pro_precio = pro_precio;
        this.pro_stock = pro_stock;
    }

    public Producto(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPro_id() {
        return pro_id;
    }

    public String getPro_nom() {
        return pro_nom;
    }

    public double getPro_precio() {
        return pro_precio;
    }

    public String getPro_stock() {
        return pro_stock;
    }

    public void setPro_nom(String pro_nom) {
        this.pro_nom = pro_nom;
    }

    public void setPro_precio(double pro_precio) {
        this.pro_precio = pro_precio;
    }

    public void setPro_stock(String pro_stock) {
        this.pro_stock = pro_stock;
    }
    
    
}
