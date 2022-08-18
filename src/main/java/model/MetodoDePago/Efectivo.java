package model.MetodoDePago;

import model.apiCotizacion.Cotizacion;

import java.io.IOException;

public class Efectivo implements MetodoDePago{

    private String monedaElegida;

    public Efectivo(String moneda) {
        this.monedaElegida = moneda;
    }

    public String getMonedaElegida() {
        return monedaElegida;
    }

    public void setMoneda(String moneda) {
        this.monedaElegida = moneda;
    }

    public void buscarCotizacion() throws IOException {
        Cotizacion cotizacion = new Cotizacion();
        System.out.println(cotizacion.obtenerCotizacion().toString());
    }

    @Override
    public void pagar(float montoAPagar) throws IOException {
        if(monedaElegida.equals("dolar")){
            this.buscarCotizacion();
        }
    }

}
