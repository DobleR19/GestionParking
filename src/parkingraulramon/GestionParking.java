
package parkingraulramon;


public class GestionParking {

    
    public static void main(String[] args) {
        Plaza p1 = new Plaza(18,"Primero");
        Plaza p2= new Plaza(2,"Segundo");
        Vehiculo v1=new Coche("largo","6538bky","53881498l");
        p1.setVehiculo(v1);
        //System.out.println(p1.precio());
        System.out.println(p2.toString());
        
    }
    
}
