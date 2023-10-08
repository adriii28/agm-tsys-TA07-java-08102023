package Ej4;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Ejercicio4App {
public static void main(String[] args) {
	
		final int STOCK_INICIAL = 10;
		
        final double IVA = 0.21;
	
		Scanner sc = new Scanner(System.in);
		
		Hashtable<String, Double> BDProductos = new Hashtable<String, Double>();
		Hashtable<String, Integer> BDStock = new Hashtable<String, Integer>();
		
		ArrayList<Double> precioArray = new ArrayList<Double>();
        ArrayList<Integer> cantidadArray = new ArrayList<Integer>();

		//Base de datos de tienda (es una libreria)
		BDProductos.put("lapiz", 0.5);
		BDProductos.put("libro mates", 20.0);
		BDProductos.put("estuche", 10.0);
		BDProductos.put("mochila", 10.0);
		BDProductos.put("boli", 1.0);
		BDProductos.put("libreta", 4.0);
		BDProductos.put("libro ingles", 19.0);
		BDProductos.put("carpeta", 15.0);
		BDProductos.put("goma", 2.0);
		BDProductos.put("post-it", 5.0);
		
		BDStock.put("lapiz", 10);
		BDStock.put("libro mates", 10);
		BDStock.put("estuche", 10);
		BDStock.put("mochila", 10);
		BDStock.put("boli", 10);
		BDStock.put("libreta", 10);
		BDStock.put("libro ingles", 10);
		BDStock.put("carpeta", 10);
		BDStock.put("goma", 10);
		BDStock.put("post-it", 10);
		
		int accionUsuario = 0;
		
		do {
			
			//Pide al usuario que desea hacer y devuelve un numero
			accionUsuario = controlEntradaUsuario(sc);

			switch (accionUsuario) {
			case 1:
				anadirArticulo(sc,BDProductos,BDStock,STOCK_INICIAL);
				break;
			case 2:
				buscarPorArticulo(BDProductos,BDStock,sc);
				break;
			case 3:
				listarTodosArticulos(BDProductos, BDStock);
				break;
			case 4:
				generarVenta(BDProductos, BDStock,sc,precioArray,cantidadArray,IVA);
				break;
			case 5:
				System.out.println("Saliendo...");
				break;

			default:
				break;
			}
			
		} while (accionUsuario != 5);
		
		sc.close();
	}

	public static void generarVenta(Hashtable<String, Double> bDProductos, Hashtable<String, Integer> bDStock, Scanner sc, ArrayList<Double> precioArray, ArrayList<Integer> cantidadArray, double IVA) {
		System.out.println("\n - Venta -");
        		
		int numProductos = numProductos(sc);
		String nombreArticulo = "";
		
        for (int i = 0; i < numProductos; i++) {
        	int stock = 0;
        	double precio = 0;
        	System.out.print("Introduce el nombre del articulo que desea comprar: ");
        	nombreArticulo = sc.nextLine();
        	
    		Enumeration<Integer> stockElement = bDStock.elements();
    		Enumeration<Double> precioElement = bDProductos.elements();
    		Enumeration<String> articuloElement = bDProductos.keys();

    		while (articuloElement.hasMoreElements()) {
				String string = (String) articuloElement.nextElement();
				stock = (Integer) stockElement.nextElement();
				precio = (Double) precioElement.nextElement();
				if (string.toLowerCase().equals(nombreArticulo.toLowerCase())) {
					System.out.println(" -> Stock del articulo: " +stock);
					precioArray.add(precio);

				}
			}
        
            int cantidad = cantidadProducto(sc,stock,cantidadArray);
            
            bDStock.put(nombreArticulo.toLowerCase(), (stock-cantidad));
            
            double precioFinal = calcularPrecio(precioArray, cantidadArray);
            double precioIva = precioFinal + (precioFinal * IVA);
            int totalArticulos = cantidadArray.size();

            imprimirInfo(precioFinal, precioIva, totalArticulos);

            double cantPagada = cantidadPagada(sc, precioIva);
            double devolver = cantPagada - precioIva;
            System.out.println("- Cantidad a devolver -> " + devolver + "€\n");
            
		}
	
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

	
	public static int cantidadProducto(Scanner sc, int stock, ArrayList<Integer> cantidadArray) {
        int cantidad = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Cantidad comprada: ");
            String cantidadString = sc.nextLine();

            try {
                cantidad = Integer.parseInt(cantidadString);
                if (cantidad >= 0) {
                	if (cantidad <stock){
                		cantidadArray.add(cantidad);
                        valido = true;
                    } else {
        				System.out.println(" -> !La cantidad comprada no puede superar al stock ");
                    }
                }  else {
                    System.out.println("Introduce una cifra entera positiva");
                }
            } catch (Exception e) {
                System.out.println("Introduce un caracter valido");
            }
        }
        return cantidad;
    }

    public static int numProductos(Scanner sc) {
    	
    	/* Igual que en el Ejercicio1, un metodo para introducir los productos. 
         * Tiene un control para que el usuario SOLO introduzca valores correctos, utilizando el trycatch
         * y un bucle, del cual, no se sale hasta que el usuario no introduzca bien los caracteres*/
    	
        int numProductos = 0;
        boolean valido = false;
        
        do {
        	System.out.print("Numero de articulos a comprar: ");
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

	public static void anadirArticulo(Scanner sc, Hashtable<String, Double> bDProductos, Hashtable<String, Integer> bDStock, int STOCK_INICIAL) {
		
		/*Metodo para añadir articulos, le pide el nombre del articulo al usuario y un precio.
		 * En este caso valida que el precio introducido sea valido, y si no lo es vuelve a preguntarlo
		 * hasta que se introduzca uno valido*/
		
		System.out.print("\nIntroduce el nombre de el articulo: ");
		String articuloIntroducido = sc.nextLine();
		boolean validar = true;
		do {
			validar = true;
			System.out.print("Introduce el precio de el articulo: ");
			String precioString = sc.nextLine();
			
			try {
				double precio = Double.parseDouble(precioString);
				if (precio>0) {
					bDProductos.put(articuloIntroducido, precio);
					bDStock.put(articuloIntroducido, STOCK_INICIAL);
					System.out.println(" ARTICULO AÑADIDO CORRCTAMENTE\n");
				} else {
					validar = false;
					System.out.println(" -> Introduce un numero entero positivo");
				}
			} catch (Exception e) {
				validar = false;
				System.out.println(" -> Introduce un numero valido");

			}
		} while (!validar);
		
		
	}

	public static void listarTodosArticulos(Hashtable<String, Double> bDProductos, Hashtable<String, Integer> bDStock) {
		
		/*Metodo que lista todos los articulos con su precio*/
		
		Enumeration<String> articuloKey = bDProductos.keys();
		Enumeration<Double> precioElement = bDProductos.elements();
		Enumeration<Integer> stockElement = bDStock.elements();

		System.out.println();
		while (articuloKey.hasMoreElements()) {
			String articulo = (String) articuloKey.nextElement();
			Double precio = (Double) precioElement.nextElement();
			int stock = (Integer) stockElement.nextElement();
			
			System.out.println("Articulo -> "+articulo+" / Precio -> "+precio+"€ / Stock -> " + stock );
			
		}
		System.out.println();

		
	}

	public static void buscarPorArticulo(Hashtable<String, Double> bDProductos, Hashtable<String, Integer> bDStock, Scanner sc) {
		
		/*Pregunta al usuario por el nombre del articulo a buscar y devuelve el articulo y su precio*/
		
		System.out.print("\nIntroduce el articulo que desea buscar: ");
		String articuloBuscar = sc.nextLine();
		boolean encontrado = false;
		
		Enumeration<String> enumeration = bDProductos.keys();
		Enumeration<Double> precioElement = bDProductos.elements();
		Enumeration<Integer> stockElement = bDStock.elements();

		while (enumeration.hasMoreElements()) {
			String articulo = (String) enumeration.nextElement();
			Double precio = (Double) precioElement.nextElement();
			int stock = (Integer) stockElement.nextElement();

			if (articuloBuscar.toLowerCase().equals(articulo.toLowerCase())) {
				encontrado = true;
				System.out.println("\nResultados encontrados para "+articuloBuscar + ":");
				System.out.println(" - Articulo: "+articulo+ " "
						+ "\n - Precio: " + precio+"€"
						+ "\n - Stock articulo: "+stock+"\n");
			}
		}		
		
		if (!encontrado) {
			System.out.println("!Articulo no encontrado\n");
		}
		
	}

	public static int controlEntradaUsuario(Scanner sc) {
		
		/*El metodo sirve para verificar que se introduzca un numero valido
		 * y en caso de que no sea valido, el programa vuelve a preguntar por un numero
		 * hasta que el usuario introduzca uno valido*/ 
		
		boolean validar = true;
		int accion =0;
		
		do {
			validar = true;
			
			listarMenu();
			String accionString = sc.nextLine();
			
			try {
				accion = Integer.parseInt(accionString);
				if (accion<0) {
					validar = false;
					System.out.println("\n -> Caracter no valido, tiene que ser un numero entero positivo\n");
				}
			} catch (Exception e) {
				validar = false;
				System.out.println("\n -> Caracter no valido\n");
			}
		} while (!validar);
		return accion;
	}

	public static void listarMenu() {
		/*Imprime el menu*/
		System.out.println(" -- LIBRERIA --");
		System.out.println(" 1. Añadir articulo"
				+ "\n 2. Buscar por articulos"
				+ "\n 3. Listar todo"
				+ "\n 4. Generar venta"
				+ "\n 5. Salir");
		System.out.print("Que desea hacer?(1-4): ");

		
	}
}
