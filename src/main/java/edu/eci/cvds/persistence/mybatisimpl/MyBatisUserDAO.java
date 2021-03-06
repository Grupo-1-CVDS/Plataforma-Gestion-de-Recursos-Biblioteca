package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Booking;
import edu.eci.cvds.entities.Report;
import edu.eci.cvds.entities.Resource;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.UserDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.UserMapper;
import edu.eci.cvds.entities.User;

import java.util.List;

public class MyBatisUserDAO implements UserDAO {
    @Inject
    private UserMapper userMapper;
    @Override
    @lombok.Generated
    public void create() throws PersistenceException {
        try {
            userMapper.createUser();
        }catch (Exception e){
            throw new PersistenceException("No se Pudo crear Usuario por " + e.getMessage());
        }
    }

    @Override
    public User getUserIdByEmail(String email) throws PersistenceException {
        try {
            return userMapper.getUserIdByEmail(email);
        }catch (Exception e){
            throw new PersistenceException("No se Pudo consultar id de Usuario" + e.getMessage());
        }
    }

    @Override
    public List<Booking> viewBookingUser(String email) throws PersistenceException {
        try {
            return userMapper.viewBookingUser(email);
        }catch (Exception e){
            throw new PersistenceException("No se puede consulatr las reservas del usuario actual" + e.getMessage());
        }
    }

    @Override
    public User getUserById(int id) throws PersistenceException {
        try{
            return userMapper.getUserById(id);
        }catch(Exception e) {
            throw new PersistenceException("No se pudo consultar el horario" + e.getMessage());
        }
    }

    @Override
    public List<Report> getReportByResourceMan() {
        return userMapper.getReportByResourceMan();
    }

    @Override
    public List<Report> getReportByResourceMen() {
        return userMapper.getReportByResourceMen();
    }

    @Override
    public List<Report> getReportByResourceRec() {
        return userMapper.getReportByResourceRec();
    }

    @Override
    public List<Report> getReportByResourceCan() {
        return userMapper.getReportByResourceCan();
    }


}
