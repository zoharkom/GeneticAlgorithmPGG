package simulation;

import java.util.Random;

import algorithm.BestResponseAlgorithm;
import algorithm.BruteForce;
import algorithm.SimpleGeneticAlgorithm;
import algorithm.components.CandidateSolution;
import algorithm.config.SGAConfiguration;
import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.fitness.MaxSocialWelfareFitnessEvaluator;
import graph.Graph;
import graph.gen.ErdosReniyGraphGenerator;
import graph.gen.ScaleFreeGraphGenerator;
import graph.util.GDFWriter;



public class Simulator {
	public final static double ACTION_COST= 0.4;
	
	private final static long SEED = 0;
	private final static int REPEAT_COUNT = 50;
	private final static int MIN_NUM_PLAYERS = 500;
	private final static int MAX_NUM_PLAYERS = 500;
	private final static int NUM_PLAYERS_STEP = 2;
	private final static double ER_EDGE_PROB = 0.3;
	private final static double SF_EDGE_PROB = 0.1;
	private final static int SF_M0 = 4;
	private final static int SF_M = 1;
	
	
	public static void main(String[] args){
		
		//Configure algorithm:
		SimpleGeneticAlgorithm genAlg = new SimpleGeneticAlgorithm();
		SGAConfiguration conf = SGAConfiguration.generateDefaultSGAConfiguration();
		genAlg.algorithmConfig(conf);
		
		SimpleGeneticAlgorithm genAlg2 = new SimpleGeneticAlgorithm();
		SGAConfiguration conf2 = SGAConfiguration.generateDefaultSGAConfiguration1();
		genAlg2.algorithmConfig(conf2);
		
		
		//Run experiments:
		Random rand = new Random(SEED);
		
		for(int n = MIN_NUM_PLAYERS ; n <= MAX_NUM_PLAYERS; n += NUM_PLAYERS_STEP){
			
			System.out.println("\n-----------------------");
			System.out.println("Number of players: "+ n);
			System.out.println("-----------------------\n");
			
			for(int i = 0 ; i < REPEAT_COUNT; i++){
				System.out.println("Generating problem number: "+ i);
				
				//Generate a problem instance (i.e., 1 graph):
//				Graph g1 = new ErdosReniyGraphGenerator(ER_EDGE_PROB).generate(n, rand);
				Graph g2 = new ScaleFreeGraphGenerator(SF_M, SF_M0, SF_EDGE_PROB).generate(n, rand);

				//Solve problem using SGA:
				CandidateSolution sol1 = null,sol2 = null, sol3 = null, sol4 = null;
				long t1,t2,t3,t4;
				try {
					t1 = System.currentTimeMillis();
					sol1 = genAlg.findSolution(g2);
					t1 = System.currentTimeMillis() - t1;
					t2 = System.currentTimeMillis();
					sol2 = new BestResponseAlgorithm(g2).findSolution(g2);
					t2 = System.currentTimeMillis() - t2;
//					t3 = System.currentTimeMillis();
//					sol3 = new BruteForce().findSolution(g2);
//					t3 = System.currentTimeMillis() - t3;
					t4 = System.currentTimeMillis();
					sol4 = genAlg2.findSolution(g2);
					t4 = System.currentTimeMillis() - t4;
					
					GDFWriter.write(g2, sol1, "/home/zohar/Desktop/PGGOutput/out-ga-"+i+".gdf");
					GDFWriter.write(g2, sol2, "/home/zohar/Desktop/PGGOutput/out-best-response-"+i+".gdf");
//					GDFWriter.write(g2, sol3, "/home/zohar/Desktop/PGGOutput/out-brute-force-"+i+".gdf");
					GDFWriter.write(g2, sol4, "/home/zohar/Desktop/PGGOutput/out-ga-roulet-"+i+".gdf");
					
					FitnessEvaluator e = new MaxSocialWelfareFitnessEvaluator();
					
					System.out.println("Social Welfare:");
					System.out.println("Genetic algorithm tournament: " + sol1.getFitness() + " (" + t1 + ")");
					System.out.println("Best response: " + e.evaluate(g2, sol2) + " (" + t2 + ")");
//					System.out.println("Brute force: " + e.evaluate(g2, sol3) + " (" + t3 + ")");
					System.out.println("Genetic algorithm roulet: " + e.evaluate(g2, sol4) + " (" + t4 + ")");
					System.out.println("-----------------------------------------------------");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}

	}

}
