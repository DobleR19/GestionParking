
package parkingraulramon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
        boolean f = false;
        String numPlaza=null;
        Iterator<String> it= listadoPlazas.keySet().iterator();
        while(it.hasNext() && f==false){
            String snn = it.next();
            Plaza plaza = this.getListadoPlazas().get(snn);
            if (plaza.getVehiculo()==null){
                if(v instanceof Coche && plaza.getTipo()=='C'){
                    plaza.setVehiculo(v);
                    f=true;
                    numPlaza= Integer.toString(plaza.getNumPlaza());
                }
                if (v instanceof Moto && plaza.getTipo()=='M'){
                    plaza.setVehiculo(v);;
                    f=true;
                    numPlaza= Integer.toString(plaza.getNumPlaza());
                }   
            }
        }
        return numPlaza;
    }
    
    public int darBaja (int numPlaza){
        int baja=1;
        Iterator<String> it= listadoPlazas.keySet().iterator();
        while(it.hasNext()){
            String snn=it.next();
            Plaza plaza=listadoPlazas.get(snn);
            if(numPlaza==plaza.getNumPlaza()){
                if(plaza.getVehiculo()!=null){
                    plaza.setVehiculo(null);
                    baja=0;
                }else {
                    baja=2;
                }   
            }
        }
        return baja;    
    }
    
    public List<Plaza> listarPlazas(String estado,char tipoVehiculo){
        List<Plaza> listado = new ArrayList<>();
        List<Plaza> ocupadasCoches = new ArrayList<>();
        List<Plaza> libresCoches = new ArrayList<>();
        List<Plaza> ocupadasMotos = new ArrayList<>();
        List<Plaza> libresMotos = new ArrayList<>();
        Iterator<String>it = listadoPlazas.keySet().iterator();
        while(it.hasNext()){
            String snn = it.next();
            Plaza plaza = listadoPlazas.get(snn);
            if(plaza.getVehiculo() != null && plaza.getTipo() == 'C'){
                ocupadasCoches.add(plaza);
            }
            if(plaza.getVehiculo() == null && plaza.getTipo() == 'C'){
                libresCoches.add(plaza);
            }
            if(plaza.getVehiculo()!=null && plaza.getTipo()=='M'){
                ocupadasMotos.add(plaza);
            }
            if(plaza.getVehiculo()==null && plaza.getTipo()=='M')
                libresMotos.add(plaza);
        }
        if(estado.equals("ocupadas") && tipoVehiculo=='C'){
            listado=ocupadasCoches;
        }
        if(estado.equals("libres") && tipoVehiculo == 'C'){
            listado = libresCoches;
        }
        if(estado.equals("ocupadas") && tipoVehiculo == 'M'){
            listado = ocupadasMotos;
        }
        if(estado.equals("libres") && tipoVehiculo == 'M'){
            listado = libresMotos;
        }
        return listado;
    }
    
    public int ganancias() {
        int beneficios=0;
        List<Plaza> listadoCoches = new ArrayList<>();
        listadoCoches = listarPlazas("ocupadas",'C');
        List<Plaza> listadoMotos = new ArrayList<>();
        listadoMotos = listarPlazas("ocupadas",'M');
        for(Plaza plaza: listadoCoches){
            beneficios += plaza.precio();
        }
        for(Plaza plaza: listadoMotos){
            beneficios += plaza.precio();
        }
        return beneficios;   
    }
}
