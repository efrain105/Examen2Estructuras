package org.cardona.estructuras.modelo.array.vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InputValidator{

    public static String capturaMarca(Scanner sc) {
        String marca;
        while (true) {
            System.out.print("Por favor, ingrese la marca: ");
            marca = sc.nextLine().trim();  // Elimina espacios al inicio y al final
            if (!marca.isBlank() && marca.matches("^[A-Za-z0-9 ]{1,50}$")) {  // Verifica que no esté en blanco
                break;
            } else {
                System.out.println("Marca inválida. Solo se permiten letras, números y espacios. Máximo 50 caracteres.");
            }
        }
        return marca;
    }

    public static String capturaModelo(Scanner sc) {
        String modelo;
        while (true) {
            System.out.print("Por favor, ingrese el modelo: ");
            modelo = sc.nextLine().trim();
            if (!modelo.isEmpty() && modelo.matches("^[A-Za-z0-9 ]{1,50}$")) { // permite letras, numeros y espacios, maximo 50 caracteres
                break;
            } else {
                System.out.println("Modelo inválido. Solo se permiten letras, números y espacios. Máximo 50 caracteres.");
            }
        }
        return modelo;
    }

    public static Integer capturaAnio(Scanner sc) {
        int anio;
        while (true) {
            System.out.print("Por favor, ingrese el año (formato YYYY): ");
            String anioStr = sc.nextLine().trim();
            if (anioStr.matches("\\d{4}")) {
                try {
                    anio = Integer.parseInt(anioStr);
                    if (anio > 1900 && anio <= LocalDate.now().getYear()) {
                        break;
                    } else {
                        System.out.println("Año inválido. Debe estar entre 1900 y el año actual.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Año inválido. Debe ser un número de cuatro dígitos.");
                }
            } else {
                System.out.println("Formato de año incorrecto. Debe ser un número de cuatro dígitos.");
            }
        }
        return anio;
    }

    public static String capturaMatricula(Scanner sc, ArrayList<Vehiculo> vehiculos) {
        String matricula;
        boolean matriculaExiste;

        while (true) {
            matriculaExiste = false; // reiniciamos el estado de la matrícula existente

            System.out.print("Por favor, ingrese la matrícula: ");
            matricula = sc.nextLine().trim();

            // validar formato de matricula
            if (!matricula.isEmpty() && matricula.matches("^[A-Z0-9-]{1,10}$")) {
                // comprobar si la matricula ya existe en la lista de vehiculos
                for (Vehiculo v : vehiculos) {
                    if (matricula.equalsIgnoreCase(v.getMatricula())) {
                        matriculaExiste = true;
                        System.out.println("La matrícula ya existe. Por favor, ingrese una matrícula diferente.");
                        break;
                    }
                }

                if (!matriculaExiste) {
                    return matricula; // matricula valida y nica salimos del bucle y retornamos
                }
            } else {
                System.out.println("Matrícula inválida. Solo se permiten letras, números y guiones. Máximo 10 caracteres.");
            }
        }
    }

    public static String capturaNumeroMotor(Scanner sc) {
        String numeroMotor;
        while (true) {
            System.out.print("Por favor, ingrese el número de motor: ");
            numeroMotor = sc.nextLine().trim();
            if (!numeroMotor.isEmpty() && numeroMotor.matches("^[A-Z0-9]{1,15}$")) { // limita el número de motor a un maximo de 15 caracteres
                break;
            } else {
                System.out.println("Número de motor inválido. Solo se permiten letras y números. Máximo 15 caracteres.");
            }
        }
        return numeroMotor;
    }

    public static String capturaColor(Scanner sc) {
        String color;
        while (true) {
            System.out.print("Por favor, ingrese el color: ");
            color = sc.nextLine().trim();
            if (!color.isEmpty() && color.matches("^[A-Za-z ]{1,20}$")) { // permite letras y espacios, maximo 20 caracteres
                break;
            } else {
                System.out.println("Color inválido. Solo se permiten letras y espacios. Máximo 20 caracteres.");
            }
        }
        return color;
    }

    public static String capturarDuenio(Scanner sc) {
        String duenio;
        while (true) {
            System.out.print("Por favor, ingrese el nombre del dueño: ");
            duenio = sc.nextLine().trim();
            if (!duenio.isEmpty() && duenio.matches("^[A-Za-z ]{1,50}$")) { // permite letras y espacios, maximo 50 caracteres
                break;
            } else {
                System.out.println("Nombre inválido. Solo se permiten letras y espacios. Máximo 50 caracteres.");
            }
        }

        return duenio;
    }

    public static Integer capturaCilindrada(Scanner sc) {
        while (true) {
            System.out.print("Por favor, ingrese la cilindrada de la moto: ");
            String input = sc.nextLine().trim();

            if (input.matches("\\d{2,4}")) {
                return Integer.parseInt(input);  // sí son de 2-4 dígitos
            } else {
                System.out.println("Error: La cilindrada debe ser un número de 2 a 4 dígitos.");
            }
        }
    }

    public static Boolean capturaMaletero(Scanner sc) {
        while (true) {
            System.out.print("Por favor, ingrese si la moto tiene maletero (sí/no): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("sí") || input.equals("si")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Debe responder 'sí' o 'no'.");
            }
        }
    }

    public static String capturaTipoCarroceria(Scanner sc) {
        while (true) {
            System.out.print("Por favor, ingrese el tipo de carrocería (Sedán, Hatchback, SUV, etc.): ");
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Error: El tipo de carrocería no puede estar vacío.");
            }
        }
    }

    public static Integer capturaNumeroPuertas(Scanner sc) {
        while (true) {
            System.out.print("Por favor, ingrese el número de puertas (entre 2 y 5): ");
            String input = sc.nextLine().trim();

            if (input.matches("\\d") && Integer.parseInt(input) >= 2 && Integer.parseInt(input) <= 5) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Error: El número de puertas debe ser entre 2 y 5.");
            }
        }
    }

    public static Integer capturarTonelaje(Scanner sc) {
        while (true) {
            System.out.print("Por favor, ingrese la capacidad de carga en toneladas: ");
            String input = sc.nextLine().trim();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Error: El tonelaje debe ser un número positivo.");
            }
        }
    }

    public static Integer capturaNumeroEjes(Scanner sc) {
        while (true) {
            System.out.print("Por favor, ingrese el número de ejes (entre 2 y 4): ");
            String input = sc.nextLine().trim();

            if (input.matches("\\d") && Integer.parseInt(input) >= 2 && Integer.parseInt(input) <= 4) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Error: El número de ejes debe ser entre 2 y 4.");
            }
        }
    }

}
