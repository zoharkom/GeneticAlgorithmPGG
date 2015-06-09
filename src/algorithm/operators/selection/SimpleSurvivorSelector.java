package algorithm.operators.selection;

import graph.Graph;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public class SimpleSurvivorSelector implements SurvivorSelector {


	@Override
	public Population select(Population population, Population parents, Graph g, FitnessEvaluator fitnessEvaluator) {
		return population;
	}

}
