package model.MetodoDePago;

import java.io.IOException;

public interface MetodoDePago {

    public void pagar(float montoAPagar) throws IOException, InsuficienteMontoExcepction, InsuficienteMontoExcepction;
}
