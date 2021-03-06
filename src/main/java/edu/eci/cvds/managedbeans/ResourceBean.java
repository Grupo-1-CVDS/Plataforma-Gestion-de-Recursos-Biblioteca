package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Resource;
import edu.eci.cvds.services.ECIStuffServices;
import edu.eci.cvds.services.ServicesException;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ManagedBean(name = "resourceBean")
@ApplicationScoped
public class ResourceBean extends BasePageBean{

    @Inject
    private ECIStuffServices eciStuffServices;
    @Setter @Getter private List<Resource> result;
    private int idSeleccionado;

    private String nombre;
    private String ubicacion;
    private String tipo;
    private String capacidad;
    private String iDisp;
    private String fDisp;

    private String descripcion;

    private String disponible;

    public void registerResource() throws ServicesException{
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(iDisp);
            Date iDispSql = new Date(parsed.getTime());

            parsed = format.parse(fDisp);
            Date fDispSql = new Date(parsed.getTime());

            boolean disponibilidad = Boolean.parseBoolean(disponible);
            Resource resource = new Resource(this.nombre, this.ubicacion,this.descripcion, Integer.parseInt(this.capacidad), this.tipo , iDispSql, fDispSql,disponibilidad);

            eciStuffServices.registerResources(resource);

            System.out.println("Recurso Agregado: " + resource.toString());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/consultarRecursos.xhtml");

        }catch (Exception e){
            throw new ServicesException(e.getMessage());
        }
    }
    @PostConstruct
    public List<Resource> consultResources() throws ServicesException {
        try {
            result =  eciStuffServices.consultResources();
            return result;
        } catch (ServicesException ex) {
            throw new ServicesException(ex.getMessage());
        }
    }

    public List<Resource> consultALlResources() throws ServicesException {
        try {
            return   eciStuffServices.consultAllResources();
        } catch (ServicesException ex) {
            throw new ServicesException(ex.getMessage());
        }
    }
    public void changeResourceState(int idResource) throws ServicesException{
        try {
            eciStuffServices.changeResourceState(idResource);
        } catch (ServicesException ex) {
            throw new ServicesException(ex.getMessage());
        }
    }


    public ECIStuffServices getEciStuffServices() {
        return eciStuffServices;
    }

    public void setEciStuffServices(ECIStuffServices eciStuffServices) {
        this.eciStuffServices = eciStuffServices;
    }

    public int getIdSeleccionado() {
        return idSeleccionado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getiDisp() {
        return iDisp;
    }

    public void setiDisp(String iDisp) {
        this.iDisp = iDisp;
    }

    public String getfDisp() {
        return fDisp;
    }

    public void setfDisp(String fDisp) {
        this.fDisp = fDisp;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdSeleccionado(int idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }

    public Date parseToSqlDate(String date) throws ServicesException {
        try{
            System.out.println("Fecha:------"+date+"---------");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsed = format.parse(date);
            return new Date(parsed.getTime());
        }catch (Exception e){
            throw new ServicesException(e.getMessage());
        }


    }

}
