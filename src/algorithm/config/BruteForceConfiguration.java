package algorithm.config;

import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.fitness.MaxSocialWelfareFitnessEvaluator;

public class BruteForceConfiguration implements PGGAlgorithmConfiguration{
	
	private FitnessEvaluator fitnessEvaluator;
	
	public static BruteForceConfiguration generateDefaultBruteForceConfiguration(){
		BruteForceConfiguration conf = new BruteForceConfiguration();
		conf.fitnessEvaluator = new MaxSocialWelfareFitnessEvaluator();
		return conf;
		
	}

	public FitnessEvaluator getFitnessEvaluator() {
		return fitnessEvaluator;
	}

	public void setFitnessEvaluator(FitnessEvaluator fitnessEvaluator) {
		this.fitnessEvaluator = fitnessEvaluator;
	}



}
