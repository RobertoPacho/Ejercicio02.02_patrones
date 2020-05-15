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
public class LibroDigital extends Libro{
    private int cod_libD;
    private double comision;

    public LibroDigital(int cod_libD, double comision) {
        this.cod_libD = cod_libD;
        this.comision = comision;
    }

    public LibroDigital() {
    }
    
    

    public int getCod_libD() {
        return cod_libD;
    }

    public void setCod_libD(int cod_libD) {
        this.cod_libD = cod_libD;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    

    @Override
    public Double calcularEnvio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double calcularBase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
