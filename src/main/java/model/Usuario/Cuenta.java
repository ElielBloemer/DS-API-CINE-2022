package model.Usuario;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Scanner;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuenta;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioAsociado;
    @Column
    private String email;
    @Column(name = "password")
    private String contrasenia;
    @Column(name = "fecha_de_creacion")
    private LocalDate fechaDeCreacion;
    @Transient
    private Boolean sesionIniciada = false;


    public int getId_cuenta() {
        return idCuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.idCuenta = id_cuenta;
    }

    public Boolean sesionIniciadaCorrectamente() {
        return sesionIniciada;
    }


    private void solicitarEmailYContrasenia() {
        Scanner cadena = new Scanner(System.in);

        System.out.print("Ingrese su e-mail: ");
        email = cadena.nextLine();
        System.out.print("Ingrese su contrasenia: ");
        contrasenia = cadena.nextLine();
        fechaDeCreacion = LocalDate.now();
    }
    public void iniciarSesion(Usuario unUsuario) {
        this.solicitarEmailYContrasenia();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Cuenta cuentaBuscada = (Cuenta) entityManager.createQuery(
                            "FROM Cuenta u WHERE u.email LIKE :variableEmail AND u.contrasenia LIKE :variablePassword ")
                    .setParameter("variableEmail",email)
                    .setParameter("variablePassword",contrasenia).getSingleResult();

            System.out.println("Usuario <<" + cuentaBuscada.usuarioAsociado.getNombreUsuario() + ">> encontrado. Sesion iniciada");
            unUsuario = cuentaBuscada.usuarioAsociado;
            sesionIniciada = true;
        } catch (Exception e) {
            System.out.println("No existe el usuario");
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    public void registrarse(Usuario nuevoUsuario) {
        this.solicitarEmailYContrasenia();

        nuevoUsuario.solicitarDatos();
        nuevoUsuario.setCuentaPersonal(this);
        this.usuarioAsociado = nuevoUsuario;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(nuevoUsuario);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}