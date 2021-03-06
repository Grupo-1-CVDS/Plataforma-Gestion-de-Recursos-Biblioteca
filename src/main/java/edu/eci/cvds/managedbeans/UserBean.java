package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Booking;
import edu.eci.cvds.entities.Report;
import edu.eci.cvds.entities.Resource;
import edu.eci.cvds.entities.User;
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
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.PieChartModel;
import javax.faces.bean.ManagedBean;

/**
 * Bean para la interfaz de usuario de los Usuarios
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "userBean")
@ApplicationScoped
public class UserBean extends BasePageBean {

	@Getter @Setter private int idRecurso;
	@Getter @Setter private int idUser;
	@Getter @Setter private String email;
	@Getter @Setter private String password;
	@Setter private List<Booking> bookingsUser;
	@Setter private List<Report> reportsMasU;
	@Setter private List<Report> reportsMenU;
	@Setter private List<Report> reportsRec;
	@Setter private List<Report> reportsCan;
	@Getter @Setter private ArrayList<Report> reportsFilter = new ArrayList<>();
	@Getter @Setter private PieChartModel model;

	@lombok.Generated
	public UserBean() {
		model = new PieChartModel();
		try {
			getReportsMasU();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setTitle("Recursos con mayores y menores reservas");
		model.setLegendPosition("e");
		model.setFill(true);
		model.setShowDataLabels(true);
		model.setDiameter(250);
	}

	@Inject
	private ECIStuffServices eciStuffServices;

	@lombok.Generated
	public void signIn() throws Exception{
		try{
			System.out.println("SIGNIN");
			eciStuffServices.signIn(email, password);
			System.out.println(email);
			bookingsUser = eciStuffServices.viewBookingUser();
		}catch (ServicesException ex){
			throw ex;
		}
	}

	@lombok.Generated
	public void logOut() throws Exception{
		try {
			System.out.println("====================LOGOUT========================");
			eciStuffServices.logOut();
		}catch (ServicesException ex) {
			throw ex;
		}
	}

	@lombok.Generated
	public void viewBookingUser() throws Exception{
		try{
			bookingsUser = eciStuffServices.viewBookingUser();
		}catch (ServicesException ex){
			throw ex;
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/reservasUsuario.xhtml");
	}

	public void updateBookingsUser() throws Exception{
		try {
			bookingsUser = eciStuffServices.viewBookingUser();
		}catch (ServicesException ex){
			  throw ex;
		}
	}

	public String getEmail() {
		return email;
	}

	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setEmail(String email) {
		System.out.println(email);
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}

	public User getUserIdByEmail(int idRecurso) throws ServicesException{
		this.idRecurso = idRecurso;

		try{
			User result = eciStuffServices.getUserIdByEmail(this.email);
			this.idUser = result.getId();
			System.out.println(result);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/reservarRecurso.xhtml");
			return result;
		}catch (Exception e){
			throw new ServicesException(e.getMessage());
		}
	}

	public List<Booking> getBookingsUser() throws Exception{
		updateBookingsUser();
		return bookingsUser;
	}

	public List<Report> getReportsMasU() throws Exception{
		reportsMasU = eciStuffServices.getReportByResourceMan();
		for (Report r: reportsMasU) {
			model.set(r.getNombre(), r.getNumReservas());
		}
		return reportsMasU;
	}
	public List<Report> getReportsMenU() throws Exception{
		reportsMenU = eciStuffServices.getReportByResourceMen();
		return reportsMenU;
	}
	public List<Report> getReportsRec() throws Exception{
		reportsRec = eciStuffServices.getReportByResourceRec();
		return reportsRec;
	}
	public List<Report> getReportsCan() throws Exception{
		reportsCan = eciStuffServices.getReportByResourceCan();
		return reportsCan;
	}

}
