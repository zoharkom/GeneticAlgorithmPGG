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
	
	public int getSize(){
		return this.size;
	}

}
