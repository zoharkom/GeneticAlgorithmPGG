package algorithm.operators.selection;

import java.util.List;

import algorithm.components.CandidateSolution;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public interface ParentSelector {

	public List<CandidateSolution> select(Population population, FitnessEvaluator fitnessEvaluator);

}