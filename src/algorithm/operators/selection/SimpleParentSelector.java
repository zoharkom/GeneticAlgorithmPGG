package algorithm.operators.selection;

import graph.Graph;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public class SimpleParentSelector implements ParentSelector {

	@Override
	public Population select(Population population,Graph g, FitnessEvaluator fitnessEvaluator) {
		return population;
	}

}
