package com.masivian.roulette.repository;

import org.springframework.data.repository.CrudRepository;

import com.masivian.roulette.model.Roulette;

public interface RouletteRepository extends CrudRepository<Roulette, String> {
}
