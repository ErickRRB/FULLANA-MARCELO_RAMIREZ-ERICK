package services;

import dao.OdontologoDAO;
import entities.Odontologo;

import java.util.List;

public class OdontologoService {
    private OdontologoDAO odontologoDAO;

    /*Inyeccion a traves del constructor*/
    public OdontologoService(OdontologoDAO odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public void guardarOdontologo(Odontologo odontologo) {
        odontologoDAO.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoDAO.listar();
    }


}
