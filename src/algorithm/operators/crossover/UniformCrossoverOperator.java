package algorithm.operators.crossover;

import java.rmi.UnexpectedException;
import java.util.Random;

import algorithm.components.CandidateSolution;

public class UniformCrossoverOperator implements CrossoverOperator{

	private Random rand;

	public UniformCrossoverOperator(Random rand) {
		this.rand = rand;
	}

	@Override
	public CandidateSolution doCrossover(CandidateSolution s1, CandidateSolution s2) throws UnexpectedException {
		if(s1.getSize() != s2.getSize()){
			throw new UnexpectedException("In crossover both parents are expected to be of the same size");
		}

		CandidateSolution offspring = new CandidateSolution(s1.getSize());
		

		for(int i = 0 ; i < s2.getSize() ; i++){
			if(rand.nextDouble() < 0.5){
				offspring.setAllele(i, s1.getAllele(i));
			} else {
				offspring.setAllele(i, s2.getAllele(i));
			}
		}

		return offspring;
	}

}
