package algorithm;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import graph.Graph;
import algorithm.components.CandidateSolution;
import algorithm.components.PGGAlgorithmConfiguration;
import algorithm.components.Population;
import algorithm.components.operators.CrossoverOperator;
import algorithm.components.operators.FitnessEvaluator;
import algorithm.components.operators.MutationOperator;
import algorithm.components.operators.ParentSelector;
import algorithm.components.operators.SurvivorSelector;

public class SimpleGeneticAlgorithm implements PGGAlgorithm {
	private Random rand;
	private ParentSelector parentSelector;
	private SurvivorSelector survivorSelector;
	private MutationOperator mutationOperator;
	private CrossoverOperator crossoverOperator;
	private FitnessEvaluator fitnessEvaluator;
	private int popSize;
	
	
	

	@Override
	public void algorithmConfig(PGGAlgorithmConfiguration conf) {
		// TODO Auto-generated method stub
	}

	@Override
	public CandidateSolution evolveSolutions(Graph g) {
		//Create initial population (according to the required representation):
		Population population = new Population(g, popSize, rand);
		
		//Evolve candidate solutions until termination condition holds:
		while(!shouldTerminate()){
			//1. Parent selection:
			List<CandidateSolution> parents = parentSelector.select(population, fitnessEvaluator);
			
			//2. Shuffle mating pool:
			Collections.shuffle(parents);
			
			//3. Perform crossover for each consecutive pair with probability p_c (otherwise copy parents):
			population = computeNextGeneration(parents);
			
			//4. Perform mutation on offsprings with probability p_m:
			mutatePopulation(population);
			
			//5. Survivor selection
			population = survivorSelector.select(population, fitnessEvaluator);
		}
		
		CandidateSolution bestSol = chooseBestSolution(population);
		
		//TODO - perform best improvement or some other local search
		//       improvement to achieve a PNE (with/without side payments)
		
		return bestSol;
	}
	
	private void mutatePopulation(Population population) {
		// TODO Auto-generated method stub
		
	}

	private Population computeNextGeneration(List<CandidateSolution> parents) {
		// TODO Auto-generated method stub
		return null;
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
