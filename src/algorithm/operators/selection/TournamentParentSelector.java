package algorithm.operators.selection;

import graph.Graph;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.components.CandidateSolution;
import algorithm.components.Population;
import algorithm.operators.fitness.FitnessEvaluator;

public class TournamentParentSelector implements ParentSelector {

	private static final long TOURNAMENT_SIZE = 5;

	@Override
	public Population select(Population population, Graph g,
			FitnessEvaluator fitnessEvaluator) throws UnexpectedException {
		Population parents = new Population();
		Random rand = new Random();
		Map<Integer, Double> fitnessMap = new HashMap<>();

		// Compute all fitnesses:
		for (int i = 0; i < population.getSize(); i++) {
			double fitness = fitnessEvaluator.evaluate(g,
					population.getIndividual(i));
			fitnessMap.put(i, fitness);
		}

		for (int i = 0; i < population.getSize(); i++) {

			Integer winner = rand.ints(0, population.getSize())
					.distinct()
					.limit(TOURNAMENT_SIZE)
					.boxed()
					.max((a, b) -> {
						return Double.compare(fitnessMap.get(a), fitnessMap.get(b));
					}).get();
			parents.addIndividual(population.getIndividual(winner));
		}

		return parents;
	}

}
