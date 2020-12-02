package com.masivian.roulette.model;


import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@RedisHash("Roulette")
public class Roulette implements Serializable { 
	private static final long serialVersionUID = 1L;
	private @NonNull String id;
	private @NonNull String state;
}