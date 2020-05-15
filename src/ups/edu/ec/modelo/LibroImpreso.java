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
public class LibroImpreso extends Libro{
    private int cod_libI;
    private double comisionLibI;
    private double envio;
    
    double precio=0;

    public LibroImpreso() {
        this.comisionLibI = 0;
        this.envio = 20;
    }
    

    public LibroImpreso(int cod_libI,String titulo, String autor, String edicion, double precio) {
        super(cod_libI,titulo, autor, edicion, precio);
    }

    

    @Override
    public Double calcularEnvio() {
        comisionLibI = precio * 0.02;
        return comisionLibI;
    }

    @Override
    public Double calcularBase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
