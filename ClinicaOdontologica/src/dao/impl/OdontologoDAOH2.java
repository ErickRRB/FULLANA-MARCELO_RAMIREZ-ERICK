package dao.impl;

import dao.OdontologoDAO;
import entities.Odontologo;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.H2Connection.getConnection;
//import java.util.logging.Logger;

public class OdontologoDAOH2 implements OdontologoDAO<Odontologo> {

    public static final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:./db/odontologo;INIT=RUNSCRIPT FROM 'sql/script.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private final static String SQL_INSERT = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private final static String SQL_SELECT = "SELECT * FROM ODONTOLOGOS";


    @Override
    public void guardar(Odontologo odontologo) {
        File log4jfile = new File("log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.execute();
            preparedStatement.close();
            LOGGER.info("Nuevo registro creado en H2: " + odontologo.getNombre() + " " + odontologo.getApellido());
        } catch (Exception e) {
            LOGGER.error("El error es: ", e);
        }
        return;
    }


    @Override
    public List<Odontologo> listar() {
        Connection connection = null;
        List<Odontologo> domicilios = new ArrayList<>();

        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Odontologo odontologo = new Odontologo(rs.getInt(2), rs.getString(3), rs.getString(4));
                domicilios.add(odontologo);
            }

            LOGGER.info("Listado de todos los odontologos: " + domicilios);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return domicilios;
    }
}
