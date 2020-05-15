/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.vista;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import ups.edu.ec.modelo.Cliente;
import ups.edu.ec.modelo.Compra;
import ups.edu.ec.modelo.Credito;
import ups.edu.ec.modelo.Detalle;
import ups.edu.ec.modelo.Libro;
import ups.edu.ec.modelo.LibroImpreso;
import ups.edu.ec.modelo.Recarga;

/**
 *
 * @author hp
 */
public class Inicio {

    public static void main(String[] args) {
        ArrayList<Cliente> listaClientes;
        listaClientes = new ArrayList<Cliente>();

        ArrayList<Libro> listaLibro;
        listaLibro = new ArrayList<Libro>();

        ArrayList<Compra> listaCompra;
        listaCompra = new ArrayList<Compra>();

        ArrayList<Detalle> listaDetalle;
        listaDetalle = new ArrayList<Detalle>();

        ArrayList<Credito> listaCredito;
        listaCredito = new ArrayList<Credito>();

        //LibroImpreso libroImpreso = new LibroImpreso();
        //System.out.println(libroImpreso.comision());
        int gEnvio = 20;
        int codLib = 0;

        Libro[] mislibros = new Libro[5];
        listaLibro.add(mislibros[0] = new LibroImpreso(1, "ODISEA", "Homero", "v1", 10.50));
        listaLibro.add(mislibros[1] = new LibroImpreso(2, "lA ILIADA", "Homero", "v1", 3.75));
        listaLibro.add(mislibros[2] = new LibroImpreso(3, "Juventud en extasis", "Carlos Cuauhtemoc Sanchez", "v1-1993", 14.05));
        listaLibro.add(mislibros[3] = new LibroImpreso(4, "Los Miserables", "Victor Hugo", "1993", 6.75));
        listaLibro.add(mislibros[4] = new LibroImpreso(5, "El Perfume", "Patrik Suskind", "v1-1993", 20.00));
        Scanner entrada = new Scanner(System.in);

        Cliente[] cliente = new Cliente[3];
        listaClientes.add(cliente[0] = new Cliente(1, "Narcisa"));
        listaClientes.add(cliente[1] = new Cliente(2, "Bryan"));
        listaClientes.add(cliente[2] = new Cliente(3, "Fernando"));

        Credito[] credito = new Credito[3];

        listaCredito.add(credito[0] = new Credito(1, 200, cliente[0]));
        listaCredito.add(credito[1] = new Credito(2, 50, cliente[1]));
        listaCredito.add(credito[2] = new Credito(3, 75, cliente[2]));

        boolean salir = false;
        int opcion = 0;

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        String date = "15/05/2020";
        try {
            testDate = df.parse(date);

        } catch (Exception e) {
            System.out.println("invalid format");
        }

        while (!salir) {
            System.out.println("1.....libro Impreso");
            System.out.println("2.....libro Digital");
            System.out.println("3.....salir");

            System.out.print("Ingrese tipo de compra = ");
            opcion = entrada.nextInt();
            int codigoCliente = 1;
            double total_print = 0.0;
            switch (opcion) {
                case 1:
                    Cliente cliente1 = new Cliente();
                    for (int i = 0; i < listaClientes.size(); i++) {
                        if (listaClientes.get(i).getCod_cliente() == codigoCliente) {//codigo statico cliente
                            cliente1 = new Cliente(listaClientes.get(i).getCod_cliente(), listaClientes.get(i).getNombre());
                        }
                    }
                    //*******************CREACION DE COMPRA PARA EL DETALLE**************************
                    Compra compra = new Compra(1, testDate, cliente1);
                    listaCompra.add(compra);
                    //********************CREACION DE DETALLE***************************************
                    Detalle detalle = new Detalle();
                    detalle.setCod_detalle(1);

                    int ultimo = 0;
                    System.out.println("________________LISTA DE LIBROS_____________________");
                    while (!salir) {
                        for (int i = 0; i < listaLibro.size(); i++) {
                            System.out.println(listaLibro.get(i).getCod_libro() + "...." + listaLibro.get(i).getTitulo());
                        }
                        ultimo = listaLibro.size() + 1;
                        System.out.println(listaLibro.size() + 1 + "....Salir");
                        System.out.print("Ingrese Codigo de Libro = ");

                        codLib = entrada.nextInt();

                        if (ultimo == codLib) {
                            salir = true;
                            break;
                        }
                        break;
                    }
                    
                    if (codLib < listaLibro.size() + 1) {
                        for (final Libro l : mislibros) {
                            if (l.getCod_libro() == codLib) {//codigo statico del libro
                                Libro libro = new Libro(l.getCod_libro(), l.getTitulo(), l.getAutor(), l.getEdicion(), l.getPrecio()) {
                                    @Override
                                    public Double calcularEnvio() {
                                        double com = 20;
                                        //20 costo de envio
                                        return com;
                                    }

                                    @Override
                                    public Double calcularBase() {
                                        double costoBase=(l.getPrecio()*0.02)+l.getPrecio();
                                        //10.71 costo del libro mas comision
                                        return costoBase;
                                    }
                                };
                                detalle.setProducto(libro);
                                detalle.setCompra(compra);
                                detalle.setCantidad(1);
                                detalle.setPrecio(l.getPrecio());
                            }
                            
                        }
                        listaDetalle.add(detalle);

                        for (int i = 0; i < listaDetalle.size(); i++) {
                            detalle = new Detalle();
                            total_print = detalle.calcularTotalImpreso(listaDetalle);

                            double monto = 0;
                            String montoRecorte = "";
                            for (int j = 0; j < listaCredito.size(); j++) {
                                if (listaCredito.get(j).getCliente().getCod_cliente() == codigoCliente) {
                                    monto = listaCredito.get(j).getPrecio();
                                    if (monto > total_print) {
                                        Credito credito1 = new Credito();
                                        credito1.debitarCredito(monto, listaCredito, codigoCliente, total_print);

                                        System.out.println(" ");
                                        System.out.println("  ");
                                        System.out.println("TABLA DE DEBITO ACTUALIZADA");
                                        for (int y = 0; y < listaCredito.size(); y++) {
                                            System.out.println(listaCredito.get(y).getCliente().getNombre() + "   " + listaCredito.get(y).getPrecio());
                                        }
                                    } else {
                                        System.out.println("");
                                        System.out.println("***************");
                                        System.out.println("SIN FONDOS");
                                        System.out.println("***************");
                                        do {
                                            System.out.println("1....Recargar");
                                            System.out.println("2....Salir");

                                            opcion = entrada.nextInt();
                                            switch (opcion) {
                                                case 1:
                                                    System.out.print("Ingrese cantidad de recargo : ");
                                                    double recargar = entrada.nextDouble();

                                                    Recarga recarga = new Recarga();
                                                    recarga.recargarCredito(recargar, listaCredito, codigoCliente);

                                                    System.out.println(" ");
                                                    System.out.println("  ");
                                                    System.out.println("TABLA DE DEBITO ACTUALIZADA");
                                                    for (int y = 0; y < listaCredito.size(); y++) {
                                                        System.out.println(listaCredito.get(y).getCliente().getNombre() + "   " + listaCredito.get(y).getPrecio());
                                                    }
                                                    break;
                                                case 2:
                                                    salir = true;

                                                    break;
                                                default:
                                                    throw new AssertionError();
                                            }

                                        } while (opcion == 2);
                                    }
                                }
                            }

                        }
                        System.out.println("_______________________________________________");
                    } else {
                        System.out.println("Codigo de libro no existe");
                    }
                    break;

                case 2:
                    Cliente cliente2 = new Cliente();
                    for (int i = 0; i < listaClientes.size(); i++) {
                        if (listaClientes.get(i).getCod_cliente() == codigoCliente) {//codigo statico cliente
                            cliente2 = new Cliente(listaClientes.get(i).getCod_cliente(), listaClientes.get(i).getNombre());
                        }
                    }
                    //*******************CREACION DE COMPRA PARA EL DETALLE**************************
                    Compra compra1 = new Compra(1, testDate, cliente2);
                    listaCompra.add(compra1);
                    //********************CREACION DE DETALLE***************************************
                    Detalle detalle1 = new Detalle();
                    detalle1.setCod_detalle(1);

                    System.out.println("________________LISTA DE LIBROS_____________________");
                    while (!salir) {
                        for (int i = 0; i < listaLibro.size(); i++) {
                            System.out.println(listaLibro.get(i).getCod_libro() + "...." + listaLibro.get(i).getTitulo());
                        }
                        ultimo = listaLibro.size() + 1;
                        System.out.println(listaLibro.size() + 1 + "....Salir");
                        System.out.print("Ingrese Codigo de Libro = ");

                        codLib = entrada.nextInt();

                        if (ultimo == codLib) {
                            salir = true;
                            break;
                        }
                        break;
                    }
                    if (codLib < listaLibro.size() + 1) {
                        for (final Libro l : mislibros) {
                            if (l.getCod_libro() == codLib) {//codigo statico del libro
                                Libro libro = new Libro(l.getCod_libro(), l.getTitulo(), l.getAutor(), l.getEdicion(), l.getPrecio()) {
                                    @Override
                                    public Double calcularEnvio() {
                                        double com = l.getPrecio() * 0.02;
                                        return com;
                                    }

                                    @Override
                                    public Double calcularBase() {
                                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                    }
                                };
                                detalle1.setProducto(libro);
                                detalle1.setCompra(compra1);
                                detalle1.setCantidad(2);
                                detalle1.setPrecio(l.getPrecio());
                            }
                        }
                        listaDetalle.add(detalle1);

                        for (int i = 0; i < listaDetalle.size(); i++) {
                            detalle = new Detalle();
                            total_print = detalle.calcularTotalDigital(listaDetalle);

                            double monto = 0;
                            String montoRecorte = "";
                            for (int j = 0; j < listaCredito.size(); j++) {
                                if (listaCredito.get(j).getCliente().getCod_cliente() == codigoCliente) {
                                    monto = listaCredito.get(j).getPrecio();
                                    if (monto > total_print) {
                                        Credito credito1 = new Credito();
                                        credito1.debitarCredito(monto, listaCredito, codigoCliente, total_print);

                                        System.out.println(" ");
                                        System.out.println("  ");
                                        System.out.println("TABLA DE DEBITO ACTUALIZADA");
                                        for (int y = 0; y < listaCredito.size(); y++) {
                                            System.out.println(listaCredito.get(y).getCliente().getNombre() + "   " + listaCredito.get(y).getPrecio());
                                        }
                                    } else {
                                        System.out.println("");
                                        System.out.println("***************");
                                        System.out.println("SIN FONDOS");
                                        System.out.println("***************");
                                        do {
                                            System.out.println("1....Recargar");
                                            System.out.println("2....Salir");

                                            opcion = entrada.nextInt();
                                            switch (opcion) {
                                                case 1:
                                                    System.out.print("Ingrese cantidad de recargo : ");
                                                    double recargar = entrada.nextDouble();

                                                    Recarga recarga = new Recarga();
                                                    recarga.recargarCredito(recargar, listaCredito, codigoCliente);

                                                    System.out.println(" ");
                                                    System.out.println("  ");
                                                    System.out.println("TABLA DE DEBITO ACTUALIZADA");
                                                    for (int y = 0; y < listaCredito.size(); y++) {
                                                        System.out.println(listaCredito.get(y).getCliente().getNombre() + "   " + listaCredito.get(y).getPrecio());
                                                    }
                                                    break;
                                                case 2:
                                                    salir = true;

                                                default:
                                                    throw new AssertionError();
                                            }
                                        } while (opcion == 2);
                                    }
                                }
                            }

                        }
                        System.out.println("_______________________________________________");
                    } else {
                        System.out.println("Codigo de libro no existe");
                    }
                    break;
                case 3:
                    salir = true;
                    break;
            }
        }
        System.out.println(
                "__________________________________________________________");
    }
}
