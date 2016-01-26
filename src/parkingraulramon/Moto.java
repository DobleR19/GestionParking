
package parkingraulramon;


public class Moto extends Vehiculo {
    private int numRuedas;

    public Moto(String matricula, String nif) {
        super(matricula, nif);
        this.numRuedas = numRuedas;
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }
    
    
}
