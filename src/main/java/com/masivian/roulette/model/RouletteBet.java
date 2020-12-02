package com.masivian.roulette.model;


import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@RedisHash("RouletteBet")
public class RouletteBet implements Serializable { 
	private static final long serialVersionUID = 1L;
	private @NonNull String id;
	private @NonNull String state;
	private @NonNull String idRoulette;
	private @NonNull String idUserBet;	
	private int number;	
	private String color;	
	private float ammount;	
}