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
public class Categoria {
    
     private int categoria_id;
    private String categoria_nom;

    public Categoria() {
    }

    public Categoria(String categoria_nom) {
        this.categoria_nom = categoria_nom;
    }

    public Categoria(int categoria_id, String categoria_nom) {
        this.categoria_id = categoria_id;
        this.categoria_nom = categoria_nom;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public String getCategoria_nom() {
        return categoria_nom;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public void setCategoria_nom(String categoria_nom) {
        this.categoria_nom = categoria_nom;
    }


    @Override
    public String toString() {
        return categoria_nom ;
    }
    
}

