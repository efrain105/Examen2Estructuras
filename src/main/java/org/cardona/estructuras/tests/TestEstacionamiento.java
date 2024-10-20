//Cardona De luna Efrain Guadalupe
package org.cardona.estructuras.tests;
import org.cardona.estructuras.modelo.array.vehiculos.DatosVehiculo;
import org.cardona.estructuras.modelo.array.vehiculos.InputValidator;
import org.cardona.estructuras.modelo.array.vehiculos.Vehiculo;
import org.cardona.estructuras.modelo.array.vehiculos.camiones.Camion;
import org.cardona.estructuras.modelo.array.vehiculos.coches.Coche;
import org.cardona.estructuras.modelo.array.vehiculos.motos.Moto;

import java.time.LocalDate;
import java.util.Scanner;

public class TestEstacionamiento {

    private static int size;
    private static Vehiculo[] vehiculos;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Cardona De luna Efrain Guadalupe");
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();

            int opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine();  // Limpiar buffer de entrada

            try {
                switch (opcion) {
                    case 1:
                        saltoLineaGuiones();
                        if (vehiculos != null) {
                            System.out.println("Ya se ha creado un estacionamiento de vehículos.");

                            boolean modificarElTamaoDelEstacionamiento = confirmarAccion(sc, "modificar el tamaño del estacionamiento");
                            if (modificarElTamaoDelEstacionamiento) {
                                definirTamanoArray();
                                  // Limpiar buffer de entrada
                            }
                        } else {
                            definirTamanoArray();
                        }
                        break;
                    case 2:
                        saltoLineaGuiones();
                        if (vehiculos != null) {
                            mostrarEstacionamiento();
                            if (confirmarAccion(sc, "mover de lugar algun vehiculo")) {
                                moverVehiculoDeSlot(vehiculos, sc);
                            }
                        } else {
                            System.out.println("No se ha creado un estacionamiento de vehículos.");
                            boolean accion = confirmarAccion(sc, "crearlo ahora");
                            if (accion) {
                                definirTamanoArray();
                            } else {
                                System.out.println("No se ha creado un estacionamiento de vehículos.");
                            }
                        }
                        break;


                    case 3:
                        saltoLineaGuiones();
                        if (vehiculos == null) {
                            definirTamanoArray();
                        }
                        try {
                            insertarVehiculoEnSlot(vehiculos, sc);
                        } catch (Exception e) {
                            System.out.println("Error al crear el vehículo específico: " + e.getMessage());
                        }
                        break;

                    case 4:
                        saltoLineaGuiones();
                        try {
                            mostrarInformacionVehiculo();
                        } catch (Exception e) {
                            System.out.println("Error al mostrar información del vehículo: ");
                        }
                        break;

                    case 5:
                        saltoLineaGuiones();
                        try {
                            Vehiculo vehiculoParaActualizar = seleccionarVehiculo();

                            if (vehiculoParaActualizar instanceof Vehiculo && !(vehiculoParaActualizar instanceof Coche)
                                    && !(vehiculoParaActualizar instanceof Camion) && !(vehiculoParaActualizar instanceof Moto)) {
                                saltoLineaGuiones();
                                actualizarDatosGeneralesVehiculo(vehiculoParaActualizar, sc);
                            }
                            if (vehiculoParaActualizar instanceof Camion) {
                                saltoLineaGuiones();
                                actualizarDatosCamion((Camion) vehiculoParaActualizar, sc);
                            }
                            if (vehiculoParaActualizar instanceof Moto) {
                                saltoLineaGuiones();
                                actualizarDatosMoto((Moto) vehiculoParaActualizar, sc);
                            }
                            if (vehiculoParaActualizar instanceof Coche) {
                                saltoLineaGuiones();
                                actualizarDatosCoche((Coche) vehiculoParaActualizar, sc);
                            }

                            if (vehiculoParaActualizar == null) {
                                System.out.println("Vehiculo no encontrado");
                            }

                        } catch (Exception e) {
                            System.out.println("Error al actualizar los datos del vehículo: " + e.getMessage());
                        }
                        break;

                    case 6:
                        saltoLineaGuiones();
                        try {
                            vaciarCerrarEstacionamiento();
                        } catch (Exception e) {
                            System.out.println("Error al borrar los datos del vehículo: " + e.getMessage());
                        }
                        break;

                    case 7:
                        saltoLineaGuiones();
                        try {
                            destruirVehiculo();
                        } catch (Exception e) {
                            System.out.println("Error al destruir el vehículo: " + e.getMessage());
                        }
                        break;

                    case 8:
                        saltoLineaGuiones();
                        try {
                            boolean allNull = true;
                            if (vehiculos != null) {
                                for (Vehiculo vehiculo : vehiculos) {
                                    if (vehiculo != null) {
                                        allNull = false;
                                        break;
                                    }
                                }
                            }


                            if (allNull) {
                                System.out.println("No hay vehículos o estacionamiento aun.");
                            } else {
                                int aux = 0;
                                for (Vehiculo vehiculo : vehiculos) {
                                    aux++;
                                    if (vehiculo != null) {
                                        System.out.println(aux);
                                        System.out.println("Vehículo número estacionado en el lugar: " + (aux));
                                        System.out.println(vehiculo);
                                        System.out.println("-------------------");
                                    }


                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error al listar los vehículos: " + e.getMessage());
                        }
                        break;

                    case 9:
                        saltoLineaGuiones();
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

                if (opcion != 9) {
                    saltoLineaGuiones();
                    System.out.println("Para continuar presione Enter");
                    sc.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Se produjo un error: " + e.getMessage());
                System.out.println("Por favor, intente de nuevo.");
            }
        }


        System.out.println("Cardona De luna Efrain Guadalupe");
    }

    private static void saltoLineaGuiones() {
        System.out.println("+------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }

    private static void mostrarMenu() {
        saltoLineaGuiones();
        System.out.println("Menú de Gestión de Vehículos");
        System.out.println("1. Crear un estacionamiento de vehículos");
        System.out.println("2. Mostrar estacionamiento de vehículos");
        System.out.println("3. Crear Vehículo (Avanzado: (\"Generico\",\"Coche\", \"Moto\", \"Camión\")");
        System.out.println("4. Mostrar Información de Vehículos");
        System.out.println("5. Modificar Información de Vehículos");
        System.out.println("6. Cerrar estacionamiento");
        System.out.println("7. Eliminar vehículos del estacionamiento");
        System.out.println("8. Mostrar todos los Vehículos");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /*private static void crearVehiculoBasico() {
        System.out.print("Ingrese la marca: ");
        String marca = sc.nextLine();
        System.out.print("Ingrese el modelo: ");
        String modelo = sc.nextLine();
        int anio = capturaAnio(sc);
        System.out.print("Ingrese la matrícula: ");
        String matricula = capturaMatricula(sc, vehiculos);
        Vehiculo vehiculo = new Vehiculo(marca, modelo, anio, matricula);
        vehiculos.add(vehiculo);
        System.out.println("Vehículo creado exitosamente.");
    }*/

    private static void crearVehiculoEspecifico(int indice) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Por defecto el Vehículo se registra con: \"Marca, Modelo, Año y Matricula\"");
            System.out.println("Seleccione que otros datos registrar: ");
            System.out.println("1. Con número de motor y color");
            System.out.println("2. Con número de motor, color y dueño");
            System.out.println("0. Regresar");
            System.out.print("Ingrese su opción: ");


            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer


            switch (opcion) {
                case 0:
                    continuar = false;
                    break;
                case 1:
                    saltoLineaGuiones();
                    crearVehiculoMotorColor(indice);
                    continuar = false;
                    break;

                case 2:
                    saltoLineaGuiones();
                    crearVehiculoMotorColorDuenio(indice);
                    continuar = false;
                    break;

                default:
                    saltoLineaGuiones();
                    System.out.println("Opción no válida. Por favor, seleccione 1 o 2.");
                    System.out.println("Para continuar presione Enter");
                    sc.nextLine(); // Pausar para que el usuario pueda ver el mensaje antes de continuar
                    break;
            }

        }
    }

    private static void crearVehiculoMotorColorDuenio(int indice) {
        String marca = InputValidator.capturaMarca(sc);
        //String marca = capturaMarca(sc);
        String modelo = InputValidator.capturaModelo(sc);
        int anio = capturaAnio(sc);
        String matricula = capturaMatricula(sc, vehiculos);
        String numeroMotor = capturaNumeroMotor(sc);
        String color = capturaColor(sc);
        String duenio = capturarDuenio(sc);
        System.out.println("Tipo de vehiculo");
        System.out.println("1. Vehiculo generico");
        System.out.println("2. Moto");
        System.out.println("3. Coche");
        System.out.println("4. Camion");
        System.out.print("Ingrese que tipo de vehiculo es: ");
        boolean continuar = true;
        while (continuar) {

            int opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine();
            switch (opcion) {
                case 1:
                    saltoLineaGuiones();
                    vehiculos[indice] = new Vehiculo(marca, modelo, anio, matricula, numeroMotor, color, duenio);
                    continuar = false;
                    break;
                case 2:
                    saltoLineaGuiones();
                    Integer cilindrada = capturaCilindrada(sc);
                    Boolean tieneMalerero = capturaMaletero(sc);
                    vehiculos[indice] = new Moto(marca, modelo, anio, matricula, numeroMotor, color, duenio, cilindrada, tieneMalerero);
                    continuar = false;
                    break;
                case 3:
                    saltoLineaGuiones();
                    String carroceria = capturaTipoCarroceria(sc);
                    Integer puertas = capturaNumeroPuertas(sc);
                    vehiculos[indice] = new Coche(marca, modelo, anio, matricula, numeroMotor, color, duenio, carroceria, puertas);
                    continuar = false;
                    break;
                case 4:
                    saltoLineaGuiones();
                    Integer tonelaje = capturarTonelaje(sc);
                    Integer numeroEjes = capturaNumeroEjes(sc);
                    vehiculos[indice] = new Camion(marca, modelo, anio, matricula, numeroMotor, color, duenio, tonelaje, numeroEjes);
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
        System.out.println("Vehículo creado exitosamente.");
    }

    private static void crearVehiculoMotorColor(int indice) {
        String marca = capturaMarca(sc);
        String modelo = InputValidator.capturaModelo(sc);
        int anio = capturaAnio(sc);
        String matricula = capturaMatricula(sc, vehiculos);
        String numeroMotor = capturaNumeroMotor(sc);
        String color = capturaColor(sc);
        saltoLineaGuiones();
        System.out.println("Tipo de vehiculo");
        System.out.println("1. Vehiculo generico");
        System.out.println("2. Moto");
        System.out.println("3. Coche");
        System.out.println("4. Camion");
        System.out.print("Ingrese que tipo de vehiculo es: ");
        boolean continuar = true;
        while (continuar) {

            int opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine();
            switch (opcion) {
                case 1:
                    saltoLineaGuiones();
                    vehiculos[indice] = new Vehiculo(marca, modelo, anio, matricula, numeroMotor, color);
                    continuar = false;
                    break;
                case 2:
                    saltoLineaGuiones();
                    Integer cilindrada = capturaCilindrada(sc);
                    Boolean tieneMalerero = capturaMaletero(sc);
                    vehiculos[indice] = new Moto(marca, modelo, anio, matricula, numeroMotor, color, cilindrada, tieneMalerero);
                    continuar = false;
                    break;
                case 3:
                    saltoLineaGuiones();
                    String carroceria = capturaTipoCarroceria(sc);
                    Integer puertas = capturaNumeroPuertas(sc);
                    vehiculos[indice] = new Coche(marca, modelo, anio, matricula, numeroMotor, color, carroceria, puertas);
                    continuar = false;
                    break;
                case 4:
                    saltoLineaGuiones();
                    Integer tonelaje = capturarTonelaje(sc);
                    Integer numeroEjes = capturaNumeroEjes(sc);
                    vehiculos[indice] = new Camion(marca, modelo, anio, matricula, numeroMotor, color, tonelaje, numeroEjes);
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }

    }

    private static Vehiculo seleccionarVehiculo() {
        System.out.print("Ingrese la matrícula del vehículo: ");
        String matriculaIngresada = sc.nextLine();

        for (Vehiculo vehiculo : vehiculos) {
            // Verificar si la matrícula del vehículo es null
            if (vehiculo != null && vehiculo.getMatricula() != null && vehiculo.getMatricula().equals(matriculaIngresada)) {
                return vehiculo;
            }
        }
        // Retornar null si no se encontró el vehículo con la matrícula ingresada
        return null;
    }

    private static void mostrarInformacionVehiculo() {
        Vehiculo vehiculo = seleccionarVehiculo();
        if (vehiculo != null) {
            for (int i = 0; i < vehiculos.length; i++) {
                if (vehiculos[i] != null && vehiculos[i].equals(vehiculo)) {
                    System.out.println("Vehículo en el slot " + (i + 1) + ": " + vehiculo);
                    return;
                }
            }
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    private static void vaciarCerrarEstacionamiento() {
        // Verificar si el estacionamiento ha sido creado
        if (vehiculos == null || vehiculos.length == 0) {
            System.out.println("El estacionamiento no ha sido creado.");
            return;
        }

        boolean hayVehiculos = false;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                hayVehiculos = true;
                break;
            }
        }

        if (!hayVehiculos) {
            System.out.println("No hay vehículos en el estacionamiento.");
        }

        // quitar vehículos del estacionamiento uno por uno
        if (confirmarAccion(sc, "eliminar el estacionamiento")) {
            if (vehiculos == null) {
                System.out.println("El estacionamiento ya ha sido cerrado.");
                return;
            } else {
                for (int i = 0; i < vehiculos.length; i++) {
                    if (vehiculos[i] != null) {
                        System.out.println("Retirando vehículo del slot " + (i + 1) + ": " + vehiculos[i]);
                        vehiculos[i] = null;
                    }
                }
                vehiculos = null;
                System.out.println("Estacionamiento vaciado y cerrado.");
            }

        } else {
            System.out.println("Acción cancelada.");
        }
    }

    private static void destruirVehiculo() {
        if (vehiculos == null || vehiculos.length == 0) {
            System.out.println("El estacionamiento no ha sido creado.");
            return;
        }

        boolean hayVehiculos = false;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                hayVehiculos = true;
                break;
            }
        }

        if (!hayVehiculos) {
            System.out.println("No hay vehículos en el estacionamiento.");
        }


        System.out.println("Seleccione el método para retirar el vehículo:");
        System.out.println("1. Por número de cajón");
        System.out.println("2. Por placas del vehículo");
        System.out.print("Ingrese su opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        Vehiculo vehiculo = null;
        int indice = -1;

        switch (opcion) {
            case 1:
                mostrarEstacionamiento();
                System.out.print("Ingrese el número de cajón: ");
                int cajon = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                cajon--; // Convertir a índice 0-base
                if (cajon >= 0 && cajon < vehiculos.length) {
                    vehiculo = vehiculos[cajon];
                    indice = cajon;
                } else {
                    System.out.println("Número de cajón inválido.");
                    return;
                }
                break;
            case 2:
                vehiculo = seleccionarVehiculo();
                if (vehiculo != null) {
                    for (int i = 0; i < vehiculos.length; i++) {
                        if (vehiculos[i] != null && vehiculos[i].equals(vehiculo)) {
                            indice = i;
                            break;
                        }
                    }
                }
                mostrarEstacionamiento();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (vehiculo != null && indice != -1) {
            System.out.println("Vehículo a retirar: " + vehiculo);
            if (confirmarAccion(sc, "retirar el vehículo")) {
                vehiculos[indice] = null;
                System.out.println("Vehículo retirado y espacio liberado.");
            } else {
                System.out.println("Acción cancelada.");
            }
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    private static String capturaMarca(Scanner sc) {
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

    private static Integer capturaAnio(Scanner sc) {
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

    private static String capturaMatricula(Scanner sc, Vehiculo[] vehiculos) {
        String matricula;
        boolean matriculaExiste;

        while (true) {
            matriculaExiste = false; // reiniciamos el estado de la matrícula existente

            System.out.print("Por favor, ingrese la matrícula: ");
            matricula = sc.nextLine().trim();

            // validar formato de matricula
            if (!matricula.isEmpty() && matricula.matches("^[A-Z0-9-]{1,10}$")) {
                // comprobar si la matricula ya existe en el array de vehiculos
                for (Vehiculo v : vehiculos) {
                    if (v != null && matricula.equalsIgnoreCase(v.getMatricula())) {
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

    private static String capturaNumeroMotor(Scanner sc) {
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

    private static String capturaColor(Scanner sc) {
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

    private static String capturarDuenio(Scanner sc) {
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

    private static void actualizarDatosGeneralesVehiculo(Vehiculo vehiculo, Scanner sc) {
        int opcionUp = -1;
        boolean continuar = true;

        String nuevaMarca = vehiculo.getMarca();
        String nuevoModelo = vehiculo.getModelo();
        Integer nuevoAnio = vehiculo.getAnio();
        String nuevaMatricula = vehiculo.getMatricula();
        String nuevoNumeroMotor = vehiculo.getNumeroMotor();
        String nuevoColor = vehiculo.getColor();
        String nuevoDuenio = vehiculo.getDuenio();


        while (continuar) {

            System.out.println("Seleccione el dato que quiere actualizar: ");
            System.out.println("1. Marca: " + vehiculo.getMarca());
            System.out.println("2. Modelo: " + vehiculo.getModelo());
            System.out.println("3. Año: " + vehiculo.getAnio());
            System.out.println("4. Matrícula: " + vehiculo.getMatricula());
            System.out.println("5. Número de motor: " + vehiculo.getNumeroMotor());
            System.out.println("6. Color: " + vehiculo.getColor());
            System.out.println("7. Nombre dueño: " + vehiculo.getDuenio());
            System.out.println("8. Regresar al menú anterior y guardar cambios");
            try {
                opcionUp = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine(); // limpiar buffer


            switch (opcionUp) {
                case 1:
                    nuevaMarca = capturaMarca(sc);
                    //vehiculo.setMarca(nuevaMarca);
                    System.out.println("Marca actualizada correctamente a: " + nuevaMarca);
                    break;
                case 2:
                    nuevoModelo = InputValidator.capturaModelo(sc);
                    //vehiculo.setModelo(nuevoModelo);
                    System.out.println("Modelo actualizado correctamente a: " + nuevoModelo);
                    break;
                case 3:
                    nuevoAnio = capturaAnio(sc);
                    // vehiculo.setAnio(nuevoAnio);
                    System.out.println("Año actualizado correctamente a: " + nuevoAnio);
                    break;
                case 4:
                    nuevaMatricula = capturaMatricula(sc, vehiculos);
                    //vehiculo.setMatricula(nuevaMatricula);
                    System.out.println("Matrícula actualizada correctamente a: " + nuevaMatricula);
                    break;
                case 5:
                    nuevoNumeroMotor = capturaNumeroMotor(sc);
                    //vehiculo.setNumeroMotor(nuevoNumeroMotor);
                    System.out.println("Número de motor actualizado correctamente a: " + nuevoNumeroMotor);
                    break;
                case 6:
                    nuevoColor = capturaColor(sc);
                    //vehiculo.setColor(nuevoColor);
                    System.out.println("Color actualizado correctamente a: " + nuevoColor);
                    break;
                case 7:
                    nuevoDuenio = capturarDuenio(sc);
                    // vehiculo.setDuenio(nuevoDuenio);
                    System.out.println("Nombre del dueño actualizado correctamente a: " + nuevoDuenio);
                    break;

                case 8:
                    System.out.println("Regresando al menú anterior...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, seleccione una opción válida.");
                    break;

            }


            if (opcionUp != 8) {
                System.out.println("Para continuar presione Enter");
                sc.nextLine();
            }

            DatosVehiculo datosVehiculo = new DatosVehiculo(nuevaMarca, nuevoModelo, nuevoAnio, nuevaMatricula, nuevoNumeroMotor, nuevoColor, nuevoDuenio);
            vehiculo.actualizarDatosVehiculos(datosVehiculo);

        }


    }

    private static void actualizarDatosCoche(Coche coche, Scanner sc) {
        int opcionUp = -1;
        boolean continuar = true;
        while (continuar) {

            System.out.println("Seleccione el dato que quiere actualizar: ");
            System.out.println("1. Marca: " + coche.getMarca());
            System.out.println("2. Modelo: " + coche.getModelo());
            System.out.println("3. Año: " + coche.getAnio());
            System.out.println("4. Matrícula: " + coche.getMatricula());
            System.out.println("5. Número de motor: " + coche.getNumeroMotor());
            System.out.println("6. Color: " + coche.getColor());
            System.out.println("7. Nombre dueño: " + coche.getDuenio());
            System.out.println("8. Tipo de carroceria: " + coche.getTipoCarroceria());
            System.out.println("9. numero de puertas: " + coche.getNumeroPuertas());
            System.out.println("10. Regresar al menú anterior");
            try {
                opcionUp = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine(); // limpiar buffer


            switch (opcionUp) {
                case 1:
                    String nuevaMarca = capturaMarca(sc);
                    coche.setMarca(nuevaMarca);
                    System.out.println("Marca actualizada correctamente a: " + coche.getMarca());
                    break;
                case 2:
                    String nuevoModelo = InputValidator.capturaModelo(sc);
                    coche.setModelo(nuevoModelo);
                    System.out.println("Modelo actualizado correctamente a: " + coche.getModelo());
                    break;
                case 3:
                    Integer nuevoAnio = capturaAnio(sc);
                    coche.setAnio(nuevoAnio);
                    System.out.println("Año actualizado correctamente a: " + coche.getAnio());
                    break;
                case 4:
                    String nuevaMatricula = capturaMatricula(sc, vehiculos);
                    coche.setMatricula(nuevaMatricula);
                    System.out.println("Matrícula actualizada correctamente a: " + coche.getMatricula());
                    break;
                case 5:
                    String nuevoNumeroMotor = capturaNumeroMotor(sc);
                    coche.setNumeroMotor(nuevoNumeroMotor);
                    System.out.println("Número de motor actualizado correctamente a: " + coche.getNumeroMotor());
                    break;
                case 6:
                    String nuevoColor = capturaColor(sc);
                    coche.setColor(nuevoColor);
                    System.out.println("Color actualizado correctamente a: " + coche.getColor());
                    break;
                case 7:
                    String nuevoDuenio = capturarDuenio(sc);
                    coche.setDuenio(nuevoDuenio);
                    System.out.println("Nombre del dueño actualizado correctamente a: " + coche.getDuenio());
                    break;
                case 8:
                    String carroceria = capturaTipoCarroceria(sc);
                    coche.setTipoCarroceria(carroceria);
                    System.out.println("Carroceria actualizado correctamente a: " + coche.getTipoCarroceria());
                    break;
                case 9:
                    Integer puertas = capturaNumeroPuertas(sc);
                    coche.setNumeroPuertas(puertas);
                    System.out.println("Numero de puertas actualizado correctamente a: " + coche.getNumeroPuertas());
                    break;
                case 10:
                    System.out.println("Regresando al menú anterior...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, seleccione una opción válida.");
                    break;
            }

            if (opcionUp != 10) {
                saltoLineaGuiones();
                System.out.println("Para continuar presione Enter");
                sc.nextLine();
            }

        }


    }

    private static void actualizarDatosCamion(Camion camion, Scanner sc) {
        int opcionUp = -1;
        boolean continuar = true;
        while (continuar) {


            System.out.println("Seleccione el dato que quiere actualizar: ");
            System.out.println("1. Marca: " + camion.getMarca());
            System.out.println("2. Modelo: " + camion.getModelo());
            System.out.println("3. Año: " + camion.getAnio());
            System.out.println("4. Matrícula: " + camion.getMatricula());
            System.out.println("5. Número de motor: " + camion.getNumeroMotor());
            System.out.println("6. Color: " + camion.getColor());
            System.out.println("7. Nombre dueño: " + camion.getDuenio());
            System.out.println("8. Tonelaje: " + camion.getTonelaje());
            System.out.println("9. Numero de ejes: " + camion.getNumeroEjes());
            System.out.println("10. Regresar al menú anterior");
            try {
                opcionUp = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine(); // limpiar buffer


            switch (opcionUp) {
                case 1:
                    String nuevaMarca = capturaMarca(sc);
                    camion.setMarca(nuevaMarca);
                    System.out.println("Marca actualizada correctamente a: " + camion.getMarca());
                    break;
                case 2:
                    String nuevoModelo = InputValidator.capturaModelo(sc);
                    camion.setModelo(nuevoModelo);
                    System.out.println("Modelo actualizado correctamente a: " + camion.getModelo());
                    break;
                case 3:
                    Integer nuevoAnio = capturaAnio(sc);
                    camion.setAnio(nuevoAnio);
                    System.out.println("Año actualizado correctamente a: " + camion.getAnio());
                    break;
                case 4:
                    String nuevaMatricula = capturaMatricula(sc, vehiculos);
                    camion.setMatricula(nuevaMatricula);
                    System.out.println("Matrícula actualizada correctamente a: " + camion.getMatricula());
                    break;
                case 5:
                    String nuevoNumeroMotor = capturaNumeroMotor(sc);
                    camion.setNumeroMotor(nuevoNumeroMotor);
                    System.out.println("Número de motor actualizado correctamente a: " + camion.getNumeroMotor());
                    break;
                case 6:
                    String nuevoColor = capturaColor(sc);
                    camion.setColor(nuevoColor);
                    System.out.println("Color actualizado correctamente a: " + camion.getColor());
                    break;
                case 7:
                    String nuevoDuenio = capturarDuenio(sc);
                    camion.setDuenio(nuevoDuenio);
                    System.out.println("Nombre del dueño actualizado correctamente a: " + camion.getDuenio());
                    break;
                case 8:
                    Integer tonelaje = capturarTonelaje(sc);
                    camion.setTonelaje(tonelaje);
                    System.out.println("Carroceria actualizado correctamente a: " + camion.getTonelaje());
                    break;
                case 9:
                    Integer numeroDeEjes = capturaNumeroEjes(sc);
                    camion.setNumeroEjes(numeroDeEjes);
                    System.out.println("Numero de puertas actualizado correctamente a: " + camion.getNumeroEjes());
                    break;
                case 10:
                    System.out.println("Regresando al menú anterior...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, seleccione una opción válida.");
                    break;
            }

            if (opcionUp != 10) {
                saltoLineaGuiones();
                System.out.println("Para continuar presione Enter");
                sc.nextLine();
            }

        }


    }

    private static void actualizarDatosMoto(Moto moto, Scanner sc) {
        int opcionUp = -1;
        boolean continuar = true;
        while (continuar) {


            System.out.println("Seleccione el dato que quiere actualizar: ");
            System.out.println("1. Marca: " + moto.getMarca());
            System.out.println("2. Modelo: " + moto.getModelo());
            System.out.println("3. Año: " + moto.getAnio());
            System.out.println("4. Matrícula: " + moto.getMatricula());
            System.out.println("5. Número de motor: " + moto.getNumeroMotor());
            System.out.println("6. Color: " + moto.getColor());
            System.out.println("7. Nombre dueño: " + moto.getDuenio());
            System.out.println("8. Cilindraje: " + moto.getCilindrada());
            System.out.println("9. Tiene maletero: " + (Boolean.TRUE.equals(moto.getTieneMaletero()) ? "Si" : "No"));
            System.out.println("10. Regresar al menú anterior");
            try {
                opcionUp = sc.nextInt();
            } catch (Exception e) {

            }
            sc.nextLine(); // limpiar buffer


            switch (opcionUp) {
                case 1:
                    String nuevaMarca = capturaMarca(sc);
                    moto.setMarca(nuevaMarca);
                    System.out.println("Marca actualizada correctamente a: " + moto.getMarca());
                    break;
                case 2:
                    String nuevoModelo = InputValidator.capturaModelo(sc);
                    moto.setModelo(nuevoModelo);
                    System.out.println("Modelo actualizado correctamente a: " + moto.getModelo());
                    break;
                case 3:
                    Integer nuevoAnio = capturaAnio(sc);
                    moto.setAnio(nuevoAnio);
                    System.out.println("Año actualizado correctamente a: " + moto.getAnio());
                    break;
                case 4:
                    String nuevaMatricula = capturaMatricula(sc, vehiculos);
                    moto.setMatricula(nuevaMatricula);
                    System.out.println("Matrícula actualizada correctamente a: " + moto.getMatricula());
                    break;
                case 5:
                    String nuevoNumeroMotor = capturaNumeroMotor(sc);
                    moto.setNumeroMotor(nuevoNumeroMotor);
                    System.out.println("Número de motor actualizado correctamente a: " + moto.getNumeroMotor());
                    break;
                case 6:
                    String nuevoColor = capturaColor(sc);
                    moto.setColor(nuevoColor);
                    System.out.println("Color actualizado correctamente a: " + moto.getColor());
                    break;
                case 7:
                    String nuevoDuenio = capturarDuenio(sc);
                    moto.setDuenio(nuevoDuenio);
                    System.out.println("Nombre del dueño actualizado correctamente a: " + moto.getDuenio());
                    break;
                case 8:
                    Integer cilindraje = capturaCilindrada(sc);
                    moto.setCilindrada(cilindraje);
                    System.out.println("Cilindraje actualizado correctamente a: " + moto.getCilindrada());
                    break;
                case 9:
                    Boolean maletero = capturaMaletero(sc);
                    moto.setTieneMaletero(maletero);
                    String male = moto.getTieneMaletero() ? "sí" : "no";
                    System.out.println("Tiene maletero: " + male);
                    break;
                case 10:
                    System.out.println("Regresando al menú anterior...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, seleccione una opción válida.");
                    break;
            }

            if (opcionUp != 10) {
                saltoLineaGuiones();
                System.out.println("Para continuar presione Enter");
                sc.nextLine();
            }

        }


    }

    private static void insertarVehiculoEnSlot(Vehiculo[] vehiculos, Scanner sc) {
        System.out.println("Slots disponibles:");
        for (int i = 0; i < vehiculos.length; i++) {
            if (vehiculos[i] == null) {
                System.out.println("Slot " + (i + 1) + " está libre.");
            }
        }

        System.out.print("Ingrese el índice del slot donde desea insertar el vehículo: ");
        int indice = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        indice--; // Restar 1 para que el índice sea 0-based
        if (indice >= 0 && indice < vehiculos.length) {
            if (vehiculos[indice] == null) {
                vehiculos[indice] = new Vehiculo(); // Crear un vehículo vacío

                crearVehiculoEspecifico(indice);// Capturar y asignar datos al vehículo
            } else {
                System.out.println("El slot ya está ocupado.");
            }
        } else {
            System.out.println("Índice fuera de rango. Por favor, ingrese un índice válido.");
        }
    }

    private static void moverVehiculoDeSlot(Vehiculo[] vehiculos, Scanner sc) {
    // Verificar si hay vehículos en el estacionamiento
    boolean hayVehiculos = false;
    for (Vehiculo vehiculo : vehiculos) {
        if (vehiculo != null) {
            hayVehiculos = true;
            break;
        }
    }

    if (!hayVehiculos) {
        System.out.println("No hay vehículos en el estacionamiento para mover.");
        return;
    }

    // Mostrar los slots ocupados
    System.out.println("Slots ocupados:");
    for (int i = 0; i < vehiculos.length; i++) {
        if (vehiculos[i] != null) {
            System.out.println("Slot " + (i + 1) + " ocupado por " + vehiculos[i].getMarca() + " " + vehiculos[i].getModelo());
        }
    }

    int indiceOrigen = -1;
    while (true) {
        // Pedir al usuario el índice del slot del vehículo que desea mover
        System.out.print("Ingrese el índice del slot del vehículo que desea mover: ");
        indiceOrigen = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        indiceOrigen--; // Restar 1 para que el índice sea 0-based

        if (indiceOrigen >= 0 && indiceOrigen < vehiculos.length && vehiculos[indiceOrigen] != null) {
            break;
        } else {
            System.out.println("Por favor, ingrese un cajón donde haya vehículo.");
            System.out.println("Presione Enter para continuar");
            sc.nextLine();
        }
    }

    // Mostrar los slots disponibles
    System.out.println("Slots disponibles:");
    for (int i = 0; i < vehiculos.length; i++) {
        if (vehiculos[i] == null) {
            System.out.println("Slot " + (i + 1) + " está libre.");
        }
    }

    // Pedir al usuario el índice del slot de destino
    System.out.print("Ingrese el índice del slot de destino: ");
    int indiceDestino = sc.nextInt();
    sc.nextLine(); // Limpiar buffer
    indiceDestino--; // Restar 1 para que el índice sea 0-based

    if (indiceDestino < 0 || indiceDestino >= vehiculos.length || vehiculos[indiceDestino] != null) {
        System.out.println("Índice de destino inválido o el slot ya está ocupado.");
        return;
    }

    // Mover el vehículo al slot de destino
    vehiculos[indiceDestino] = vehiculos[indiceOrigen];
    vehiculos[indiceOrigen] = null;

    System.out.println("Vehículo movido exitosamente del slot " + (indiceOrigen + 1) + " al slot " + (indiceDestino + 1) + ".");
    mostrarEstacionamiento();
}

    private static void definirTamanoArray() {
        while (true) {
            System.out.println("El tamaño del estacionamiento se asignará entre 3 y 7");

            int tamano = 3 + (int) (Math.random() * 5);
            if (tamano >= 3 && tamano <= 7) {
                vehiculos = new Vehiculo[tamano]; // Initialize the array here
                System.out.println("Tamaño del Estacionamiento definido a " + tamano);
                mostrarEstacionamiento();
                break;
            } else {
                System.out.println("El tamaño debe estar entre 3 y 7.");
            }
        }
    }

    private static boolean confirmarAccion(Scanner sc, String accion) {
        System.out.println("¿Quisieras " + accion + "? (sí/no):");
        String respuesta = sc.next().trim().toLowerCase();
        sc.nextLine();
        return respuesta.equals("sí") || respuesta.equals("si");
    }

    private static void mostrarEstacionamiento() {
        System.out.println("Estacionamiento de vehículos:");
        System.out.println("+------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.println("| Slot | Tipo                 | Dueño                | Marca                | Modelo               | Año                  | Matrícula            |");
        System.out.println("+------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");

        for (int i = 0; i < vehiculos.length; i++) {
            Vehiculo vehiculo = vehiculos[i];
            if (vehiculo != null) {
                String tipoVehiculo = "Genérico";
                if (vehiculo instanceof Moto) {
                    tipoVehiculo = "Moto";
                } else if (vehiculo instanceof Coche) {
                    tipoVehiculo = "Coche";
                } else if (vehiculo instanceof Camion) {
                    tipoVehiculo = "Camión";
                }
                System.out.printf("| %-4d | %-20s | %-20s | %-20s | %-20s | %-20d | %-20s |\n",
                        i + 1,
                        tipoVehiculo,
                        vehiculo.getDuenio() != null ? vehiculo.getDuenio() : "N/A",
                        vehiculo.getMarca(),
                        vehiculo.getModelo(),
                        vehiculo.getAnio(),
                        vehiculo.getMatricula());
            } else {
                System.out.printf("| %-4d | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                        i + 1,
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A");
            }
        }

        System.out.println("+------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }

}
