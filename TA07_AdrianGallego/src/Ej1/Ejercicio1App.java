package Ej1;

import java.util.Hashtable;
import java.util.Scanner;

public class Ejercicio1App {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("--CURSO PROGRAMACION NOTA MEDIA");
    	
    	//Declaro el numero de alumnos y examenes fijo
    	int numAlumnos = 2;
    	int numExamenes = 3;
    	
    	Hashtable<String, Double> notasMedias = new Hashtable<String, Double>();
    	
    	//Bucle que recorre la cantidad de alumnos (en este caso se ha declarado arriba) para preguntar el nombre y la media
    	
    	for (int i = 0; i < numAlumnos; i++) {
    		 System.out.print("\nNombre del alumno: ");
             String nombreAlumno = sc.nextLine();
             
             /*La media de el alumno la preugunta a traves de este metodo
              * en el metodo pregunta por las notas del alumno y una vez introducidas
              * devuelve la media directamente*/
             
             double mediaAlumno = mediaAlumno(sc, numExamenes);
             
             
             notasMedias.put(nombreAlumno, mediaAlumno);
             System.out.println("Nota media: " + mediaAlumno);
             
         }

         sc.close();

         System.out.println("\n" + notasMedias.toString());
    	

    }

    /*He creado este metodo porque queria controlar la entrada del usuario,
     * es decir, comprobar que el usuario introduce caracteres validos.
     * Como ocupa bastnte he decidido hacerlo en un metodo aparte */
    
    public static double mediaAlumno(Scanner sc, int numExamenes) {
        double mediaAlumno = 0;
        boolean valido = false;

        /*Este do-while es por si el usuario introduce un numero que no es valido.
         * La variable 'valido', como podemos ver se pone true al inicio de el bucle 
         * si el usuario introduce bien los numeros saldra del bucle a la primera, pero si no
         * pasa a ser FALSE y se vuelve a ejecutar el codigo*/
        do {
            valido = true;
            for (int i = 0; i < numExamenes; i++) {
                System.out.print("Nota examen " + (i + 1) + ": ");
                String notaString = sc.nextLine();

                try {
                    double nota = Double.parseDouble(notaString);
                    if (nota >= 0) {
                        mediaAlumno += nota;
                    } else {
                        System.out.println("\n -> Ha introducido una nota que no es valida");
                        valido = false;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("\n -> Ha introducido un caracter que no es valido");
                    valido = false;
                    break;
                }
            }

            if (!valido) {
                System.out.println("Introduzca notas validas");
                mediaAlumno = 0;
            }
        } while (!valido);

        mediaAlumno = mediaAlumno / numExamenes;

        return mediaAlumno;
    }
	


}
