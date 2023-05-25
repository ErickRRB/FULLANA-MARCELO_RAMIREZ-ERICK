package dao;

import entities.Odontologo;

import java.util.List;

public interface OdontologoDAO {
    void guardar (Odontologo odontolo);
    List<Odontologo> listar();
}
