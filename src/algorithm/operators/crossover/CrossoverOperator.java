package algorithm.operators.crossover;

import java.rmi.UnexpectedException;

import algorithm.components.CandidateSolution;

public interface CrossoverOperator {
	public CandidateSolution doCrossover(CandidateSolution s1, CandidateSolution s2) throws UnexpectedException;
}
