package model.MetodoDePago;

public class InsuficienteMontoExcepction extends Exception{

    public InsuficienteMontoExcepction (){
        super("No hay saldo suficiente");
    }
}
