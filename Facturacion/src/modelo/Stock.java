/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 1001489.joan23
 */
public class Stock {
    private int estoc_id;
    private int estoc_max;
    private int estoc_min;
    private int prod_id;

    public Stock(int estoc_id, int estoc_max, int estoc_min, int prod_id) {
        this.estoc_id = estoc_id;
        this.estoc_max = estoc_max;
        this.estoc_min = estoc_min;
        this.prod_id = prod_id;
    }

    public Stock(int estoc_max, int estoc_min) {
        this.estoc_max = estoc_max;
        this.estoc_min = estoc_min;
    }

    public Stock(int estoc_max, int estoc_min, int prod_id) {
        this.estoc_max = estoc_max;
        this.estoc_min = estoc_min;
        this.prod_id = prod_id;
    }

    public int getEstoc_id() {
        return estoc_id;
    }

    public int getEstoc_max() {
        return estoc_max;
    }

    public void setEstoc_id(int estoc_id) {
        this.estoc_id = estoc_id;
    }

    public void setEstoc_max(int estoc_max) {
        this.estoc_max = estoc_max;
    }

    public void setEstoc_min(int estoc_min) {
        this.estoc_min = estoc_min;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getEstoc_min() {
        return estoc_min;
    }

    public int getProd_id() {
        return prod_id;
    }
    
    
    
}
