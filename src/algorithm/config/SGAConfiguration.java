package algorithm.config;

import java.util.Random;

import algorithm.operators.MaxSocialWelfareWithSidePaymentsImprover;
import algorithm.operators.SolutionImprover;
import algorithm.operators.crossover.CrossoverOperator;
import algorithm.operators.crossover.OnePointCrossoverOperator;
import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.fitness.MaxSocialWelfareFitnessEvaluator;
import algorithm.operators.mutation.MutationOperator;
import algorithm.operators.mutation.SimpleMutationOperator;
import algorithm.operators.selection.ParentSelector;
import algorithm.operators.selection.SimpleParentSelector;
import algorithm.operators.selection.SimpleSurvivorSelector;
import algorithm.operators.selection.SurvivorSelector;

public class SGAConfiguration implements PGGAlgorithmConfiguration {
	
	private final static long SEED = 37;
	
	private Random rand;
	
	private int populationSize;
	private int numberOfGenerations;
	private double mutationProb;
	private double crossoverProb;
	
	private ParentSelector parentSelector;
	private SurvivorSelector survivorSelector;
	private MutationOperator mutationOperator;
	private CrossoverOperator crossoverOperator;
	private FitnessEvaluator fitnessEvaluator;
	private SolutionImprover solutionImprover;
	
	public static SGAConfiguration generateDefaultSGAConfiguration(){
		SGAConfiguration conf = new SGAConfiguration();
		conf.rand = new Random(SEED);
		
		conf.populationSize = 100;
		conf.numberOfGenerations = 1000;
		conf.mutationProb = 0.1;
		conf.crossoverProb = 0.8;
		
		conf.parentSelector = new SimpleParentSelector();
		conf.survivorSelector = new SimpleSurvivorSelector();
		conf.mutationOperator = new SimpleMutationOperator(conf.rand, conf.mutationProb);
		conf.crossoverOperator = new OnePointCrossoverOperator(conf.rand);
		conf.fitnessEvaluator = new MaxSocialWelfareFitnessEvaluator();
		conf.solutionImprover = new MaxSocialWelfareWithSidePaymentsImprover();
		
		
		
		return conf;
	}
	
	public void setMutationProbability(double mutationProb) {
		this.mutationProb = mutationProb;
	}

	public double getMutationProbability() {
		return mutationProb;
	}
	
	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public double getCrossoverProbability() {
		return crossoverProb;
	}

	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProb = crossoverProbability;
	}

	public int getNumberOfGenerations() {
		return numberOfGenerations;
	}

	public void setNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}

	public ParentSelector getParentSelector() {
		return parentSelector;
	}

	public void setParentSelector(ParentSelector parentSelector) {
		this.parentSelector = parentSelector;
	}

	public SurvivorSelector getSurvivorSelector() {
		return survivorSelector;
	}

	public void setSurvivorSelector(SurvivorSelector survivorSelector) {
		this.survivorSelector = survivorSelector;
	}

	public MutationOperator getMutationOperator() {
		return mutationOperator;
	}

	public void setMutationOperator(MutationOperator mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public CrossoverOperator getCrossoverOperator() {
		return crossoverOperator;
	}

	public void setCrossoverOperator(CrossoverOperator crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
	}

	public FitnessEvaluator getFitnessEvaluator() {
		return fitnessEvaluator;
	}

	public void setFitnessEvaluator(FitnessEvaluator fitnessEvaluator) {
		this.fitnessEvaluator = fitnessEvaluator;
	}

	public SolutionImprover getSolutionImprover() {
		return solutionImprover;
	}

	public void setSolutionImprover(SolutionImprover solutionImprover) {
		this.solutionImprover = solutionImprover;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

}
