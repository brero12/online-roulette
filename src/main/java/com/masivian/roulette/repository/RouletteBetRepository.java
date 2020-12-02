package com.masivian.roulette.repository;

import org.springframework.data.repository.CrudRepository;

import com.masivian.roulette.model.RouletteBet;

public interface RouletteBetRepository extends CrudRepository<RouletteBet, String> {
}
