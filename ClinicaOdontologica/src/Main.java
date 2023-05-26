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
        ConfiguracionJDBC configuracion = new ConfiguracionJDBC();
        configuracion.conectarConBasesDeDatos();
        OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2(configuracion);
        OdontologoDAOMemoria odontologoDAOMemoria = new OdontologoDAOMemoria(new ArrayList<>());

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoDAO(odontologoDAOH2);

        List<Odontologo> odontologosH2 = new ArrayList<>();
        List<Odontologo> odontologosMemoria = new ArrayList<>();

        Odontologo o1 = new Odontologo(7, "Jorge", "Pruebas");
        Odontologo o2 = new Odontologo(8, "Jorgito", "Repruebas");
        Odontologo o3 = new Odontologo(9, "Holas", "MasPruebas");

        odontologoService.guardarOdontologo(o1);
        odontologoService.guardarOdontologo(o2);
        odontologoService.guardarOdontologo(o3);

        odontologosH2 = odontologoService.listarOdontologos();

        System.out.println("Odontólogos almacenados en H2:");
        for (Odontologo odontologo : odontologosH2) {
            System.out.println(odontologo.toString());
        }

        odontologoService.setOdontologoDAO(odontologoDAOMemoria);
        Odontologo o4 = new Odontologo(123, "Marcelo", "Fullana");
        Odontologo o5 = new Odontologo(456, "Erick", "RAmirez");
        Odontologo o6 = new Odontologo(789, "Luciana", "Murga");

        odontologoService.guardarOdontologo(o4);
        odontologoService.guardarOdontologo(o5);
        odontologoService.guardarOdontologo(o6);
        odontologosMemoria = odontologoService.listarOdontologos();

        System.out.println("Odontólogos almacenados en Memoria:");
        for (Odontologo odontologo : odontologosMemoria) {
            System.out.println(odontologo.toString());
        }
    }
}