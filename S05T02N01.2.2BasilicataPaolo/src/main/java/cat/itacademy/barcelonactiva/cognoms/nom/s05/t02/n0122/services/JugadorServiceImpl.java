package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.repository.JugadorRepository;


@Service
public class JugadorServiceImpl implements JugadorService{

	@Autowired
	JugadorRepository jugadorRepository;
	
	
	@Override
	public Jugador addJugador(Jugador jugador) {
		// TODO Auto-generated method stub
		return jugadorRepository.insert(jugador);
	}

	@Override
	public void updateJugador(Jugador jugador) {
		// TODO Auto-generated method stub
		jugadorRepository.save(jugador);
	}

	@Override
	public Optional<Jugador> getJugadorById(long id) {
		// TODO Auto-generated method stub
		
		return jugadorRepository.findById(id);
	}

	@Override
	public List<Jugador> getAll() {
		// TODO Auto-generated method stub
		return jugadorRepository.findAll();
	}

}
