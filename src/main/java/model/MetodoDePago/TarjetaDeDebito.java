package model.MetodoDePago;

import javax.persistence.*;

@Entity
@Table(name = "tarjeta_de_debito")
public class TarjetaDeDebito implements MetodoDePago{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tarjeta_de_debito")
    private int idTarjetaDeDebito;
    @Column(name="numero_de_tarjeta")
    private Integer numeroTarjeta;

    @Column(name = "titular_tarjeta")
    private String titular;

    @Column(name = "dinero_disponible")
    private double dineroDisponible;

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(float dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    public TarjetaDeDebito(Integer numeroTarjeta, String titular, float dineroDisponible) {
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.dineroDisponible = dineroDisponible;
    }

    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public void pagar(float montoAPagar) throws InsuficienteMontoExcepction {

        if(tengoDineroSuficiente(montoAPagar)){
            this.dineroDisponible = this.dineroDisponible - montoAPagar;
        }else{
            throw new InsuficienteMontoExcepction();
        }
    }

    public boolean tengoDineroSuficiente(float montoAPagar){
        return this.getDineroDisponible()>=montoAPagar;   // 20 >=10
    }
}
