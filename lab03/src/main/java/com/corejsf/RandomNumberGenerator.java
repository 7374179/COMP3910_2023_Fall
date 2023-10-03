package com.corejsf;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

@Named("random")
@ApplicationScoped
public class RandomNumberGenerator {
	private Random random = new Random(System.currentTimeMillis());
	
	@Produces @Named
	int getRandomNumber() {
		return random.nextInt(100)+1;

	}
	
}
