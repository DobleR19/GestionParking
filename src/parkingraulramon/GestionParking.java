
package parkingraulramon;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class GestionParking {

    
    public static void main(String[] args) {
        
        Parking parking = new Parking("Cementerio de coches");
        int opcion = 0;
        
        Scanner entradaInt = new Scanner(System.in);
        Scanner entradaStr = new Scanner(System.in);
        

        Vehiculo c1 = new Coche("largo", "4562-FGF", "48562144S");
        Vehiculo c2 = new Coche("corto", "4478-FRF", "55412552R");
        Vehiculo m1 = new Moto("7412-EEE", "45522141B");
        Vehiculo c3 = new Coche("corto", "8874-LLK","27452225B");
        
        Plaza p1 = new Plaza(1, "Primero");
        Plaza p2 = new Plaza(2, "Primero");
        Plaza p3 = new Plaza(3, "Primero");
        Plaza p4 = new Plaza(4, "Primero");
        Plaza p5 = new Plaza(5, "Primero");
        Plaza p6 = new Plaza(6, "Segundo");
        Plaza p7 = new Plaza(7, "Segundo");
        Plaza p8 = new Plaza(8, "Segundo");
        Plaza p9 = new Plaza(9, "Segundo");
        Plaza p10 = new Plaza(10, "Segundo");
        
        p1.setTipo('C');
        p2.setTipo('C');
        p3.setTipo('C');
        p4.setTipo('M');
        p5.setTipo('C');
        p6.setTipo('C');
        p7.setTipo('C');
        p8.setTipo('M');
        p9.setTipo('M');
        p10.setTipo('C');
        
        parking.getListadoPlazas().put("101", p1);
        parking.getListadoPlazas().put("102", p2);
        parking.getListadoPlazas().put("103", p3);
        parking.getListadoPlazas().put("104", p4);
        parking.getListadoPlazas().put("105", p5);
        parking.getListadoPlazas().put("206", p6);
        parking.getListadoPlazas().put("207", p7);
        parking.getListadoPlazas().put("208", p8);
        parking.getListadoPlazas().put("209", p9);
        parking.getListadoPlazas().put("210", p10);
        
        p1.setVehiculo(c2);
        p7.setVehiculo(c1);
        p4.setVehiculo(m1);
        p2.setVehiculo(c3);
        
        do{
            System.out.println("MENU");
            System.out.println("**************");
            System.out.println("\t1. Alquilar plaza.");
            System.out.println("\t2. Abandonar plaza.");
            System.out.println("\t3. Calcular ganancias.");
            System.out.println("\t4. Cerrar programa.");
            System.out.print("Elija una opción: ");
            opcion = entradaInt.nextInt();
            switch(opcion){
                case 1: //Alquilar plaza
                    //Creamos el objeto vehiculo
                    String matricula = null;
                    String modelo = null;
                    String color = null;
                    String nif = null;
                    int telefono = 0;
                    char tipoVehiculo;
                    String tipo = null;
                    Vehiculo v = null;
                    String numPlaza = null;
                    int numInt = 0;//numero de plaza en enteros

                    System.out.println("Datos del vehiculo:");
                    System.out.print("Introduzca matricula: ");
                    matricula = entradaStr.nextLine();
                    System.out.print("Introduzca modelo: ");
                    modelo = entradaStr.nextLine();
                    System.out.print("Introduzca color: ");
                    color = entradaStr.nextLine();
                    System.out.print("Introduzca NIF del propietario: ");
                    nif = entradaStr.nextLine();
                    System.out.print("Introduzca telefono: ");
                    telefono = entradaInt.nextInt();
                    System.out.print("Introduzca tipo de vehiculo (C/M): ");
                    tipoVehiculo = entradaStr.next().charAt(0);

                    //Según el tipo de vehiculo creamos un objeto Coche o Moto
                    if(tipoVehiculo == 'C'){
                        Scanner entradaStr2 = new Scanner(System.in);
                        System.out.print("Longitud coche (corto/largo): ");
                        tipo = entradaStr2.nextLine().toLowerCase();
                        v = new Coche(tipo, matricula, nif);
                    }else if(tipoVehiculo == 'M'){
                        v = new Moto(matricula, nif);
                    }else{
                         System.out.println("No ha introducido el tipo de vehículo de forma correcta.");
                    }
                    //a?adimos el resto de atributos que no estan en el constructor
                    v.setModelo(modelo);
                    v.setColor(color);
                    v.setTelefono(telefono);

                    //alquilamos la plaza
                    numPlaza = parking.alquilar(v);
                    if(numPlaza != null){
                        System.out.println("\n\nN? plaza designada al vehiculo "+v.getMatricula()+" es: "+numPlaza+"\n");
                    }else{
                        System.out.println("No hay plazas disponibles.");
                    }
                    break;
                case 2: //Abandonar plaza
                    int opcion2 = 0;
                    Scanner entradaInt2 = new Scanner(System.in);
                    System.out.print("Introduzca numero de plaza: ");
                    numInt = entradaInt2.nextInt();
                    opcion2 = parking.darBaja(numInt);
                    switch(opcion2){
                        case 0:
                            System.out.println("Se ha dado de baja el alquiler.");
                            break;
                        case 1:
                            System.out.println("No existe ninguna plaza con ese númeroa.");
                            break;
                        case 2:
                            System.out.println("La plaza no esta ocupada.");
                    }
                    break;
                case 3: //calcular ganancias
                    System.out.println("\nSe ha obtenido unas ganancias de: "+parking.ganancias()+" €\n");
                    break;
                case 4://Cerrar programa y listar plaza libres
                    List<Plaza> listaCoches = parking.listarPlazas("libres", 'C');
                    List<Plaza> listaMotos = parking.listarPlazas("libres", 'M');
                    LocalDate hoy = LocalDate.now();
                    String listaC = "";
                    String listaM = "";
                    System.out.println("\nListado de plazas libres de Coche a fecha "+hoy.toString());
                    for(Plaza p: listaCoches){
                        listaC += "\t"+p.toString()+"\n";
                    }
                    System.out.print(listaC);
                    System.out.println("\nListado de plazas libres de Moto a fecha "+hoy.toString());
                    for(Plaza p: listaMotos){
                        listaM += "\t"+p.toString()+"\n";
                    }
                    System.out.print(listaM);
                    break;
                default:
                    System.out.println("No ha elegido una opcion correcta.");
            }
        }while(opcion != 4);
        
    }
    
}
