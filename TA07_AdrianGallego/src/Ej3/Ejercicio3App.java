package Ej3;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Ejercicio3App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Hashtable<String, Double> BDProductos = new Hashtable<String, Double>();
		
		//Base de datos de tienda (es una libreria)
		BDProductos.put("Lapiz", 0.5);
		BDProductos.put("Libro mates", 20.0);
		BDProductos.put("Estuche", 10.0);
		BDProductos.put("Mochila", 10.0);
		BDProductos.put("Boli", 1.0);
		BDProductos.put("Libreta", 4.0);
		BDProductos.put("Libro ingles", 19.0);
		BDProductos.put("Carpeta", 15.0);
		BDProductos.put("Goma", 2.0);
		BDProductos.put("Post-it", 5.0);
		
		int accionUsuario = 0;
		
		do {
			
			//Pide al usuario que desea hacer y devuelve un numero
			accionUsuario = controlEntradaUsuario(sc);

			switch (accionUsuario) {
			case 1:
				anadirArticulo(sc,BDProductos);
				break;
			case 2:
				buscarPorArticulo(BDProductos,sc);
				break;
			case 3:
				listarTodosArticulos(BDProductos);
				break;
			case 4:
				System.out.println("Saliendo...");
				break;

			default:
				break;
			}
			
		} while (accionUsuario != 4);
	}

	public static void anadirArticulo(Scanner sc, Hashtable<String, Double> bDProductos) {
		
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

	public static void listarTodosArticulos(Hashtable<String, Double> bDProductos) {
		
		/*Metodo que lista todos los articulos con su precio*/
		
		Enumeration<String> articuloKey = bDProductos.keys();
		Enumeration<Double> precioElement = bDProductos.elements();

		System.out.println();
		while (articuloKey.hasMoreElements()) {
			String articulo = (String) articuloKey.nextElement();
			Double precio = (Double) precioElement.nextElement();
			
			System.out.println("Articulo -> "+articulo+" / Precio -> "+precio+"€");
			
		}
		System.out.println();

		
	}

	public static void buscarPorArticulo(Hashtable<String, Double> bDProductos, Scanner sc) {
		
		/*Pregunta al usuario por el nombre del articulo a buscar y devuelve el articulo y su precio*/
		
		System.out.print("\nIntroduce el articulo que desea buscar: ");
		String articuloBuscar = sc.nextLine();
		boolean encontrado = false;
		
		Enumeration<String> enumeration = bDProductos.keys();
		Enumeration<Double> precioElement = bDProductos.elements();

		while (enumeration.hasMoreElements()) {
			String articulo = (String) enumeration.nextElement();
			Double precio = (Double) precioElement.nextElement();

			if (articuloBuscar.toLowerCase().equals(articulo.toLowerCase())) {
				encontrado = true;
				System.out.println("\nResultados encontrados para "+articuloBuscar + ":");
				System.out.println(" - Articulo: "+articulo+ " "
						+ "\n - Precio: " + precio+"€\n");
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
				+ "\n 4. Salir");
		System.out.print("Que desea hacer?(1-4): ");

		
	}

}
