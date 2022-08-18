import model.Avatar.Avatar;
import model.Avatar.Humano;
import model.Usuario.Cuenta;
import model.Usuario.Usuario;
import model.apiCotizacion.Cotizacion;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcion;
        Scanner opcionElegida = new Scanner(System.in);
        Usuario usuario;
        Cuenta cuenta;

        Humano humano = new Humano(0,"Default","Negro","Negro","Default");
        humano.crearAvatarDefault();

        do {
            usuario = new Usuario();
            cuenta = new Cuenta();

            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");

            System.out.print("Opcion: ");
            opcion = opcionElegida.nextInt();

            if(opcion == 1) {
                cuenta.iniciarSesion(usuario);
            } else
                cuenta.registrarse(usuario);

        } while(opcion != 1 || !cuenta.sesionIniciadaCorrectamente());

        System.out.println("Bienvenido al sistema de reserva de entradas para el cine. Por favor, elija una de las siguientes opciones: ");

        do {
            System.out.println("0. Salir");
            System.out.println("1. Comprar entradas");
            System.out.println("2. Buscar película o partido de fútbol recomendado");
            System.out.println("3. Ver descuentos disponibles");
            System.out.println("4. Generar un avatar personalizado");
            System.out.println("5. Comprar un combo");
            System.out.println("6. Consultar cotizacion del dolar blue");

            System.out.print("Opcion: ");
            opcion = opcionElegida.nextInt();

            switch (opcion) {
                case 1:
                    usuario.comprarEntradas();
                    break;
                case 2:
                    usuario.buscarEvento();
                    break;
                case 3:
                    usuario.descuentosDisponibles();
                    break;
                case 4:
                    usuario.generarAvatar();
                    break;
                case 5:
                    usuario.comprarProducto();
                    break;
                case 6:
                    Cotizacion cotizacion = new Cotizacion();
                    String resultado = cotizacion.obtenerCotizacion().toString();
                    System.out.println(resultado);
                default:
                    break;
            }
        } while (opcion != 0);
    }
}
