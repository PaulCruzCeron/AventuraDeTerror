import java.util.*; // Importación de toda la biblioteca de java

public class AventuraDeTerror {

    // Constantes
    static final int ENERGIA_MAXIMA = 100;
    static final int PERDIDA_ENERGIA = 10;
    static final int CORAZONES_PARA_RECUPERAR = 3;

    // Variables del jugador
    static String nombreJugador;
    static int energiaJugador = ENERGIA_MAXIMA;
    static int corazones = 0;
    static List<String> demoniosCapturados = new ArrayList<>();
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        bienvenida();  // Presentación y bienvenida al jugador

        //Aspectos generales de cada escenario
        if (energiaJugador > 0) escenario(1, "Astaroth", "DESAFÍO DE ASTAROTH", () -> desafioPiedraPapelTijera());
        if (energiaJugador > 0) escenario(2, "Belial", "PRUEBA DE MEMORIA DE BELIAL", () -> pruebaMemoria());
        if (energiaJugador > 0) escenario(3, "Azazel", "LANZAMIENTO DE DADOS CONTRA AZAZEL", () -> lanzamientoDados());
        if (energiaJugador > 0) escenario(4, "Lilith", "ADIVINANZAS DE LILITH", () -> acertijos());
        if (energiaJugador > 0) escenario(5, "Lucifer", "TRES EN RAYA CONTRA LUCIFER", () -> tresEnRaya());

        // Fin del juego con estadísticas o derrota
        if (energiaJugador > 0) {
            System.out.println("\n¡Felicidades! Has sobrevivido a todos los demonios.");
            mostrarEstadisticas();
        } else {
            System.out.println("\nTu aventura termina aquí... la oscuridad te consume.");
        }

