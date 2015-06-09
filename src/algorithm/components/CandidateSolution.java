package algorithm.components;

import graph.Graph;

import java.util.Random;

public class CandidateSolution{
	
	private boolean[] candidate;
	private int size;
	
	public CandidateSolution(Graph g){
		size = g.getVertices().size();
		
		candidate = new boolean[size];
		for(int i=0; i<size; i++){
			candidate[i] = false;
		}
	}
	
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
	public CandidateSolution(int size) {
		this.size = size;
		this.candidate = new boolean[this.size];
	}

	public CandidateSolution(int assignment, int size) {
		this.size = size;
		candidate = new boolean[size];
		for(int i=0; i<size; i++){
			int bit = 1 << i;
			candidate[i] = ( (bit & assignment) == 0 ? false : true);
		}
	}

	public int getSize(){
		return this.size;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		
		if(!(o instanceof CandidateSolution)){
			return false;
		}
		
		CandidateSolution other = (CandidateSolution) o;
		
		for(int i = 0; i < this.getSize() ; i++){
			if( this.candidate[i] != other.candidate[i] ){
				return false;
			}
		}
		
		return true;
	}

	public Boolean getAllele(int i) {
		return this.candidate[i];
	}

	public void setAllele(int i, Boolean allele) {
		this.candidate[i] = allele;
	}

}
