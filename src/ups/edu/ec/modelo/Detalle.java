/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.modelo;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author hp
 */
public class Detalle extends Libro{
    private int cod_detalle;
    private Libro producto;
    private Compra compra;
    private double cantidad;
    private double precio;
    

    public Detalle(int cod_detalle, Libro producto, Compra compra, double cantidad, double precio) {
        this.cod_detalle = cod_detalle;
        this.producto = producto;
        this.compra = compra;
        this.cantidad = cantidad;
        this.precio = precio;

        
    }
    public Detalle(int cod_libD,String titulo, String autor, String edicion, double precio) {
        super(cod_libD,titulo, autor, edicion, precio);
    }

    public Detalle() {
    }
    

    public int getCod_detalle() {
        return cod_detalle;
    }

    public void setCod_detalle(int cod_detalle) {
        this.cod_detalle = cod_detalle;
    }

    public Libro getProducto() {
        return producto;
    }

    public void setProducto(Libro producto) {
        this.producto = producto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    

    @Override
    public Double calcularEnvio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double calcularTotalImpreso(ArrayList<Detalle> listaDetalle){
        int gEnvio=20;
        String total="";
        for (int i = 0; i < listaDetalle.size(); i++) {
            System.out.println("Cliente = " + listaDetalle.get(i).getCompra().getCliente().getNombre());
            System.out.println("Libro = " + listaDetalle.get(i).getProducto().getTitulo());
            System.out.println("Precio Unitario= " + listaDetalle.get(i).getPrecio());
            System.out.println("Cantidad = " + listaDetalle.get(i).getCantidad());
                        
            double costEnvio = listaDetalle.get(i).getProducto().calcularEnvio();
            System.out.println("Gastos envio = " + listaDetalle.get(i).getProducto().calcularEnvio());
            double costobase_print = (listaDetalle.get(i).getProducto().calcularBase()) * listaDetalle.get(i).getCantidad();
            
            double comisionTotal= (listaDetalle.get(i).getProducto().calcularBase() - listaDetalle.get(i).getProducto().getPrecio())*listaDetalle.get(i).getCantidad();
            String total1= String.format(Locale.US,"%.2f", comisionTotal);
            System.out.println("Comision Total= " + Double.parseDouble(total1));
            

            /*double subtotal_print = listaDetalle.get(i).getCantidad() * listaDetalle.get(i).getPrecio();*/
            System.out.println("Subtotal mas comision = " + listaDetalle.get(i).getProducto().calcularBase() * listaDetalle.get(i).getCantidad());
            
            
            double total_print = costobase_print + costEnvio;
            total= String.format(Locale.US,"%.2f", total_print);
            System.out.println("TOTAL MAS COSTO ENVIO = " + Double.parseDouble(total));
        }
        return Double.parseDouble(total);
    }
    
    public double calcularTotalDigital(ArrayList<Detalle> listaDetalle){
        String total="";
        for (int i = 0; i < listaDetalle.size(); i++) {
            System.out.println("Cliente = " + listaDetalle.get(i).getCompra().getCliente().getNombre());
            System.out.println("Libro = " + listaDetalle.get(i).getProducto().getTitulo());
            System.out.println("Cantidad = " + listaDetalle.get(i).getCantidad());
            System.out.println("Precio Unitario= " + listaDetalle.get(i).getPrecio());

            double comision_print = (listaDetalle.get(i).getProducto().calcularEnvio()) * listaDetalle.get(i).getCantidad();
            System.out.println("Comision = " + (listaDetalle.get(i).getProducto().calcularEnvio()) * listaDetalle.get(i).getCantidad());

            double subtotal_print = listaDetalle.get(i).getCantidad() * listaDetalle.get(i).getPrecio();
            System.out.println("Subtotal = " + listaDetalle.get(i).getCantidad() * listaDetalle.get(i).getPrecio());

            double total_print = comision_print + subtotal_print;
            total= String.format(Locale.US,"%.2f", total_print);
            System.out.println("TOTAL = " + Double.parseDouble(total));
        }
        return Double.parseDouble(total);
    }

    @Override
    public Double calcularBase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
