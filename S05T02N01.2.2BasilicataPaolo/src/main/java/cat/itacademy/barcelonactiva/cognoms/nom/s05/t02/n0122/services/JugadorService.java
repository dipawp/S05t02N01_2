package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.services;

import java.util.List;
import java.util.Optional;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Jugador;

public interface JugadorService {

	public Jugador addJugador(Jugador jugador);
	public void updateJugador(Jugador jugador);
	public Optional<Jugador> getJugadorById(long id);
	public List<Jugador> getAll();
}
