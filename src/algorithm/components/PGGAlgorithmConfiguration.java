package algorithm.components;

import java.util.Random;

import algorithm.components.operators.CrossoverOperator;
import algorithm.components.operators.FitnessEvaluator;
import algorithm.components.operators.MutationOperator;
import algorithm.components.operators.ParentSelector;
import algorithm.components.operators.SolutionImprover;
import algorithm.components.operators.SurvivorSelector;

public class PGGAlgorithmConfiguration {

	public double getMutationProbability() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPopulationSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getCrossoverProbability() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfGenerations() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ParentSelector getParentSelector() {
		// TODO Auto-generated method stub
		return null;
	}

	public SurvivorSelector getSurvivorSelector() {
		// TODO Auto-generated method stub
		return null;
	}

	public MutationOperator getMutationOperator() {
		// TODO Auto-generated method stub
		return null;
	}

	public CrossoverOperator getCrossoverOperator() {
		// TODO Auto-generated method stub
		return null;
	}

	public FitnessEvaluator getFitnessEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	public SolutionImprover getSolutionImprover() {
		// TODO Auto-generated method stub
		return null;
	}

	public Random getRandomGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

}
