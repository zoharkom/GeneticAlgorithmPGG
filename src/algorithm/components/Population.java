package algorithm.components;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
	
	private ArrayList<CandidateSolution> population;
	
	public Population(){
		this.population = new ArrayList<>();
	}
	
	public Population(Graph graph, int size, Random rand){
		this.population = new ArrayList<>();
		for(int i = 0; i < size ; i++){
			CandidateSolution s = new CandidateSolution(graph, rand);
			this.addIndividual(s);
		}
	}

	public int getSize() {
		return this.population.size();
	}

	public CandidateSolution getIndividual(int i) {
		return this.population.get(i);
	}

	public void setIndividual(int i, CandidateSolution s) {
		this.population.set(i, s);
	}
	
	public void addIndividual(CandidateSolution s) {
		this.population.add(s);
	}

	public List<CandidateSolution> asList() {
		return this.population;
	}

}
