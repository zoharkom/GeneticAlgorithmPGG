package algorithm.operators;

import graph.Graph;
import graph.Vertex;
import graph.util.GraphUtils;
import algorithm.components.CandidateSolution;

public class MaxSocialWelfareWithSidePaymentsImprover implements SolutionImprover {

	@Override
	public CandidateSolution improveSolution(Graph g, CandidateSolution s) {
		CandidateSolution improvedSolution = new CandidateSolution(s);
		
		for(Vertex v : g.getVertices()){
			if(GraphUtils.isFF(g, improvedSolution, v)){
				improvedSolution.setAllele(v.getId(), true);
				continue;
			}

			if(GraphUtils.isTT(g, improvedSolution, v)){
				if(!GraphUtils.hasDependents(g, improvedSolution, v)){
					improvedSolution.setAllele(v.getId(), false);
					continue;
				}
			}

		}

		return improvedSolution;
	}



}
