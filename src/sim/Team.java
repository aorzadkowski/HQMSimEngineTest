package sim;

import generation.PlayerFactory;
import hqmdatabase.Player;

public class Team {
	public Player[] forwards;
	public Player[] defencemen;
	public String teamName;
	
	/*
	
	public double[] weightsForO = new double[9];
	public double[] weightsForD = new double[9];
	
	public double offenseTotal;
	public double defenseTotal;
	public double bothTotal;
		
	public double oRatio;
	public double dRatio;
	public double bRatio;
	public double inverseBRatio;
	
	*/
	
	/*
	
	public void initSimData() {
		for (int i = 0; i < weightsForO.length; i++) {
			weightsForO[i] = forwards[0].getStats()[i] * forwards[0].role.getWeights()[i] 		+ forwards[1].getStats()[i] * forwards[1].role.getWeights()[i];
			if (forwards[0].role == forwards[1].role) 	weightsForO[i] *= 0.95;
			weightsForD[i] = defencemen[0].getStats()[i] * defencemen[0].role.getWeights()[i] 	+ defencemen[1].getStats()[i] * defencemen[1].role.getWeights()[i];
			if (defencemen[0].role == defencemen[1].role) weightsForD[i] *= 0.975;
		}
		
	//Only the first 7 stats are needed for offense calculation from forwards.
	for (int i = 0; i < 7; i++) {
		offenseTotal += weightsForO[i];
	}
	//Only the first 6 stats are needed for offense calculation from defencemen.
	for (int i = 0; i < 6; i++) {
		offenseTotal += weightsForD[i];
	}
		
	//Only these three stats are used for defense calculation.
	defenseTotal = weightsForO[7] + weightsForD[6] + weightsForD[7];
		
	//Reliability stat
	bothTotal = weightsForO[8] + weightsForD[8];
		
	oRatio = offenseTotal / SimEngine.maxOffense;
	dRatio = defenseTotal / SimEngine.maxDefense;
	bRatio = bothTotal / SimEngine.maxBoth;
		
	inverseBRatio = 1 - bRatio;
	
	*/
	
	// New stuff for new SimEngine calculator
		
	public double[][] weightsForPlayer = new double[20][4];
	public double[] weightForG = new double[7];
	
	public double[] oRatio = new double[4];
	public double[] dRatio = new double[4];
	public double[] bRatio = new double[4];
	public double[] inverseBRatio = new double[4];
	
	public double[] offenseTotal = new double[4];
	public double[] defenseTotal = new double[4];
	public double[] bothTotal = new double[4];
	

		
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public Team(String teamName, Player f1, Player f2, Player d1, Player d2) {
		this.teamName = teamName;
		forwards = new Player[] {f1, f2};
		defencemen = new Player[] {d1, d2};
		
		initSimData();
	}
	
	public void generateTeam(PlayerFactory factory) {
		forwards = new Player[] {factory.generateRandomOMan(), factory.generateRandomOMan()};
		defencemen = new Player[] {factory.generateRandomDMan(), factory.generateRandomDMan()};
	}
	
	public String toString() {
		return teamName + ": " + forwards[0].getName() + ", " + forwards[1].getName() + ", " + defencemen[0].getName() + ", " + defencemen[1].getName();
	}
	
	public void initSimData() {
		for (int i = 0; i < weightsForPlayer.length; i++) {
			weightsForPlayer[i][0] = forwards[0].getStats()[i] * forwards[0].role.getWeights()[i];
			weightsForPlayer[i][1] = forwards[1].getStats()[i] * forwards[1].role.getWeights()[i];
			if (forwards[0].role == forwards[1].role) { 	weightsForPlayer[i][0] *= 0.95; weightsForPlayer[i][1] *= 0.95;}
			weightsForPlayer[i][2] = defencemen[0].getStats()[i] * defencemen[0].role.getWeights()[i];
			weightsForPlayer[i][3] = defencemen[1].getStats()[i] * defencemen[1].role.getWeights()[i];
			if (defencemen[0].role == defencemen[1].role){ weightsForPlayer[i][2] *= 0.975; weightsForPlayer[i][3] *= 0.975;}
		}
	
	// Adds the stat categories for each player
	for ( int j = 0; j < 4; j++) {
		for ( int i = 0; i < 20; i++) {
			if ( i == 12 || i == 15 || i == 19) bothTotal[j] += weightsForPlayer[17][j];
			if ( i == 13 || i == 14 || i == 16 || i == 18) defenseTotal[j] += weightsForPlayer[17][j];
			else offenseTotal[j] += weightsForPlayer[i][j];
		}
	}
	
	for ( int i = 0; i < 4; i++) {
		oRatio[i] = offenseTotal[i] / SimEngine.maxOffense;
		dRatio[i] = defenseTotal[i] / SimEngine.maxDefense;
		bRatio[i] = bothTotal[i] / SimEngine.maxBoth;
		inverseBRatio[i] = 1 - bRatio[i];
	}
	
	}
}

