package algorithm.components.operators;

import java.util.List;

import algorithm.components.CandidateSolution;
import algorithm.components.Population;

public interface ParentSelector {

	public List<CandidateSolution> select(Population population, FitnessEvaluator fitnessEvaluator);

}
