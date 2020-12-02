package com.masivian.roulette.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.masivian.roulette.dto.RouletteBetDto;
import com.masivian.roulette.dto.RouletteDto;
import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.model.RouletteBet;
import com.masivian.roulette.repository.RouletteBetRepository;
import com.masivian.roulette.repository.RouletteRepository;
import com.masivian.roulette.util.States;

@RestController
public class RouletteController {
	@Autowired
	RouletteRepository rouletteRepository;

	@Autowired
	RouletteBetRepository rouletteBetRepository;

	States state;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck() {
		return "{ \"isWorking\" : true }";
	}

	@GetMapping("/roulettes")
	public List<Roulette> getRoulettes() {
		Iterable<Roulette> result = rouletteRepository.findAll();
		List<Roulette> roulettesList = new ArrayList<Roulette>();
		result.forEach(roulettesList::add);
		return roulettesList;
	}

	@GetMapping("/roulette/{id}")
	public Optional<Roulette> getRoulette(@PathVariable String id) {
		Optional<Roulette> emp = rouletteRepository.findById(id);
		return emp;
	}

	@PutMapping("/roulette/open")
	public String openRoulette(@RequestBody RouletteDto rouletteDto) {
		try {
			Optional<Roulette> opionalRoulette = rouletteRepository.findById(rouletteDto.getId());
			if (opionalRoulette.isPresent()) {
				Roulette tempRoulette = opionalRoulette.get();
				tempRoulette.setState(state.OPEN.toString());
				rouletteRepository.save(tempRoulette);
			}
		} catch (Exception e) {
			return "{ \"success\" :  false  }";
		}
		return "{ \"success\" :  true  }";
	}

	@PutMapping("/roulette/close")
	public String closeRoulette(@RequestBody RouletteDto rouletteDto) {
		try {
			Optional<Roulette> opionalRoulette = rouletteRepository.findById(rouletteDto.getId());
			if (opionalRoulette.isPresent()) {
				Roulette tempRoulette = opionalRoulette.get();
				tempRoulette.setState(state.CLOSED.toString());
				rouletteRepository.save(tempRoulette);
			}
		} catch (Exception e) {
			return "{ \"success\" :  false  }";
		}
		return "{ \"success\" :  true  }";
	}

	@DeleteMapping(value = "/roulette/{id}", produces = "application/json; charset=utf-8")
	public String deleteRoulette(@PathVariable String id) {
		Boolean result = rouletteRepository.existsById(id);
		rouletteRepository.deleteById(id);
		return "{ \"success\" : " + (result ? "true" : "false") + " }";
	}

	@PostMapping("/roulette")
	public Roulette addRoulette() {
		String id = String.valueOf(new Random().nextInt());
		Roulette tempRoulette = new Roulette(id, state.OPEN.toString());
		rouletteRepository.save(tempRoulette);
		return tempRoulette;
	}

	@PostMapping("/rouletteBet")
	public String addRouletteBet(@RequestBody RouletteBetDto rouletteBetDto) {

		try {
			Optional<Roulette> opionalRoulette = rouletteRepository.findById(rouletteBetDto.getIdRoulette());
			if (opionalRoulette.isPresent() && opionalRoulette.get().getState().equals(state.OPEN.toString())) {
				String id = String.valueOf(new Random().nextInt());
				RouletteBet tempRouletteBet = new RouletteBet(id, state.OPEN.toString(), rouletteBetDto.getIdRoulette(),
						"", rouletteBetDto.getNumber(), rouletteBetDto.getColor(), rouletteBetDto.getAmount(),
						rouletteBetDto.isNumeric());
				rouletteBetRepository.save(tempRouletteBet);
				return "{ \"success\" :  true  }";
			}
			else {
				return "{ \"success\" :  false, don't roulette find. }";
			}
		} catch (Exception e) {
			return "{ \"success\" :  false  }";
		}
	}

	@GetMapping("/rouletteBets")
	public List<RouletteBet> getRouletteBets() {
		Iterable<RouletteBet> result = rouletteBetRepository.findAll();
		List<RouletteBet> rouletteBetsList = new ArrayList<RouletteBet>();
		result.forEach(rouletteBetsList::add);
		return rouletteBetsList;
	}
}
