package algorithm.operators.crossover;

import java.rmi.UnexpectedException;
import java.util.Random;

import algorithm.components.CandidateSolution;

public class OnePointCrossoverOperator implements CrossoverOperator {

	private Random rand;
	
	@Override
	public CandidateSolution doCrossover(CandidateSolution s1, CandidateSolution s2) throws UnexpectedException {
		
		if(s1.getSize() != s2.getSize()){
			throw new UnexpectedException("In crossover both parents are expected to be of the same size");
		}
		
		CandidateSolution offspring = new CandidateSolution(s1);
		int crossoverPoint = rand.nextInt(s1.getSize());
		
		for(int i = crossoverPoint ; i < s2.getSize() ; i++){
			offspring.setAllele(i, s2.getAllele(i));
		}
		
		return offspring;
	}

}
