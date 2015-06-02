package algorithm.operators.selection;

import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public interface ParentSelector {

	public Population select(Population population, FitnessEvaluator fitnessEvaluator);

}
