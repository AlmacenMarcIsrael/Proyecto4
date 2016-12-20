/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Factura {
    private int fac_id;
    private double fac_importe;
    
    ArrayList<lineaFactura> lf = new ArrayList<lineaFactura>();

    public Factura() {
    }

    public Factura(int fac_id, double fac_importe) {
        this.fac_id = fac_id;
        this.fac_importe = fac_importe;
    }

    public int getFac_id() {
        return fac_id;
    }

    public double getFac_importe() {
        return fac_importe;
    }

    public ArrayList<lineaFactura> getLf() {
        return lf;
    }

    public void setFac_importe(double fac_importe) {
        this.fac_importe = fac_importe;
    }

    public void setLf(ArrayList<lineaFactura> lf) {
        this.lf = lf;
    }
    
    
}
