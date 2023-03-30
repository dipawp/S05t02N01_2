package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.services;

import java.util.List;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Partida;


public interface PartidaService {
	public Partida addPartida(Partida partida);
	public void deleteAllById(long id);
	public List<Partida> findAllByJugadorId(long id);
	public void deleteAllByJugadorId(int id);
	
}
