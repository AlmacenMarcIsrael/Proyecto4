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
public class lineaFactura {
    private int lif_id;

    public lineaFactura(int lif_id) {
        this.lif_id = lif_id;
    }

    public lineaFactura() {
    }

    public int getLif_id() {
        return lif_id;
    }

    public void setLif_id(int lif_id) {
        this.lif_id = lif_id;
    }
    
    
}
