import dao.ConfiguracionJDBC;
import dao.impl.H2Connection;
import dao.impl.OdontologoDAOH2;
import dao.impl.OdontologoDAOMemoria;
import entities.Odontologo;
import services.OdontologoService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //H2Connection.crearTabla();


        /* Establezco coneccion con el archivo ConfiguracionJDBC*/
        ConfiguracionJDBC configuracion = new ConfiguracionJDBC();
        configuracion.conectarConBasesDeDatos();
        OdontologoDAOMemoria odontologoDaoMemoria = new OdontologoDAOMemoria();
        OdontologoDAOH2 odontologoDaoH2 = new OdontologoDAOH2();



        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoDAO(odontologoDaoH2);
        List<Odontologo> odontologosH2 = new ArrayList<>();
        List<Odontologo> odontologosMemoria = new ArrayList<>();

        Odontologo o1 = new Odontologo(123, "Elsa", "Quito");
        Odontologo o2 = new Odontologo(456, "Esteban", "Quito");
        Odontologo o3 = new Odontologo(789, "Solomeo", "Paredes");

        odontologoService.guardarOdontologo(o1);
        odontologoService.guardarOdontologo(o2);
        odontologoService.guardarOdontologo(o3);

        odontologosH2 = odontologoService.listarOdontologos();

        System.out.println();
        System.out.println("Odontologos almacenados en H2:\n");
        for (Odontologo odontologo : odontologosH2) {
            System.out.println(odontologo.toString());
        }
        System.out.println();

        odontologoService.setOdontologoDAO(odontologoDaoMemoria);

        Odontologo o4 = new Odontologo(123, "Estiven", "Sigal");
        //o4.setId(1L);
        Odontologo o5 = new Odontologo(456, "Fulano", "Fulanito");
        //o5.setId(2L);
        Odontologo o6 = new Odontologo(789, "Mengano", "Menganito");
        //o6.setId(3L);

        odontologoService.guardarOdontologo(o4);
        odontologoService.guardarOdontologo(o5);
        odontologoService.guardarOdontologo(o6);

        odontologosMemoria = odontologoService.listarOdontologos();

        System.out.println();
        System.out.println("Odontologos almacenados en Memoria:\n");
        for (Odontologo odontologo : odontologosMemoria) {
            System.out.println(odontologo.toString());
        }
    }
}
