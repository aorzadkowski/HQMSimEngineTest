package sim;

import hqmdatabase.Player;
import main.Options;

public class Team {
	public Player[] forwards;
	public Player[] defencemen;
	public Player goalie;
	public Player[] teamPlayers;
	public String teamName;
	private double[][] weightsForPlayer = new double[20][4];
	public double[] oRatio = new double[4];
	public double[] playerDRatio = new double[4];
	public double[] playerBRatio = new double[4];
	public double[] playerInvBRatio = new double[4];
	public double teamDRatio = 0; // Doesn't include goalie
	public double teamBRatio = 0; // Doesn't include goalie
	public double teamInvBRatio = 0; // Doesn't include goalie
	public double goalieDRatio = 0;
	public double invGoalieBRatio = 0;
	private double teamDRoleRatio = 0;
	private double teamBRoleRatio = 0;
	private double[] offenseTotal = new double[4];
	private double[] defenseTotal = new double[4];
	private double[] bothTotal = new double[4];
	private double teamDefenseTotal = 0;
	private double teamBothTotal = 0;
	private double goalieTotal = 0;
	
		
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public Team(String teamName, Player f1, Player f2, Player d1, Player d2, Player g1) {
		this.teamName = teamName;
		forwards = new Player[] {f1, f2};
		defencemen = new Player[] {d1, d2};
		goalie = g1;
		teamPlayers = new Player[] {f1, f2, d1, d2, g1};
		
		initSimData( f1, f2, d1, d2, g1);
	}
	
	public Team(Team other) {
		this.teamName = new String(other.teamName);
		
		forwards = new Player[] { new Player(other.teamPlayers[0]), new Player(other.teamPlayers[1]) };
		defencemen = new Player[] { new Player(other.teamPlayers[2]), new Player(other.teamPlayers[3]) };
		goalie = new Player(other.goalie);
		
		teamPlayers = new Player[] { forwards[0], forwards[1], defencemen[0], defencemen[1], goalie };
	}
	
	public String toString() {
		return teamName + ": " + forwards[0].getName() + ", " + forwards[1].getName() + ", " + defencemen[0].getName() + ", " + defencemen[1].getName();
	}
	
	public void initSimData( Player f1, Player f2, Player d1, Player d2, Player g1) {
		for (int i = 0; i < weightsForPlayer.length; i++) {
			weightsForPlayer[i][0] = forwards[0].getStats()[i] * forwards[0].role.getWeights()[i];
			weightsForPlayer[i][1] = forwards[1].getStats()[i] * forwards[1].role.getWeights()[i];
			if (forwards[0].role == forwards[1].role) { 	weightsForPlayer[i][0] *= 0.95; weightsForPlayer[i][1] *= 0.95;}
			weightsForPlayer[i][2] = defencemen[0].getStats()[i] * defencemen[0].role.getWeights()[i];
			weightsForPlayer[i][3] = defencemen[1].getStats()[i] * defencemen[1].role.getWeights()[i];
			if (defencemen[0].role == defencemen[1].role){ weightsForPlayer[i][2] *= 0.975; weightsForPlayer[i][3] *= 0.975;}
		}
		for (int i = 0; i < 5; i++) {
			goalieTotal += goalie.getStats()[i] * goalie.role.getWeights()[i];

			if (Options.debug) System.out.println(teamName + " in iteration " + (i + 1) + " goalieTotal=" + goalie.getStats()[i] + " * " + goalie.role.getWeights()[i] + " = " + goalieTotal);
		}
	
	// Adds the stat categories for each player
	for ( int j = 0; j < 4; j++) {
		for ( int i = 0; i < 20; i++) {
			if ( i == 19) { bothTotal[j] += weightsForPlayer[i][j]; teamBothTotal += weightsForPlayer[i][j]; }
			else if ( i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 18) { defenseTotal[j] += weightsForPlayer[i][j]; teamDefenseTotal += weightsForPlayer[i][j]; }
			else offenseTotal[j] += weightsForPlayer[i][j];
		}
	}
	

	for ( int i = 0; i < 4; i++) {
		double[] roleRatio = new double[3];
		roleRatio = teamPlayers[i].role.getMax();	
		teamDRoleRatio += roleRatio[1];
		teamBRoleRatio += roleRatio[2];
		
		oRatio[i] = offenseTotal[i] / roleRatio[0];
		playerDRatio[i] = defenseTotal[i] / roleRatio[1];
		playerBRatio[i] = bothTotal[i] / roleRatio[2];
		playerInvBRatio[i] = 1 - playerBRatio[i];
	}
	
	teamDRatio = teamDefenseTotal / teamDRoleRatio;
	teamBRatio = teamBothTotal / teamBRoleRatio;
	teamInvBRatio = 1 - teamBRatio;
	
	goalieDRatio = goalieTotal / teamPlayers[4].role.getMax()[1];
	invGoalieBRatio = 1 - ( goalie.getStats()[7] * goalie.role.getWeights()[2] ) / goalie.role.getMax()[2];
	if (Options.debug) System.out.println(teamName + " goalieDRatio=" + goalieTotal + " / " + teamPlayers[4].role.getMax()[1] + " = " + goalieDRatio
			+ "\ninvGoalieBRatio=" + invGoalieBRatio);
	
	}
}

