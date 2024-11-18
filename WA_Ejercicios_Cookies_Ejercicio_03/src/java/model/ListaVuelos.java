/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author FMHJ
 */
public class ListaVuelos extends ArrayList<Vuelo>{

    public ListaVuelos() {
        this.add(new Vuelo("111A","2024-11-20","Cordoba","Murcia",75));
        this.add(new Vuelo("333C","2024-11-24","Malaga","Oviedo",150));
        this.add(new Vuelo("555E","2024-11-26","Sevilla","Madrid",100));
    }
    
}
