
package parkingraulramon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Parking {
    private String nombre;
    private String direccion;
    private Map<String,Plaza> listadoPlazas=new HashMap<>();

    public Parking(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Map<String,Plaza> getListadoPlazas() {
        return listadoPlazas;
    }

    public void setListadoPlazas(Map<String,Plaza> listadoPlazas) {
        this.listadoPlazas = listadoPlazas;
    }
    
    public String alquilar (Vehiculo v){
        String numPlaza=null;
        Iterator<String> it= listadoPlazas.keySet().iterator();
        while(it.hasNext()){
            String snn = it.next();
            Plaza plaza = listadoPlazas.get(snn);
            if (plaza.getVehiculo()==null){
                listadoPlazas.put(snn, plaza);
                numPlaza
            }
        }
        
        return numPlaza;
    }
}
