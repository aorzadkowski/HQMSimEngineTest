package sim;

import hqmdatabase.Player;

public class SimEngine {
	
	private static int[] team1Goals = new int[4];
	private static int[] team2Goals = new int[4];
	private static int[] team1Blocks = new int[4];
	private static int[] team2Blocks = new int[4];
	private static int[] team1Points = new int[4];
	private static int[] team2Points = new int[4];
	public static int[][] team1Stats = new int[2][5];
	public static int[][] team2Stats = new int[2][5];
	public static double[][] team1MultiStats = new double[2][5];
	public static double[][] team2MultiStats = new double[2][5];
	public static double[] probArray = new double[5];
	public static double[] goalieAssists = new double[2];
	static int team1Total = 0;
	static int team2Total = 0;
	
	public static void simulateGame(Game game) {

		team1Stats = new int[2][5]; // Resets individual game stats
		team2Stats = new int[2][5]; // Resets individual game stats
		team1Total = 0;
		team2Total = 0;
		
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		int[][] boxScore = new int[3][2];
		
		boxScore[0] = getNetScoreInPeriod(team1, team2);
		boxScore[1] = getNetScoreInPeriod(team1, team2);
		boxScore[2] = getNetScoreInPeriod(team1, team2);

		
		int team1Score = (boxScore[0][0] + boxScore[1][0] + boxScore[2][0]);
		int team2Score = (boxScore[0][1] + boxScore[1][1] + boxScore[2][1]);
		
		
		if (team1Score == team2Score) {
			int team1OTScore = 0;
			int team2OTScore = 0;
			int otCount = 0;
			
			while (team1OTScore == team2OTScore) {
				int[] otPeriod = getNetScoreInPeriod(team1, team2);
			
				team1OTScore = otPeriod[0];
				team2OTScore = otPeriod[1];
		
				/*
				 
				 8.8.17 Removing code below (sudden death) until we figure out what to do with stats during the OT period
				
				if (team1OTScore > team2OTScore) {
					team1OTScore = 1;
					team2OTScore = 0;
				}
				else if (team2OTScore > team1OTScore) {
					team2OTScore = 1;
					team1OTScore = 0;
				} 
				else if (team1OTScore == team2OTScore && team1OTScore != 0) {
					team1OTScore = 0;
					team2OTScore = 0;
				}
				
				*/
				
				
				otCount += 1;
			}
			
			int[][] newBoxScore = new int[3 + otCount][2];
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
					newBoxScore[i][j] = boxScore[i][j];
				}
			}
			
			for (int i = 3; i < 3 + otCount; i++) {
				for (int j = 0; j < 2; j++) {
					newBoxScore[i][j] = 0;
				}
			}
			
			newBoxScore[2+otCount][0] = team1OTScore;
			newBoxScore[2+otCount][1] = team2OTScore;
			
