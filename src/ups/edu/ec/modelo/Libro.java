/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.modelo;

/**
 *
 * @author hp
 */
public abstract class Libro{
    private int cod_libro;
    private String titulo;
    private String autor;
    private String edicion;
    private double precio;

    public Libro(int cod_libro, String titulo, String autor, String edicion, double precio) {
        this.cod_libro = cod_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.precio = precio;
    }
    
    public Libro() {
    }

    public int getCod_libro() {
        return cod_libro;
    }

    public void setCod_libro(int cod_libro) {
        this.cod_libro = cod_libro;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
  
    public abstract Double calcularBase();//10.71 (incluye comision) Costo mas comision
    public abstract Double calcularEnvio();//20$  envio libro impreso
    
    public double calcularCosto(){
        double baseTotal= calcularBase()+calcularEnvio();
        return baseTotal;
    };
    
}
