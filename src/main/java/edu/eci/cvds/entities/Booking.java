package edu.eci.cvds.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {
    @Getter @Setter private int id;
    @Getter @Setter private Date fechaInicio;
    @Getter @Setter private Date fechaFin;
    @Getter @Setter private int usuario_id;
    @Getter @Setter private int recurso_id;
    @Getter @Setter private String nombreUsuario;
    @Getter @Setter private String emailUsuario;
    @Getter @Setter private String nombreRecurso;
    @Getter @Setter private String ubiRecurso;
    @Getter @Setter private String tipoRecurso;

    public Booking(){
        super();
    }

    public Booking(int id, Date fechaInicio,Date fechaFin){
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public Booking(Date fechaInicio,Date fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Booking(int id, Date fechaInicio, Date fechaFin, int usuario_id, int recurso_id){
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario_id = usuario_id;
        this.recurso_id = recurso_id;
    }

    public Booking(Booking booking, String nombreUsuario, String emailUsuario, String nombreRecurso, String ubiRecurso, String tipoRecurso) {
        this.id = booking.getId();
        this.fechaInicio = booking.getFechaInicio();
        this.fechaFin = booking.getFechaFin();
        this.usuario_id = booking.getUsuario_id();
        this.recurso_id = booking.getRecurso_id();
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.nombreRecurso = nombreRecurso;
        this.ubiRecurso = ubiRecurso;
        this.tipoRecurso = tipoRecurso;
    }

    public Booking(Booking booking, String nombreRecurso, String ubiRecurso, String tipoRecurso) {
        this.id = booking.getId();
        this.fechaInicio = booking.getFechaInicio();
        this.fechaFin = booking.getFechaFin();
        this.usuario_id = booking.getUsuario_id();
        this.recurso_id = booking.getRecurso_id();
        this.nombreRecurso = nombreRecurso;
        this.ubiRecurso = ubiRecurso;
        this.tipoRecurso = tipoRecurso;
    }

    public Booking(Booking booking, String nombreRecurso, String tipoRecurso) {
        this.id = booking.getId();
        this.fechaInicio = booking.getFechaInicio();
        this.fechaFin = booking.getFechaFin();
        this.usuario_id = booking.getUsuario_id();
        this.recurso_id = booking.getRecurso_id();
        this.nombreRecurso = nombreRecurso;
        this.tipoRecurso = tipoRecurso;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
