package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Jugador")
public class Jugador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String nom;
	
	@CreatedDate
	private Date dataRegistre;
	
	
	private List<Partida> partidas = new ArrayList<>();
	
	
	
	public List<Partida> getPartidas() {
		return partidas;
	}
	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDataRegistre() {
		return dataRegistre;
	}
	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}
	public Jugador() {
		super();
	}
	public Jugador(String nom, Date dataRegistre) {
		super();
		//this.id = id;
		this.nom = nom;
		this.dataRegistre = dataRegistre;
	}

	
}
