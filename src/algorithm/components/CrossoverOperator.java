package algorithm.components;

public interface CrossoverOperator {
	public CandidateSolution doCrossover(CandidateSolution s1, CandidateSolution s2);
}
