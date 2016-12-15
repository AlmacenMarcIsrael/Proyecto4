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
    private int prod_id;
    private String prod_nom;
    private double prod_preu;
    private int serie_id;

    public Producto() {
    }

    public Producto(int prod_id, String prod_nom, double prod_preu, int serie_id) {
        this.prod_id = prod_id;
        this.prod_nom = prod_nom;
        this.prod_preu = prod_preu;
        this.serie_id = serie_id;
    }

    public Producto(int prod_id, String prod_nom, double prod_preu) {
        this.prod_id = prod_id;
        this.prod_nom = prod_nom;
        this.prod_preu = prod_preu;
    }

    public Producto(String prod_nom, double prod_preu) {
        this.prod_nom = prod_nom;
        this.prod_preu = prod_preu;
    }

    public int getProd_id() {
        return prod_id;
    }

    public String getProd_nom() {
        return prod_nom;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public void setProd_nom(String prod_nom) {
        this.prod_nom = prod_nom;
    }

    public void setProd_preu(double prod_preu) {
        this.prod_preu = prod_preu;
    }

    public void setSerie_id(int serie_id) {
        this.serie_id = serie_id;
    }

    public double getProd_preu() {
        return prod_preu;
    }

    public int getSerie_id() {
        return serie_id;
    }

    
}
