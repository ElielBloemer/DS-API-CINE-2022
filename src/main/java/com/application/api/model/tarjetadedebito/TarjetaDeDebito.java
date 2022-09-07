package com.application.api.model.tarjetadedebito;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TarjetaDeDebito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroTarjetaDeDebito;
    private String titular;
    private float dineroDisponibleMonedaLocal;
    private String monedaNacional;
    private float dineroDisponibleMonedaExtranjera;
    private String monedaExtranjera;

    public TarjetaDeDebito(String numeroTarjetaDeDebito, String titular, float dineroDisponibleMonedaLocal, String monedaNacional, float dineroDisponibleMonedaExtranjera, String monedaExtranjera) {
        this.numeroTarjetaDeDebito = numeroTarjetaDeDebito;
        this.titular = titular;
        this.dineroDisponibleMonedaLocal = dineroDisponibleMonedaLocal;
        this.monedaNacional = monedaNacional;
        this.dineroDisponibleMonedaExtranjera = dineroDisponibleMonedaExtranjera;
        this.monedaExtranjera = monedaExtranjera;
    }

    public TarjetaDeDebito() {
    }
}
