package algorithm.operators.selection;

import graph.Graph;

import java.rmi.UnexpectedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import algorithm.components.CandidateSolution;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public class ElitismSurvivorSelector implements SurvivorSelector{

	private static final int ELITE_SIZE = 20;

	@Override
	public Population select(Population population, Population parents, Graph g, FitnessEvaluator fitnessEvaluator) throws UnexpectedException {
		Population survivors = new Population();
		
		Map<CandidateSolution,Double> fitnessMap = new HashMap<>();
		for(int i = 0; i < parents.getSize() ; i++){
			CandidateSolution p = parents.getIndividual(i);
			CandidateSolution s = population.getIndividual(i);
			double fit = fitnessEvaluator.evaluate(g,p);
			fitnessMap.put(p, fit);
			fit = fitnessEvaluator.evaluate(g,s);
			fitnessMap.put(s, fit);
		}
		
		Set<CandidateSolution> eliteSet = new HashSet<>(); 

		//Save elite solutions:
		while(eliteSet.size() < ELITE_SIZE){
			CandidateSolution s = removeBestCandidate(fitnessMap);
			eliteSet.add(s);
		}

		for(CandidateSolution s : eliteSet){
			survivors.addIndividual(s);
		}
		
		List<CandidateSolution> l = population.asList();
		l.addAll(eliteSet);
		
		Collections.shuffle(l);
		//Choose all others randomly from the population:
		for(CandidateSolution s : l){
			if(survivors.getSize() >= population.getSize()){
				break;
			}
			survivors.addIndividual(s);
		}

		return survivors;
	}

	private CandidateSolution removeBestCandidate(Map<CandidateSolution, Double> fitnessMap) {
		CandidateSolution chosenOne =  
				fitnessMap
				.entrySet()
				.stream()
				.max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		fitnessMap.remove(chosenOne);
		return chosenOne;
	}

}
