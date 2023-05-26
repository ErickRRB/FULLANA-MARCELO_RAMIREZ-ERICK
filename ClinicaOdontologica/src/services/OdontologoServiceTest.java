package services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dao.impl.OdontologoDAOMemoria;
import entities.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OdontologoServiceTest {
    private OdontologoService odontologoService;
    private OdontologoDAOMemoria odontologoDAO;


    private Odontologo TestO1;
    private Odontologo TestO2;
    private Odontologo TestO3;

    @BeforeEach
    public void setUp() {
        odontologoDAO = new OdontologoDAOMemoria(new ArrayList<>());
        odontologoService = new OdontologoService();
        odontologoService.setOdontologoDAO(odontologoDAO);

        TestO1 = new Odontologo(99, "Jorgex", "Pruebasx");
        TestO2 = new Odontologo(100, "Jorgitox", "Repruebasx");
        TestO3 = new Odontologo(101, "Holasx", "MasPruebasx");

        odontologoDAO.guardar(TestO1);
        odontologoDAO.guardar(TestO2);
        odontologoDAO.guardar(TestO3);
    }

    @Test
    public void testGuardarOdontologo() {
        assertEquals(3, odontologoDAO.listar().size());
    }

    @Test
    public void testListarOdontologos() {
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(TestO1);
        odontologos.add(TestO2);
        odontologos.add(TestO3);

        List<Odontologo> resultado = odontologoService.listarOdontologos();

        assertEquals(odontologos, resultado);
    }
}
