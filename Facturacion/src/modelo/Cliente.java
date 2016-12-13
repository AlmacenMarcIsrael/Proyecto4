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
public class Cliente {
    private int cli_id;
    private String cli_NIF;
    private String cli_nom;

    public Cliente() {
    }

    public Cliente(int cli_id, String cli_NIF, String cli_nom, int cli_telf) {
        this.cli_id = cli_id;
        this.cli_NIF = cli_NIF;
        this.cli_nom = cli_nom;
    }

    public Cliente(String cli_NIF, String cli_nom) {
        this.cli_NIF = cli_NIF;
        this.cli_nom = cli_nom;
    }
    
    
    public int getCli_id() {
        return cli_id;
    }

    public String getCli_NIF() {
        return cli_NIF;
    }

    public String getCli_nom() {
        return cli_nom;
    }


    public void setCli_NIF(String cli_NIF) {
        this.cli_NIF = cli_NIF;
    }

    public void setCli_nom(String cli_nom) {
        this.cli_nom = cli_nom;
    }
       
}
