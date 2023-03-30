package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain;

public class Partida {
	
	private int id;
	private int dau1;
	private int dau2;
	private Boolean guanyada;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDau1() {
		return dau1;
	}
	public void setDau1(int dau1) {
		this.dau1 = dau1;
	}
	public int getDau2() {
		return dau2;
	}
	public void setDau2(int dau2) {
		this.dau2 = dau2;
	}
	public Boolean getGuanyada() {
		return guanyada;
	}
	public void setGuanyada(Boolean guanyada) {
		this.guanyada = guanyada;
	}
	public Partida() {
		super();
	}
	public Partida(int id, int dau1, int dau2, Boolean guanyada) {
		super();
		this.id = id;
		this.dau1 = dau1;
		this.dau2 = dau2;
		this.guanyada = guanyada;
	}
	
	
	

}
