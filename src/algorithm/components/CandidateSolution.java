package algorithm.components;

import graph.Graph;
import java.util.Random;

public class CandidateSolution {
	
	private boolean[] candidate;
	private int size;
	
	public CandidateSolution(Graph g, Random rand){
		size = g.getVertices().size();
		
		candidate = new boolean[size];
		for(int i=0; i<size; i++){
			candidate[i] = rand.nextBoolean();
		}
	}
	
	public CandidateSolution(CandidateSolution s) {
		this.size = s.size;
		this.candidate = new boolean[this.size];
		for(int i=0 ; i < this.size ; i++){
			this.candidate[i] = s.candidate[i];
		}
	}

	public int getSize(){
		return this.size;
	}

	public boolean getAllele(int i) {
		return this.candidate[i];
	}

	public void setAllele(int i, boolean allele) {
		this.candidate[i] = allele;
		
	}

}
