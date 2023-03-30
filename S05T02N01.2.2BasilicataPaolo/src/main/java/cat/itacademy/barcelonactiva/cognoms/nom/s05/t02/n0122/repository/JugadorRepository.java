package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Partida;

public interface JugadorRepository extends MongoRepository<Jugador, Long>{
	public List<Partida> findByPartidas(long id);
	//public void deleteAllPartidas(long id);

}
