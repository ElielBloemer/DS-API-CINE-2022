package com.application.api.vo;

import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import lombok.Getter;
import lombok.Setter;

public class TarjetaDeDebitoVO {

    //public Integer id;
    public String numeroTarjetaDeDebito;
    public String titular;
    public float dineroDisponibleMonedaLocal;
   // public String monedaNacional;
    public float dineroDisponibleMonedaExtranjera;
    public String monedaExtranjera;

    public TarjetaDeDebitoVO(String numeroTarjetaDeDebito, String titular, float dineroDisponibleMonedaLocal, String monedaNacional, float dineroDisponibleMonedaExtranjera, String monedaExtranjera) {
        this.numeroTarjetaDeDebito = numeroTarjetaDeDebito;
        this.titular = titular;
        this.dineroDisponibleMonedaLocal = dineroDisponibleMonedaLocal;
       // this.monedaNacional = monedaNacional;
        this.dineroDisponibleMonedaExtranjera = dineroDisponibleMonedaExtranjera;
        this.monedaExtranjera = monedaExtranjera;
    }

    public TarjetaDeDebitoVO(TarjetaDeDebito tarjetaDeDebito) {
        //this.id= tarjetaDeDebito.getId();
        this.numeroTarjetaDeDebito = tarjetaDeDebito.getNumeroTarjetaDeDebito();
        this.titular = tarjetaDeDebito.getTitular();
        this.dineroDisponibleMonedaLocal = tarjetaDeDebito.getDineroDisponibleMonedaLocal();
        //this.monedaNacional = tarjetaDeDebito.getMonedaNacional();
        this.dineroDisponibleMonedaExtranjera = tarjetaDeDebito.getDineroDisponibleMonedaExtranjera();
        this.monedaExtranjera = tarjetaDeDebito.getMonedaExtranjera();
    }


}
