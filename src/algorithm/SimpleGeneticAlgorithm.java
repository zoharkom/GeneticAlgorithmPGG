package algorithm;

import graph.Graph;
import algorithm.components.CandidateSolution;
import algorithm.components.PGGAlgorithmConfiguration;
import algorithm.components.Population;
import algorithm.components.operators.CrossoverOperator;
import algorithm.components.operators.MutationOperator;
import algorithm.components.operators.ParentSelector;
import algorithm.components.operators.SurvivorSelector;

public class SimpleGeneticAlgorithm implements PGGAlgorithm {
	
	private ParentSelector parentSelector;
	private SurvivorSelector survivorSelector;
	private MutationOperator mutationOperator;
	private CrossoverOperator crossoverOperator;
	
	

	@Override
	public void algorithmConfig(PGGAlgorithmConfiguration conf) {
		// TODO Auto-generated method stub
	}

	@Override
	public CandidateSolution evolveSolutions(Graph g) {
		//Create initial population (according to the required representation):
		Population population = new Population();
		
		//Evolve candidate solutions until termination condition holds:
		while(!shouldTerminate()){
			//TODO:
			//1. Parent selection
			//2. Select pairs for crossover
			//3. Perform crossover with probability p_c
			//4. Perform mutation on offsprings with probability p_m
			//5. Survivor selection
		}
		
		CandidateSolution bestSol = chooseBestSolution(population);
		
		//TODO - perform best improvement or some other local search
		//       improvement to achieve a PNE (with/without side payments)
		
		return bestSol;
	}
	
	private CandidateSolution chooseBestSolution(Population population) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean shouldTerminate(){
		// TODO Auto-generated method stub
		return false;
	}
	
}
