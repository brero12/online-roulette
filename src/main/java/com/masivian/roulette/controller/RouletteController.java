package com.masivian.roulette.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.repository.RouletteRepository;

@RestController
public class RouletteController
{
	@Autowired
	RouletteRepository rouletteRepository;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"isWorking\" : true }";
	}

	@GetMapping("/roulettes")
	public List<Roulette> getEmployees()
	{
		Iterable<Roulette> result = rouletteRepository.findAll();
		List<Roulette> employeesList = new ArrayList<Roulette>();
		result.forEach(employeesList::add);
		return employeesList;
	}

	@GetMapping("/roulette/{id}")
	public Optional<Roulette> getEmployee(@PathVariable String id)
	{
		Optional<Roulette> emp = rouletteRepository.findById(id);
		return emp;
	}

	@PutMapping("/roulette/{id}")
	public Optional<Roulette> updateRoulette(@RequestBody Roulette newRoulette, @PathVariable String id)
	{
		Optional<Roulette> opionalRoulette = rouletteRepository.findById(id);
		if (opionalRoulette.isPresent()) {
			Roulette tempRoulette = opionalRoulette.get();
			tempRoulette.setState(newRoulette.getState());
			rouletteRepository.save(tempRoulette);
		}
		return opionalRoulette;
	}

	@DeleteMapping(value = "/roulette/{id}", produces = "application/json; charset=utf-8")
	public String deleteRoulette(@PathVariable String id) {
		Boolean result = rouletteRepository.existsById(id);
		rouletteRepository.deleteById(id);
		return "{ \"success\" : "+ (result ? "true" : "false") +" }";
	}

	@PostMapping("/roulette")
	public Roulette addRoulette(@RequestBody Roulette newRoulette)
	{
		String id = String.valueOf(new Random().nextInt());
		Roulette tempRoulette = new Roulette(id, newRoulette.getState());
		rouletteRepository.save(tempRoulette);
		return tempRoulette;
	}
}
