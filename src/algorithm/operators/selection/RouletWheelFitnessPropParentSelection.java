package algorithm.operators.selection;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import graph.Graph;
import algorithm.components.CandidateSolution;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public class RouletWheelFitnessPropParentSelection implements ParentSelector{

	@Override
	public Population select(Population population,Graph g,  FitnessEvaluator fitnessEvaluator) throws UnexpectedException {
		Population parents = new Population();
		Random rand = new Random();
		Map<Integer, Double> fitnessMap = new HashMap<>();
		Map<Integer, Double> top = new HashMap<>();
		
		//Compute sum of all fitnesses:
		double sum = 0;
		for(int i = 0; i < population.getSize() ; i++){
			double fitness = fitnessEvaluator.evaluate(g,population.getIndividual(i));
			fitnessMap.put(i, fitness);
			sum += fitness;
		}
		
		double rangeTop = 0;
		
		//Compute range for each individual, range is proportional to the fitness:
		for(int i = 0; i < population.getSize() ; i++){
			rangeTop += fitnessMap.get(i);
			top.put(i, rangeTop);
		}
		
		for(int i = 0; i < population.getSize() ; i++){
			double r = rand.nextDouble() * sum;
			boolean found = false;
			for(int j = 0; j < population.getSize() ; j++){
				if( r <= top.get(j)){
					CandidateSolution parentCopy = new CandidateSolution(population.getIndividual(j));
					parents.addIndividual(parentCopy);
					found = true;
					break;
				}
			}
			if(!found){
				throw new UnexpectedException("Something went wrong: Did not find a parent with the correct range!");
			}
		}
		
		return parents;
	}

}
