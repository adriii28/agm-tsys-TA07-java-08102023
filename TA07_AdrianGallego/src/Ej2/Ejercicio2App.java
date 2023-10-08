package Ej2;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2App {
	
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        final double IVA = 0.21;
        
        System.out.println("-- SUPERMERCADO --");
        
        //Variable que llama a un metodo para introducir la cantidad de productos para comprar
        int numProductos = numProductos(sc);

        //Creo dos arrays uno para almacenar la cantidad de producto y el otro para almacenar el precio
        ArrayList<Double> precioArray = new ArrayList<Double>();
        ArrayList<Integer> cantidadArray = new ArrayList<Integer>();

        for (int i = 0; i < numProductos; i++) {
        	
        	/*La variable precio llama a un metodo que genera un precio aleatorio 
        	 *(En vez de introducirlo por teclado he preferido crear el precio de manera aleatorio, con un numero del 1 al 20)*/
            double precio = crearPrecio(i + 1);
            
            /*Y la variable cantidad llama a un metodo en el que se introduce por teclado la cantidad 
             * de cada producto. Tiene un control para que el usuario SOLO introduzca valores correctos */
            int cantidad = cantidadProducto(sc);

            precioArray.add(precio);
            cantidadArray.add(cantidad);
        }

        double precio = calcularPrecio(precioArray, cantidadArray);
        double precioIva = precio + (precio * IVA);
        int totalArticulos = cantidadArray.size();

        imprimirInfo(precio, precioIva, totalArticulos);

        double cantPagada = cantidadPagada(sc, precioIva);
        double devolver = cantPagada - precioIva;
        System.out.println("- Cantidad a devolver -> " + devolver + "€");

        sc.close();
    }

    
    
    public static int numProductos(Scanner sc) {
    	
    	/* Igual que en el Ejercicio1, un metodo para introducir los productos. 
         * Tiene un control para que el usuario SOLO introduzca valores correctos, utilizando el trycatch
         * y un bucle, del cual, no se sale hasta que el usuario no introduzca bien los caracteres*/
    	
        int numProductos = 0;
        boolean valido = false;
        
        do {
        	System.out.print("Numero de productos a comprar: ");
            String numProductoString = sc.nextLine();

            try {
                numProductos = Integer.parseInt(numProductoString);
                if (numProductos > 0) {
                    valido = true;
                } else {
                    System.out.println(" -> Introduce un numero mayor que cero ");
                }
            } catch (Exception e) {
                System.out.println(" -> Caracter no valido / Introduce un numero");
            }
		} while (!valido);

        return numProductos;
    }

    public static double crearPrecio(int numProducto) {
        double random = Math.random() * (20 - 1 + 1) + 1;
        random = (double) Math.round(random * 100d) / 100d;
        System.out.println("\nProducto " + numProducto + " / Precio - " + random);
        return random;
    }

    public static int cantidadProducto(Scanner sc) {
        int cantidad = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Cantidad: ");
            String cantidadString = sc.nextLine();

            try {
                cantidad = Integer.parseInt(cantidadString);
                if (cantidad >= 0) {
                    valido = true;
                } else {
                    System.out.println("Introduce una cifra entera positiva");
                }
            } catch (Exception e) {
                System.out.println("Introduce un caracter valido");
            }
        }

        return cantidad;
    }

    public static double cantidadPagada(Scanner sc, double precioIva) {
        double cantPagada = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Cantidad pagada: ");
            String cantPagadaString = sc.nextLine();

            try {
                cantPagada = Double.parseDouble(cantPagadaString);
                if (cantPagada >= precioIva) {
                    valido = true;
                } else {
                    System.out.println("La cantidad a pagar debe ser mayor o igual al precio con IVA");
                }
            } catch (Exception e) {
                System.out.println("Introduce un caracter valido");
            }
        }

        return cantPagada;
    }

    public static void imprimirInfo(double precio, double precioIva, int totalArticulos) {
        System.out.println("\n --- RESUMEN COMPRA ---");
        System.out.println("- IVA aplicado -> 21%");
        System.out.println("- Precio bruto -> " + precio + "€");
        System.out.println("- Precio con iva -> " + precioIva + "€");
        System.out.println("- Numero de articulos -> " + totalArticulos);
    }

    public static double calcularPrecio(ArrayList<Double> compra, ArrayList<Integer> cantidadArray) {
        double totalCompra = 0;

        for (int i = 0; i < compra.size(); i++) {
            double sumaArticulos = compra.get(i) * cantidadArray.get(i);
            totalCompra += sumaArticulos;
        }

        return totalCompra;
    }
}