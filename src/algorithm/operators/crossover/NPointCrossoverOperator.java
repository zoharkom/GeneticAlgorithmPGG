package algorithm.operators.crossover;

import java.rmi.UnexpectedException;
import java.util.Random;

import algorithm.components.CandidateSolution;

public class NPointCrossoverOperator implements CrossoverOperator{

	private Random rand;
	private int N;

	public NPointCrossoverOperator(Random rand, int n) {
		this.rand = rand;
		this.N = n;
	}

	@Override
	public CandidateSolution doCrossover(CandidateSolution s1, CandidateSolution s2) throws UnexpectedException {
		if(s1.getSize() != s2.getSize()){
			throw new UnexpectedException("In crossover both parents are expected to be of the same size");
		}

		CandidateSolution offspring = new CandidateSolution(s1);

		CandidateSolution[] parents = new CandidateSolution[2];
		parents[0] = s1;
		parents[1] = s2;

		int[] crossoverPoints = rand.ints(0, s1.getSize()).limit(N).toArray();

		int nextCrossoverPoint = 0, nextCrossoverIndex = 0, currentParent = 0;
		for(int i = 0 ; i < s2.getSize() ; i++){
			if(nextCrossoverPoint == i){
				//ALter parent:
				currentParent = 1 - currentParent;
				if(nextCrossoverIndex < N){
					//Update next crossover index/point:
					nextCrossoverPoint = crossoverPoints[nextCrossoverIndex++];
				}
			}
			offspring.setAllele(i, parents[currentParent].getAllele(i));
		}

		return offspring;
	}

}
