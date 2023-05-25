package dao.impl;

import dao.OdontologoDAO;
import entities.Odontologo;
//import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import dao.ConfiguracionJDBC;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import static dao.impl.H2Connection.getConnection;
//import java.util.logging.Logger;

public class OdontologoDAOH2 implements OdontologoDAO<Odontologo> {

    private ConfiguracionJDBC configuracion;

    public OdontologoDAOH2() {
    }

    private Logger logger = Logger.getLogger(String.valueOf(OdontologoDAOH2.class));

    public OdontologoDAOH2(ConfiguracionJDBC configuracion) {
        this.configuracion = configuracion;
    }


    @Override
    public void guardar(Odontologo odontologo) {
        Connection connection = configuracion.conectarConBasesDeDatos();
        Statement statement = null;
        String query = String.format("INSERT INTO odontologos VALUES('%s','%s','%s')", odontologo.getMatricula(),
                odontologo.getNombre(), odontologo.getApellido());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        } catch (SQLException e) {
            logger.warning("No se ha guardado el odontologo");
            throw new RuntimeException(e);
        }
        logger.info("Se ha guardado el odontologo");

        return;
    }


    @Override
    public List<Odontologo> listar() {
        Connection connection = configuracion.conectarConBasesDeDatos();
        Statement statement = null;
        String query = String.format("SELECT * FROM odontologos");
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer matricula = resultSet.getInt("matricula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                odontologos.add(new Odontologo(matricula, nombre, apellido));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return odontologos;
    }
}
