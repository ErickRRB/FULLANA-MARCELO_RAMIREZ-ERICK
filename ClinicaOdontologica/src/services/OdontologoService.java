package services;

import dao.OdontologoDAO;
import entities.Odontologo;

import java.util.List;

public class OdontologoService {
    private OdontologoDAO odontologoDAO;

    public OdontologoService() {
        //this.odontologoDAO = odontologoDAO;
    }

    public void setOdontologoDAO(OdontologoDAO odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public void guardarOdontologo(Odontologo odontologo) {
        odontologoDAO.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoDAO.listar();
    }


}
