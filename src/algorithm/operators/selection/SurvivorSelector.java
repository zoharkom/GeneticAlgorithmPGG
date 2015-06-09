package algorithm.operators.selection;

import java.rmi.UnexpectedException;

import graph.Graph;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public interface SurvivorSelector {
	Population select(Population population, Population parents, Graph g, FitnessEvaluator fitnessEvaluator) throws UnexpectedException;
}
