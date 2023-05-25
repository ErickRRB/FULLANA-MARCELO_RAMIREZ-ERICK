package dao.impl;

import dao.OdontologoDAO;
import entities.Odontologo;

import java.util.List;

public class OdontologoDAOMemoria implements OdontologoDAO<Odontologo> {
    private List<Odontologo> odontologoRepositorio;

    public OdontologoDAOMemoria(List<Odontologo> odontologoRepositorio) {
        this.odontologoRepositorio = odontologoRepositorio;
    }

    public OdontologoDAOMemoria() {

    }

    @Override
    public void guardar(Odontologo odontologo){
        odontologoRepositorio.add(odontologo);
    }

    @Override
    public List<Odontologo> listar() {
        return odontologoRepositorio;
    }

}