			boxScore = newBoxScore;
		}
		
		for ( int i = 0; i < 4; i++) {
			team1MultiStats[0][i] += team1Stats[0][i];
			team1MultiStats[1][i] += team1Stats[1][i];
			team2MultiStats[0][i] += team2Stats[0][i];
			team2MultiStats[1][i] += team2Stats[1][i];
		}
		team1MultiStats[1][4] += team1Stats[1][4];
		team2MultiStats[1][4] += team2Stats[1][4];
		
		game.setScore(boxScore);

	}
	
	public static int[] getNetScoreInPeriod(Team team1, Team team2) {
		team1Total = 0;
		team2Total = 0;
		
		for ( int i = 0; i < 4; i++ ) {
			team1Goals[i] = getScoreInPeriod(team1, i);
			team1Blocks[i] = getBlocksInPeriod(team1);
		
			team2Goals[i] = getScoreInPeriod(team2, i);
			team2Blocks[i] = getBlocksInPeriod(team2);
		}
		
		for ( int i = 0; i < 4; i++ ) {
			team1Points[i] = team1Goals[i] + team2Blocks[i];
		    if (team1Points[i] > 0) {
				team2Stats[1][4] += team1Points[i];
	    		team1Points[i] = calcSave(team2, team1.teamPlayers[i], team2.teamPlayers[4], team1Points[i], 2);
			}
			if (team1Points[i] < 0) team1Points[i] = 0;
			team1Stats[0][i] += team1Points[i];
			team1Total += team1Points[i];
			
			if ( team1Points[i] > 0) calcApple(team1, team1Points[i], i, 1);
		
			team2Points[i] = team2Goals[i] + team1Blocks[i];
			if (team2Points[i] > 0) {
				team1Stats[1][4] += team2Points[i];
		    	team2Points[i] = calcSave(team1, team2.teamPlayers[i], team1.teamPlayers[4], team2Points[i], 1);
			}
			if (team2Points[i] < 0) team2Points[i] = 0;
			team2Stats[0][i] += team2Points[i];
			team2Total += team2Points[i];
			
			if ( team2Points[i] > 0) calcApple(team2, team2Points[i], i, 2);
			
		}

		return new int[] {team1Total, team2Total};
	}
	
	private static int getScoreInPeriod(Team team, int player) {
		double randomNumb = Math.random();
		int currentScore = 0;
			if (randomNumb <= team.oRatio[player]) {
				currentScore += 1;
				currentScore += getScoreInPeriod(team, 1, player);
			}
		return currentScore;
	}
		
	private static int getScoreInPeriod(Team team, int score, int player) {
		double probability = team.oRatio[player] - ((0.01 + team.playerInvBRatio[player]) * team.oRatio[player] * (score));
		if (Math.random() <= probability) {
			return 1 + getScoreInPeriod(team, score + 1, player);
		}
		return 0;
	}
	
	private static int getBlocksInPeriod(Team team ) {
		double randomNumb = Math.random();
		int currentDefCont = 0;
		if (randomNumb <= team.teamDRatio) {
			currentDefCont -= 1;
			currentDefCont += getBlocksInPeriod(team, 1);
		}
		return currentDefCont;
	}
	
	private static int getBlocksInPeriod(Team team, int score ) {
		double probability = team.teamDRatio - ((0.01 + team.teamInvBRatio) * team.teamDRatio * (score));
		if (Math.random() <= probability) {
			return (-1) - getBlocksInPeriod(team, score + 1);
		}
		return 0;
	}
	
	public static int calcSave( Team goalieTeam, Player shooter, Player goalie, int goals, int goalieTeamNum ) {
		double[] weights;
		int shooterStatUsed;
		double goalieStatUsed;
		double difference;
		double newGoalieDRatio;
		int saves = 0;
		int newGoals = 0;


		for ( int i = 0; i < goals; i++ ) {
			if( shooter.position.toString() == "C" || shooter.position.toString() == "LW" ) weights = new double[] {0.05,0.25,1}; // 5% long shot 20% point shot 75% wrist shot
			else weights = new double[] {0.05,0.8,1}; // 5% long shot 75% point shot 20% wrist shot
			double prob = Math.random();
			if ( prob <= weights[0]) {
				shooterStatUsed = shooter.stats[1];
				goalieStatUsed = goalie.stats[0];
			}
			else if ( prob <= weights[1]) {
				shooterStatUsed = shooter.stats[2];
				goalieStatUsed = goalie.stats[1];
			}
			else {
				shooterStatUsed = shooter.stats[3];
				goalieStatUsed = (double)(goalie.stats[1] + goalie.stats[2])/2; // Average of wrist and breakaway
			}
			difference = goalieStatUsed - shooterStatUsed;
			newGoalieDRatio = goalieTeam.goalieDRatio + (0.05 * difference);
			//System.out.println(goalieTeam.goalieDRatio + "\t" + difference + "\t" + newGoalieDRatio + "\t" + shooter.getName() + "\t" + shooterStatUsed + "\t" + goalieStatUsed + "\t" + prob);
			if ( newGoalieDRatio > 0.90) newGoalieDRatio = 0.9;
			if ( newGoalieDRatio < 0.10) newGoalieDRatio = 0.1;
			//System.out.println(goalieTeam.goalieDRatio + "\t" + difference + "\t" + newGoalieDRatio + "\n");
			if( Math.random() < newGoalieDRatio ) saves++;
			else newGoals++;
		}

		if ( saves > 0 && goalieTeamNum == 1 ) {
			team1MultiStats[0][4] += saves;
		}
		else if( saves > 0 && goalieTeamNum == 2 ) {
			team2MultiStats[0][4] += saves;
		}

		return newGoals;
	}
	
	public static void calcApple( Team team, int goals, int player, int teamNum ) {
		for ( int i = 0; i < goals; i++ ) {
			double[] playerPassing = {0,0,0,0,0};
			int passingTotal = 0;
			probArray = new double[]{0.10,0.10,0.10,0.10,0.10,0.10};
			
			int appleFlag = 0;
			
			for ( int j = 0; j < 4; j++ ) {
				if( j != player ) playerPassing[j] = team.teamPlayers[j].stats[4] + team.teamPlayers[j].stats[5] + team.teamPlayers[j].stats[6];
				passingTotal += playerPassing[j];
			}
			
			playerPassing[4] = team.teamPlayers[4].stats[6];
			passingTotal += playerPassing[4];
			
			for ( int j = 0; j < 5; j++) {
				playerPassing[j] =  playerPassing[j] / passingTotal;
				if ( j == player ) probArray[j+1] = probArray[j];
				else	probArray[j+1] = probArray[j] + playerPassing[j] - 0.025;
			}
			
			double prob = Math.random();
			for ( int j = 0; j < 6; j++) {
				if( j != (player + 1) ) {
					if( prob < probArray[j] && j != 0 && appleFlag == 0 ) {
						if ( teamNum == 1 && j != 5) {
							team1Stats[1][j-1]++;
							appleFlag = 1;
						}
						else if ( j == 5) {
							goalieAssists[teamNum-1]++;
							appleFlag = 1;
						}
						else {
							team2Stats[1][j-1]++;
							appleFlag = 1;
						}
					}
				}
			}
		}
	}
}
