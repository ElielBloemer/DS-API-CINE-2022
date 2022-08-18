package model.Usuario;

import model.Avatar.Humano;
import model.Combo.BaldePochoclo;
import model.Combo.Bebida;
import model.Combo.Combo;
import model.Combo.Producto;
import model.Descuento.DescuentoMayor;
import model.Descuento.DescuentoMedio;
import model.Descuento.DescuentoMenor;
import model.Evento.*;
import model.MetodoDePago.TarjetaDeDebito;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String dni;
    @Column
    private int edad;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuentaAsociada;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_avatar")
    private Humano avatar;
    //@OneToOne(cascade = {CascadeType.ALL})
    //@JoinColumn(name = "id_metodoDePago")
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_tarjeta_de_debito")
    private TarjetaDeDebito tarjetDeDebito;

    @OneToMany(mappedBy = "usuario")
    private List <Producto> productos;
   // @OneToOne(cascade = {CascadeType.ALL})
    //@JoinColumn(name = "id_reserva")
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public Humano getAvatar() {
        return avatar;
    }

    public void setAvatar(Humano avatar) {
        this.avatar = avatar;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Getters y Setters
    public int getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(int id_usuario) {
        this.idUsuario = id_usuario;
    }

    public Cuenta getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(Cuenta cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setCuentaPersonal(Cuenta nuevaCuenta) {
        this.cuentaAsociada = nuevaCuenta;
    }

    // Métodos principales
    public void solicitarDatos() {
        String cadena;
        Scanner stringIngresado = new Scanner(System.in);
        int entero;
        Scanner enteroIngresado = new Scanner(System.in);

        System.out.print("Nombre: ");
        cadena = stringIngresado.nextLine();
        this.setNombre(cadena);

        System.out.print("Apellido: ");
        cadena = stringIngresado.nextLine();
        this.setApellido(cadena);

        System.out.print("DNI: ");
        cadena = stringIngresado.nextLine();
        this.setDni(cadena);

        System.out.print("Edad: ");
        entero = enteroIngresado.nextInt();
        this.setEdad(entero);

        System.out.print("Nombre de usuario: ");
        cadena = stringIngresado.nextLine();
        this.setNombreUsuario(cadena);

        //this.reservas = null;
        // this.avatar = null;
        //this.metodoDePago = null;
    }

    public void mostrarAvatar() { }

    public void buscarEvento() { }

    public void descuentosDisponibles() {
        //Descuento descuento = new Descuento();
        //descuento.setEstrategia(new DescuentoMenor());

        Entrada entrada = new Entrada();
        String eleccion;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el tipo de evento al que quiere asistir (Pelicula | Partido): ");
        eleccion = scanner.nextLine();

        if(eleccion == "Pelicula") {
            //Pelicula pelicula = new Pelicula(); // no me deja crear un constructor pelado
            System.out.print("Nombre de la pelicula: ");
            eleccion = scanner.nextLine();
            // pelicula = pelicula.buscarEnBBDD(); evento que busca esa pelicula en la base de datos
            //entrada.setEvento(pelicula);
        } else if(eleccion == "Partido") {
            //Partido partido = new Partido(); // no me deja crear un constructor pelado

            System.out.print("Equipos que disputan el partido (ejemplo: Argentina - Alemania): ");
            eleccion = scanner.nextLine();
            // partido = partido.buscarEnBBDD(); evento que busca ese partido en la base de datos
            //entrada.setEvento(partido);
        } else {
            System.out.println("El evento ingresado no existe");
            return;
        }

        /*descuento.determinarDescuento(entrada, this);
        descuento.setEstrategia(new DescuentoMedio());
        descuento.determinarDescuento(entrada, this);
        descuento.setEstrategia(new DescuentoMayor());
        descuento.determinarDescuento(entrada, this);*/
    }

    public void generarAvatar() { }

    public void comprarProducto() {
        int opcion;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("0. Cancelar");
            System.out.println("1. Comprar un balde de pochoclos");
            System.out.println("2. Comprar una bebida");
            System.out.println("3. Comprar un balde de pochoclos y una bebida");
            opcion = scanner.nextInt();

            switch(opcion) {
                case 1:
                    Producto baldeDePochoclos = new BaldePochoclo();
                    break;
                case 2:
                    Producto bebida = new Bebida();
                    break;
                case 3:
                    Producto combo = new Combo();
            }

        } while(opcion != 0);
    }

    public boolean esMayorDeEdad(){
        return this.getEdad() >= 18;
    }

    public void comprarEntradas() {
        int opcion;
        Entrada entrada = new Entrada();
        Scanner scanner = new Scanner(System.in);
        Evento eventoElegido = null;

        System.out.println("Elija el evento: ");
        System.out.print("0. Entrada para pelicula");
        System.out.print("1. Entrada para partido de futbol");
        opcion = scanner.nextInt();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if(opcion == 1) {

            System.out.println("Elija una opcion: ");
            System.out.println("1. Argentina - Brasil ");
            System.out.println("2. Argentina - Uruguay ");
            System.out.println("3. Brasil - Alemania ");
            System.out.println("4. Alemania - Francia ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    eventoElegido = (Partido) entityManager.createQuery("FROM partido WHERE id_partido = '1'");
                    break;
                case 2:
                    eventoElegido = (Partido) entityManager.createQuery("FROM partido WHERE id_partido = '2'");
                    break;
                case 3:
                    eventoElegido = (Partido) entityManager.createQuery("FROM partido WHERE id_partido = '3'");
                    break;
                case 4:
                    eventoElegido = (Partido) entityManager.createQuery("FROM partido WHERE id_partido = '4'");
                    break;
                default:
                    break;
            }
        } else if(opcion == 0) {
            System.out.println("1. Spiderman");
            System.out.println("2. Spiderman 2");
            System.out.println("3. Spiderman 3");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    eventoElegido = (Pelicula) entityManager.createQuery("FROM pelicula WHERE id_pelicula = '1'");

                    break;
                case 2:
                    eventoElegido = (Pelicula) entityManager.createQuery("FROM pelicula WHERE id_pelicula = '2'");
                    break;
                case 3:
                    eventoElegido = (Pelicula) entityManager.createQuery("FROM pelicula WHERE id_pelicula = '3'");
                    break;
                default:
                    break;
            }
        }

        entrada.setEvento(eventoElegido);
        entrada.asignarValores(eventoElegido);

        int salaEvento = eventoElegido.getSalaAsignada().getidSala();

        Sala sala = (Sala) entityManager.createQuery("FROM sala u WHERE u.id_sala LIKE :salaAsignada")
                        .setParameter("salaAsignada",salaEvento)
                                .getSingleResult();

        if(sala.hayEspaciosDisponible()) {
            entityManager.persist(entrada);
            sala.restarEspacioDisponible();
        } else {
            System.out.println("No hay lugar"); // esto se borra
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}