package algorithm.components.operators;

import algorithm.components.Population;

public interface SurvivorSelector {
	Population select(Population population, FitnessEvaluator fitnessEvaluator);
}
