package algorithm.operators.selection;

import java.rmi.UnexpectedException;

import graph.Graph;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public interface ParentSelector {

	public Population select(Population population,Graph g, FitnessEvaluator fitnessEvaluator) throws UnexpectedException;

}
