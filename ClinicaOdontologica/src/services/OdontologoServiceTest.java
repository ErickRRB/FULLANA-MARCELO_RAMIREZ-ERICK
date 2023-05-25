package services;

import static org.junit.jupiter.api.Assertions.*;

import dao.OdontologoDAO;
import dao.impl.OdontologoDAOMemoria;
import entities.Odontologo;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

class OdontologoServiceTest {
    private OdontologoService odontologoService;
    private OdontologoDAO odontologoDAO;

    @BeforeEach
    public void setUp() {
        odontologoDAO = new OdontologoDAOMemoria(); // Utiliza una implementación de OdontologoDAO para pruebas
        odontologoService = new OdontologoService();
    }
    @Test
    void guardarOdontologo_DebeGuardarOdontologo() {
        // Arrange
        Odontologo odontologo = new Odontologo(123, "Juan", "Perez");

        // Act
        odontologoService.guardarOdontologo(odontologo);

        // Assert
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        assertTrue(odontologos.contains(odontologo));
    }
}