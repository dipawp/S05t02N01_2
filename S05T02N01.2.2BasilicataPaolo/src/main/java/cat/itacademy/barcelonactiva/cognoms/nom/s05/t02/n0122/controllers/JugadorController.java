package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.DTO.JugadorDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.model.domain.Partida;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.services.JugadorServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n0122.services.PartidaDausImpl;



@RestController
public class JugadorController {
	
	
	@Autowired
	JugadorServiceImpl jugadorServiceImpl;
	
	
	@Autowired
	PartidaDausImpl partidaDausImpl;
	
	
	@PostMapping(path = "/players")
	public ResponseEntity<Jugador> addJugador(@RequestBody Jugador jugador){
		try {
		 Jugador jug = jugadorServiceImpl.addJugador(jugador);
		 return new ResponseEntity<>(jug,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(path = "/players")
	public ResponseEntity<Jugador> updateJugador(@RequestBody Jugador jugador){
		try {
			Optional<Jugador> jugOptional = jugadorServiceImpl.getJugadorById(jugador.getId());
			if(jugOptional.isPresent()) {
				Jugador jugadorUp = jugOptional.get(); //new Jugador(jugador.getNom(),new Date());
				jugadorUp.setNom(jugador.getNom());
				jugadorServiceImpl.updateJugador(jugadorUp);
				return new ResponseEntity<>(jugadorUp,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			//System.out.println(e.getMessage());
		}
	}
	
	
	
	@PostMapping(path = "/players/{id}/games")
	public ResponseEntity<Partida> getPartidas(@PathVariable("id") long id){
		
		Partida partida = partidaDausImpl.llencarDaus();
		Jugador jugador = new Jugador();
		try {
			Optional<Jugador> jugadorData = jugadorServiceImpl.getJugadorById(id);
			if(jugadorData.isPresent()) {
			   
			   jugador.setId(jugadorData.get().getId());
			   jugador.setNom(jugadorData.get().getNom());
			   jugador.setDataRegistre(jugadorData.get().getDataRegistre());
			   jugador.setPartidas(jugadorData.get().getPartidas());
			   partida.setId(jugadorData.get().getPartidas().size());
			   jugador.getPartidas().add(partida);
			   jugadorServiceImpl.updateJugador(jugador);
			   return new ResponseEntity<>(partida,HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping(path = "/players/{id}")
	public ResponseEntity<Partida> tiraDaus(@PathVariable("id") long id){
		try {
			Optional<Jugador> jugadorData = jugadorServiceImpl.getJugadorById(id);
			if(jugadorData.isPresent()) {
				Jugador jugador = jugadorData.get();
				jugador.getPartidas().clear();
				jugadorServiceImpl.updateJugador(jugador);
				return new ResponseEntity<>(null,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(path = "/players")
	public ResponseEntity<List<JugadorDTO>> getPlayersStatistics(){
		List<JugadorDTO> jugadorsDTO = new ArrayList<>();
		List<Jugador> jugadors = jugadorServiceImpl.getAll();
		if(jugadors.size() > 0) {
			for(Jugador jugador: jugadors) {
				List<Partida> partidas = jugador.getPartidas(); //partidaServiceImpl.findAllByJugadorId(jugador.getId());
				int win = 0;
				for(Partida partida: partidas) {
					if(partida.getGuanyada()) {
						win += 1;
					}
				}
				JugadorDTO jugadorDTO = new JugadorDTO(jugador.getNom(), ((float)(partidas.size() * win) / 100));
				jugadorsDTO.add(jugadorDTO);
			}
		}
		return new ResponseEntity<List<JugadorDTO>>(jugadorsDTO,HttpStatus.OK);
	}
	
	
	
	@GetMapping(path = "/players/ranking")
	public ResponseEntity<String> getMidPlayersRanking(){
		List<Jugador> jugadors = jugadorServiceImpl.getAll();
		float ranking = 0f;
		if(jugadors.size() > 0) {
			for(Jugador jugador: jugadors) {
				List<Partida> partidas = jugador.getPartidas(); //partidaServiceImpl.findAllByJugadorId(jugador.getId());
				int win = 0;
				for(Partida partida: partidas) {
					if(partida.getGuanyada()) {
						win += 1;
					}
				}
				ranking += ((float)(partidas.size() * win) / 100);
			}
		}
		return new ResponseEntity<String>("El ranking mig es: " +  Float.toString(((float)ranking) / jugadors.size()) + "%",HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/players/ranking/loser")
	public ResponseEntity<JugadorDTO> getLoser(){
		List<JugadorDTO> jugadorsDTO = new ArrayList<>();
		List<Jugador> jugadors = jugadorServiceImpl.getAll();
		if(jugadors.size() > 0) {
			for(Jugador jugador: jugadors) {
				List<Partida> partidas = jugador.getPartidas(); //partidaServiceImpl.findAllByJugadorId(jugador.getId());
				int win = 0;
				for(Partida partida: partidas) {
					if(partida.getGuanyada()) {
						win += 1;
					}
				}
				JugadorDTO jugadorDTO = new JugadorDTO(jugador.getNom(), ((float)(partidas.size() * win) / 100));
				jugadorsDTO.add(jugadorDTO);
			}
			Collections.sort(jugadorsDTO, new Comparator<JugadorDTO>() {
				@Override
				public int compare(JugadorDTO o1, JugadorDTO o2) {
					// TODO Auto-generated method stub
					return Float.compare(o1.getExit(), o1.getExit());
				}
			});
			return new ResponseEntity<>(jugadorsDTO.get(0),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@GetMapping(path = "/players/ranking/winner")
	public ResponseEntity<JugadorDTO> getWinner(){
		List<JugadorDTO> jugadorsDTO = new ArrayList<>();
		List<Jugador> jugadors = jugadorServiceImpl.getAll();
		if(jugadors.size() > 0) {
			for(Jugador jugador: jugadors) {
				List<Partida> partidas = jugador.getPartidas(); //partidaServiceImpl.findAllByJugadorId(jugador.getId());
				int win = 0;
				for(Partida partida: partidas) {
					if(partida.getGuanyada()) {
						win += 1;
					}
				}
				JugadorDTO jugadorDTO = new JugadorDTO(jugador.getNom(), ((float)(partidas.size() * win) / 100));
				jugadorsDTO.add(jugadorDTO);
			}
			Collections.sort(jugadorsDTO, new Comparator<JugadorDTO>() {
				@Override
				public int compare(JugadorDTO o1, JugadorDTO o2) {
					// TODO Auto-generated method stub
					return Float.compare(o2.getExit(), o1.getExit());
				}
			});
			return new ResponseEntity<>(jugadorsDTO.get(0),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		
	}
}