        entrada.close(); // Finaliza el juego
    }

    // Bienvenida al jugador
    public static void bienvenida() {
        System.out.println("===================================================================");
        System.out.println("      BIENVENIDO A LA MANSIÓN DE LOS DEMONIOS PERDIDOS");
        System.out.println("===================================================================");
        System.out.println("\nUna niebla densa cubre el suelo, mientras el aire helado te corta la piel.");
        System.out.println("Delante de ti se alza una mansión antigua, oscura y abandonada...");
        System.out.println("Las ventanas están cubiertas de polvo, y las sombras parecen moverse por sí solas.");
        System.out.println("Al cruzar el umbral, el suelo de madera cruje bajo tus pies y un escalofrío recorre tu espalda.");
        System.out.println("Sientes una presencia que te observa desde las profundidades de la oscuridad...");
        System.out.println("\nEsta mansión esconde secretos que nunca debieron ser desenterrados.");
        System.out.println("Demonios antiguos habitan en sus rincones, criaturas que acechan en las sombras y alimentan sus fuerzas con el miedo.");
        System.out.println("Solo aquellos valientes que aceptan el riesgo de enfrentarse a las fuerzas del mal logran salir con vida.");
        System.out.print("\n¿Cuál es tu nombre, valiente aventurero que desafía las sombras? ");
        nombreJugador = entrada.nextLine(); //Pide al jugador su nombre
        System.out.println("\n" + nombreJugador + ", prepárate para enfrentarte a las fuerzas del mal.");
    }

    // Método de escenario generalizado
    public static void escenario(int numero, String demonio, String descripcion, Runnable desafio) {
        System.out.println("\n===== ESCENARIO " + numero + ": " + descripcion + " =====");
        System.out.println("Energía vital del jugador: " + energiaJugador + "%");
        desafio.run();
        if (energiaJugador > 0) capturarDemonio(demonio);
    }

    // Escenario 1: Desafío de Piedra, Papel o Tijera
    public static void desafioPiedraPapelTijera() {
        System.out.println("La puerta principal de la mansión se abre con un crujido espeluznante, revelando un vestíbulo oscuro y polvoriento.");
        System.out.println("El aire está cargado de un hedor rancio, como si algo hubiera muerto aquí hace mucho tiempo.");
        System.out.println("Las paredes están cubiertas de cuadros antiguos, sus figuras te observan con ojos vacíos y fríos.");
        System.out.println("Sientes que algo, o alguien, se esconde entre las sombras, vigilando cada uno de tus movimientos.\n");
        System.out.println("Aparéce el primer demonio");
        System.out.println("Te enfrentas a Astaroth, el demonio de las sombras. Te desafía a un juego de piedra, papel o tijera...");
        int victorias = 0;

        while (energiaJugador > 0 && victorias < 1) {
            System.out.println("\nElige tu movimiento:");
            System.out.println("1. Piedra");
            System.out.println("2. Papel");
            System.out.println("3. Tijera");
            System.out.print("Tu elección: ");
            int jugador = entrada.nextInt();
            int demonio = new Random().nextInt(3) + 1;

            if ((jugador == 1 && demonio == 3) || (jugador == 2 && demonio == 1) || (jugador == 3 && demonio == 2)) {
                System.out.println("¡Has vencido a Astaroth!");
                victorias++;
            } else if (jugador == demonio) {
                System.out.println("Empate, intenta nuevamente...");
            } else {
                System.out.println("Astaroth te supera y tu energía disminuye.");
                reducirEnergia();
            }
        }
    }

    // Escenario 2: Prueba de Memoria
    public static void pruebaMemoria() {
        System.out.println("Avanzas hacia un largo pasillo iluminado solo por una débil luz parpadeante.");
        System.out.println("El eco de tus pasos parece multiplicarse en las paredes.");
        System.out.println("A cada lado, puertas cerradas y desconchadas parecen murmurar secretos oscuros.");
        System.out.println("De repente, un susurro inhumano surge desde una de las habitaciones… y el silencio se vuelve ensordecedor.\n");
        System.out.println("Con mucho temor decides abrir y entrar en una de las puertas, el segundo demonio aparéce y es más poderozo que el anterior.");
        System.out.println("Belial, el demonio de las ilusiones, desafía tu memoria...");

        List<String[]> palabras = Arrays.asList(
                new String[]{"alma", "maldición", "sombras", "vacío"},
                new String[]{"oscuridad", "pesadilla", "desesperación", "fuga"},
                new String[]{"destino", "ruina", "laberinto", "susurro"},
                new String[]{"espectro", "silencio", "tinieblas", "abismo"},
                new String[]{"eternidad", "miedo", "tormento", "misterio"}
        );

        boolean memoriaAcierto = false;

        while (energiaJugador > 0 && !memoriaAcierto) {
            String[] lista = palabras.get(new Random().nextInt(palabras.size()));
            System.out.println("Memoriza las siguientes palabras:");

            for (String palabra : lista) System.out.print(palabra + " ");
            System.out.println("\n¡Recuerda rápido!, no olvides la ortografía");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            } //Oculata las palabras mostradas

            System.out.println("\nEscribe las palabras en orden mostrado y tal cual miraste:");
            memoriaAcierto = validarPalabras(lista);
        }
    }

    // Validación de palabras en la Prueba de Memoria
    private static boolean validarPalabras(String[] lista) {
        for (String palabra : lista) {
            System.out.print("Palabra: ");
            if (!entrada.next().equalsIgnoreCase(palabra)) {
                System.out.println("Fallo... tu mente se nubla.");
                reducirEnergia();
                return false;
            }
        }
        return true;
    }

    // Escenario 3: Lanzamiento de Dados
    public static void lanzamientoDados() {
        System.out.println("Entras en una sala de estar donde los muebles están cubiertos de telas viejas y desgarradas.");
        System.out.println("El reloj de pared se detuvo hace décadas, pero aún emite un leve tic-tac, como si el tiempo estuviera atrapado en este lugar.");
        System.out.println("La chimenea tiene marcas de algo quemado recientemente, aunque nadie ha habitado aquí en años.");
        System.out.println("Un reflejo fugaz cruza el espejo roto… pero al voltear, solo te encuentras con una oscuridad densa y amenazante.\n");
        System.out.println("Una voz susurra tu nombre, " + nombreJugador + ", estoy por aquí...");
        System.out.println("El tercer demonio está frente a ti...");
        System.out.println("Azazel, el demonio de la discordia, te desafía a un juego de azar.");

        entrada.nextLine();  // Espera a que el jugador presione una tecla

        while (energiaJugador > 0) {
            // Lanzamiento del demonio
            int dadoDemonio = lanzarDado() + lanzarDado();
            System.out.println("Tirada de Azazel: " + dadoDemonio);

            // Solicitar al jugador que presione una tecla para lanzar sus dados
            System.out.print("Presiona ENTER para lanzar tus dados...");
            entrada.nextLine();

            // Lanzamiento del jugador
            int dadoJugador = lanzarDado() + lanzarDado();
            System.out.println("Tu lanzamiento: " + dadoJugador);

            // Comparación de resultados
            if (dadoJugador > dadoDemonio) {
                System.out.println("¡Has vencido a Azazel!");
                break;
            } else if (dadoJugador == dadoDemonio) {
                System.out.println("Empate, vuelve a lanzar...");
            } else {
                System.out.println("Azazel gana esta ronda...");
                reducirEnergia();
            }
        }
    }

    // Escenario 4: Adivinanzas
    public static void acertijos() {
        System.out.println("Subes una escalera empinada que parece que va a colapsar en cualquier momento.");
        System.out.println("Los cuadros colgados en la pared muestran rostros pálidos y distorsionados, figuras de otros tiempos que te miran con expresiones de agonía.");
        System.out.println("Al llegar al final de la escalera, un susurro helado pronuncia tu nombre…");
        System.out.println("y una sombra se desliza por el piso, desapareciendo en una habitación oscura al fondo del pasillo.\n");
        System.out.println("Sigues al espectro y le gritas detente...");
        System.out.println("El ente maligno se detiene y decide enfrentarte.");
        System.out.println("Lilith, la demonio de los secretos, te reta a resolver sus enigmas. Solo podrás responder con una palabra, ten cuidado aventurero.");

        List<String[]> adivinanzas = Arrays.asList(
                new String[]{"Vuelo sin alas, lloro sin ojos, si quieres que te lo diga espera que no me oigas. ¿Qué soy?", "nube"},
                new String[]{"Blanca por dentro, verde por fuera, si quieres que te lo diga espera. ¿Qué es?", "pera"},
                new String[]{"Oro parece, plata no es, el que no lo adivine bien tonto es. ¿Qué es?", "plátano"},
                new String[]{"Adivina, adivinanza, no se ve, y está en la casa; pero a veces sale y no vuelve hasta mañana. ¿Qué es?", "sol"},
                new String[]{"Tengo agujas pero no sé coser, doy la hora sin detenerme. ¿Qué soy?", "reloj"}
        );

        int correctas = 0;

        while (energiaJugador > 0 && correctas < 2) {
            String[] adivinanza = adivinanzas.get(new Random().nextInt(adivinanzas.size()));
            System.out.println(adivinanza[0]);
            System.out.print("Tu respuesta: ");

            if (entrada.next().equalsIgnoreCase(adivinanza[1])) {
                correctas++;
                System.out.println("¡Correcto!");
            } else {
                System.out.println("Respuesta incorrecta...");
                reducirEnergia();
            }
        }
    }

    // Escenario 5: Tres en Raya
    public static void tresEnRaya() {
        System.out.println("Encuentras una biblioteca polvorienta llena de libros viejos y encuadernados en cuero.");
        System.out.println("Al hojear uno, las páginas parecen desmoronarse entre tus dedos, revelando letras escritas con algo que parece sangre seca.");
        System.out.println("Un viento helado sopla inexplicablemente entre las estanterías, haciendo que los libros se agiten.");
        System.out.println("Una risa apenas perceptible se escucha desde lo alto… al mirar hacia arriba, encuentras una sombra inquieta y espelusnante.\n");
        System.out.println("Está ante ti el demonio mas poderozo de la mansión, ¿podrás sobrevivir? ...");

        System.out.println("Lucifer, el príncipe de las tinieblas, te desafía a un juego final de Tres en Raya.");

        char[][] tablero = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; //Crea el tablero de 3 X 3
        boolean turnoJugador = true; // Controla quien inicia el juego
        boolean victoria = false; // Indica si hay un ganador

        //Bucle principal del juego
        while (energiaJugador > 0 && !victoria) { // mientras el jugador disponga de energía vital el juego se vuelve a ejecutar mostrando el tablero
            mostrarTablero(tablero);

            if (turnoJugador) {
                System.out.print("Ingresa tu movimiento (fila y columna separados por un espacio): ");

                // Controla la posicion donde el jugador podrá hacer su movimiento desde las posiciones 1 1, 2 1, 3 1 etc.
                int fila = entrada.nextInt() - 1;
                int columna = entrada.nextInt() - 1;

                // Si la posición esta vacia se marca una X y si la posición ya fue seleccionada se muestra un mensaje de intentalo de nuevo
                if (tablero[fila][columna] == ' ') {
                    tablero[fila][columna] = 'X';
                    turnoJugador = false;
                } else System.out.println("Posición ocupada. Intenta de nuevo.");

                //Turno del demonio
            } else {
                int fila, columna;
                do {
                    // Se selecciona aleatoreamente una posicion vacia en el tablero y se coloca un 0
                    fila = new Random().nextInt(3);
                    columna = new Random().nextInt(3);
                } while (tablero[fila][columna] != ' ');

                tablero[fila][columna] = 'O';
                turnoJugador = true;
                System.out.println("Lucifer realiza su movimiento.");
            }

            victoria = verificarVictoria(tablero);
            if (victoria) mostrarTablero(tablero);
        }

        if (energiaJugador > 0) System.out.println("¡Has vencido a Lucifer y concluido la aventura!");
        else System.out.println("Lucifer te ha vencido... tu energía se ha agotado.");
    }

    // METODOS DEL JUEGO

    //Método para lanzar dados
    public static int lanzarDado() {
        return (int) (Math.random() * 6) + 1;
    }

    //Método para reducir energía vital cuando se pierde
    public static void reducirEnergia() {
        energiaJugador -= PERDIDA_ENERGIA;
        System.out.println("Energía del jugador: " + energiaJugador + "%");
    }

    // Método para capturar un demonio y manejar la recuperación de energía
    public static void capturarDemonio(String demonio) {
        demoniosCapturados.add(demonio); // Agrega el demonio capturado a la lista
        corazones++; // Incrementa el contador de corazones

        // Verifica si el jugador ha recolectado 3 corazones
        if (corazones == CORAZONES_PARA_RECUPERAR) {
            energiaJugador = ENERGIA_MAXIMA; // Restaura la energía al máximo
            corazones = 0; // Reinicia el contador de corazones
            System.out.println("¡Has recolectado 3 corazones! Tu energía se ha restaurado completamente.");
        } else {
            System.out.println("Has ganado un corazón. Corazones recolectados: " + corazones);
        }
    }

    //Método para mostrar las estadísticas finales del juego
    public static void mostrarEstadisticas() {
        System.out.println("\nEstadísticas de la aventura:");
        System.out.println("Nombre del jugador: " + nombreJugador);
        System.out.println("Energía final: " + energiaJugador + "%");
        System.out.println("Corazones obtenidos: " + corazones);
        System.out.println("Demonios capturados: " + demoniosCapturados);
    }

    //Método para mostrar el tablero de 3 en raya
    public static void mostrarTablero(char[][] tablero) {
        System.out.println("Tablero de Tres en Raya:");
        for (char[] fila : tablero) {
            for (char celda : fila) System.out.print("| " + celda + " ");
            System.out.println("|");
        }
    }

    //Verificación de victoria tablero 3 en raya
    public static boolean verificarVictoria(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != ' ') ||
                    (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i] && tablero[0][i] != ' '))
                return true;
        }
        return (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != ' ') ||
                (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != ' ');
    }
}
