/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * 
 * @author FMHJ97
 */
public class ListaUsuarios extends ArrayList<Usuario>{
    
    /**
     * Constructor
     */
    public ListaUsuarios() {
        /*
        Agregamos una lista de usuarios predeterminados.
        Usaremos el ArrayList para realizar una verificación
        de credenciales.
        */
        this.add(new Usuario("fran", "1234"));
        this.add(new Usuario("juan", "queso"));
        this.add(new Usuario("carlos", "pwd123"));
    }
}
