/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author FMHJ97
 */
public class Vuelo {
    private String codigo;
    private String fecha;
    private String origen;
    private String destino;
    private int precio;

    public Vuelo(String codigo, String fecha, String origen, String destino, int precio) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
    }
    
    public void subirPrecio() {
        this.precio *= 1.10;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getPrecio() {
        return precio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}
