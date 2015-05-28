package simulation;

import java.rmi.UnexpectedException;
import java.util.Random;

import algorithm.PGGAlgorithm;
import algorithm.SimpleGeneticAlgorithm;
import algorithm.components.CandidateSolution;
import algorithm.config.SGAConfiguration;
import graph.Graph;
import graph.gen.ErdosReniyGraphGenerator;
import graph.gen.ScaleFreeGraphGenerator;



public class Simulator {
	public final static double ACTION_COST= 0.4;
	
	private final static long SEED = 0;
	private final static int REPEAT_COUNT = 50;
	private final static int MIN_NUM_PLAYERS = 10;
	private final static int MAX_NUM_PLAYERS = 10;
	private final static int NUM_PLAYERS_STEP = 2;
	private final static double ER_EDGE_PROB = 0.4;
	private final static double SF_EDGE_PROB = 0.1;
	private final static int SF_M0 = 4;
	private final static int SF_M = 1;
	
	
	public static void main(String[] args){
		
		//Configure algorithm:
		SimpleGeneticAlgorithm alg = new SimpleGeneticAlgorithm();
		SGAConfiguration conf = SGAConfiguration.generateDefaultSGAConfiguration();
		alg.algorithmConfig(conf);
		
		//Run experiments:
		Random rand = new Random(SEED);
		
		for(int n = MIN_NUM_PLAYERS ; n <= MAX_NUM_PLAYERS; n += NUM_PLAYERS_STEP){
			
			System.out.println("\n-----------------------");
			System.out.println("Number of players: "+ n);
			System.out.println("-----------------------\n");
			
			for(int i = 0 ; i < REPEAT_COUNT; i++){
				System.out.println("Generating problem number: "+ i);
				
				//Generate a problem instance (i.e., 1 graph):
				Graph g = new ErdosReniyGraphGenerator(ER_EDGE_PROB).generate(n, rand);
//				Graph g = new ScaleFreeGraphGenerator(SF_M, SF_M0, SF_EDGE_PROB).generate(n, rand);

				//Solve problem using SGA:
				CandidateSolution sol = null;
				try {
					sol = alg.findSolution(g);
				} catch (UnexpectedException e) {
					e.printStackTrace();
				}
				
				//Print solution: TODO --> we better print to a csv file...
				System.out.println("Best solution: " + sol.toString());
			}
		}

	}

}
