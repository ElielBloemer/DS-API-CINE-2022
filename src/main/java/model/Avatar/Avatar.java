package model.Avatar;

import javax.persistence.*;

@MappedSuperclass
public abstract class Avatar implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avatar")
    protected int idAvatar;
    @Column(name = "numero_camiseta")
    protected int numeroCamiseta;
    @Column(name = "apodo_camiseta")
    protected String apodoCamiseta;
    @Column(name = "color_pelo")
    protected String ColorPelo;
    @Column(name = "color_ojos")
    protected String colorOjos;
    @Column(name = "camiseta_seleccion")
    protected String camisetaSeleccion;

    public Object clone(){
        Object clone= null;
        try{
            clone=super.clone();

        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }

        return clone;
    }


    public Avatar(int numeroCamiseta, String apodoCamiseta, String colorPelo, String colorOjos, String camisetaSeleccion) {
        this.numeroCamiseta = numeroCamiseta;
        this.apodoCamiseta = apodoCamiseta;
        ColorPelo = colorPelo;
        this.colorOjos = colorOjos;
        this.camisetaSeleccion = camisetaSeleccion;
    }

    public String getCamisetaSeleccion() {
        return camisetaSeleccion;
    }

    public void setCamisetaSeleccion(String camisetaSeleccion) {
        this.camisetaSeleccion = camisetaSeleccion;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public String getApodoCamiseta() {
        return apodoCamiseta;
    }

    public void setApodoCamiseta(String apodoCamiseta) {
        this.apodoCamiseta = apodoCamiseta;
    }

    public int getId() {
        return idAvatar;
    }

    public void setId(int id) {
        this.idAvatar = id;
    }

    public String getColorPelo() {
        return ColorPelo;
    }

    public void setColorPelo(String colorPelo) {
        ColorPelo = colorPelo;
    }

    public String getColorOjos() {
        return colorOjos;
    }

    public void setColorOjos(String colorOjos) {
        this.colorOjos = colorOjos;
    }

    public abstract void solicitarDatos();

    public abstract void crearAvatarDefault();


}