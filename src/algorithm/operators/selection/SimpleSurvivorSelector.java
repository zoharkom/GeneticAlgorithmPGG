package algorithm.operators.selection;

import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public class SimpleSurvivorSelector implements SurvivorSelector {

	@Override
	public Population select(Population population, FitnessEvaluator fitnessEvaluator) {
		return population;
	}

}
