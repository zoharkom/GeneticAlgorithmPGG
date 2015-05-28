package algorithm.components;

import graph.Graph;

import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	private ArrayList<CandidateSolution> population;
	private int size;
	
	public Population(){
		this.population = new ArrayList<>();
		//TODO
	}
	
	public Population(Graph graph, int size){
		this.size = size;
		this.population = new ArrayList<>();
		//TODO
	}
	
	public Population(Graph graph, int size, Random rand){
		this.size = size;
		this.population = new ArrayList<>();
		//TODO
	}

	public int getSize() {
		return this.size;
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
	


}
