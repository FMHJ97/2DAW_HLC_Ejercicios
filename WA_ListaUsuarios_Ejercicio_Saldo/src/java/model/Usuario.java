/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FMHJ97
 */
public class Usuario {
    private String usuario;
    private String password;
    private int saldo;
    
    /**
     * Default Constructor.
     */
    public Usuario() {
        super();
    }

    /**
     * Constructor with fields.
     */
    public Usuario(String usuario, String password, int saldo) {
        this.usuario = usuario;
        this.password = password;
        this.saldo = saldo;
    }
    
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
