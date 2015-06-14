package algorithm;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import graph.Graph;
import algorithm.components.CandidateSolution;
import algorithm.components.Population;
import algorithm.config.SGAConfiguration;
import algorithm.operators.crossover.CrossoverOperator;
import algorithm.operators.fitness.BestPNEFitnessEvaluator;
import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.improve.SolutionImprover;
import algorithm.operators.mutation.MutationOperator;
import algorithm.operators.selection.ParentSelector;
import algorithm.operators.selection.SurvivorSelector;

public class TwoPhaseSimpleGeneticAlgorithm implements PGGAlgorithm {
	protected Random rand;
	
	protected int popSize;
	protected double mutationProb;
	protected double crossoverProb;
	protected int numOfGenerations;
	
	protected ParentSelector parentSelector;
	protected SurvivorSelector survivorSelector;
	protected MutationOperator mutationOperator;
	protected CrossoverOperator crossoverOperator;
	protected FitnessEvaluator fitnessEvaluator;
	protected SolutionImprover solutionImprover;

	
	public void algorithmConfig(SGAConfiguration conf) {
		this.rand = conf.getRand();
		
		this.popSize = conf.getPopulationSize();
		this.mutationProb = conf.getMutationProbability();
		this.crossoverProb = conf.getCrossoverProbability();
		this.numOfGenerations = conf.getNumberOfGenerations();
		
		this.parentSelector = conf.getParentSelector();
		this.survivorSelector = conf.getSurvivorSelector();
		this.mutationOperator = conf.getMutationOperator();
		this.crossoverOperator = conf.getCrossoverOperator();
		this.fitnessEvaluator = conf.getFitnessEvaluator();
		this.solutionImprover = conf.getSolutionImprover();
	}
	
	@Override
	public CandidateSolution findSolution(Graph g, ArrayList<double[]> stat) throws UnexpectedException {
		//Create initial population (according to the required representation):
		Population population = new Population(g, popSize, rand);
		
		int generation = 0;
		
		//Evolve candidate solutions until termination condition holds:
		while(!shouldTerminate(generation++)){
			if(generation >= numOfGenerations/2){
				fitnessEvaluator = new BestPNEFitnessEvaluator();
			}
			
			//1. Parent selection:
			Population parents = parentSelector.select(population, g, fitnessEvaluator);
			List<CandidateSolution> parentsList = parents.asList();
			
			//2. Shuffle mating pool:
			Collections.shuffle(parentsList);
			
			//3. Perform crossover for each consecutive pair with probability p_c (otherwise copy parents):
			population = computeNextGeneration(parentsList);
			
			//4. Perform mutation on offsprings with probability p_m:
			mutatePopulation(population);
			
			//5. Survivor selection
			population = survivorSelector.select(population,parents, g, fitnessEvaluator);
			
//			CandidateSolution bestSolOfGen = chooseBestSolution(g, population);
//			System.out.println("Generation: "+generation);
//			System.out.println("Best solution fitness: " + fitnessEvaluator.evaluate(g, bestSolOfGen));
		}
		
		CandidateSolution bestSol = chooseBestSolution(g, population);
		
		//Perform an improvement to the found solution:
		bestSol = solutionImprover.improveSolution(g, bestSol);
		
		return bestSol;
	}
	
	private Population computeNextGeneration(List<CandidateSolution> parents) throws UnexpectedException {
		Population nextGen = new Population();
		
		Iterator<CandidateSolution> iter = parents.iterator();
		while(iter.hasNext()){
			CandidateSolution s1 = iter.next();
			if(iter.hasNext()){ //Standard case with 2 parents
				CandidateSolution s2 = iter.next();
				
				if(rand.nextDouble() < crossoverProb){ //Perform crossover with probability p_c:
					CandidateSolution offspring1 = crossoverOperator.doCrossover(s1, s2);
					CandidateSolution offspring2 = crossoverOperator.doCrossover(s1, s2);
					
					nextGen.addIndividual(offspring1);
					nextGen.addIndividual(offspring2);
					
				} else { //No crossover, just copy parents:
					nextGen.addIndividual(s1);
					nextGen.addIndividual(s2);
				}
				
			} else { //Only one parent (number of parents is odd and this is the last parent)
				nextGen.addIndividual(s1);
			}
		}

		return nextGen;
	}

	private CandidateSolution chooseBestSolution(Graph g,Population population) throws UnexpectedException {
		CandidateSolution bestSol = null;
		double bestFitness = Integer.MIN_VALUE;
		
		for(int i = 0; i < population.getSize() ; i++){
			CandidateSolution currentSol = population.getIndividual(i);
			double currentFitness = fitnessEvaluator.evaluate(g, currentSol);
			if(currentFitness >= bestFitness){
				bestFitness = currentFitness;
				bestSol = currentSol;
			}
		}
		
		return bestSol;
	}

	private boolean shouldTerminate(int generation){
		return (generation >= numOfGenerations);
	}
	
	private void mutatePopulation(Population population) {
		for(int i = 0; i < population.getSize() ; i++){
			//Perform mutation to individual i with probability p_m:
			if(rand.nextDouble() < mutationProb){
				CandidateSolution beforeMutation = population.getIndividual(i);
				CandidateSolution afterMutation = mutationOperator.doMutation(beforeMutation);
				population.setIndividual(i, afterMutation);	
			}
		}
	}
}
