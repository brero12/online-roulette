package com.masivian.roulette.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter @Setter
public class RouletteBetDto { 
		
	private @NonNull String idRoulette;
	@Min(0)
	@Max(36)
	private int number;
	@Pattern(regexp = "((?!(red|black)).)*$")  
	private String color;
	@Max(10000)
	private float amount;
	private boolean numeric;	
}