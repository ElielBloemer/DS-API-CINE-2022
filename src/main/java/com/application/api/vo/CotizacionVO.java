package com.application.api.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CotizacionVO{

    public String fecha;
    public String compra;
    public String venta;

    public CotizacionVO(String fecha, String compra, String venta) {
        this.fecha = fecha;
        this.compra = compra;
        this.venta = venta;
    }

}
