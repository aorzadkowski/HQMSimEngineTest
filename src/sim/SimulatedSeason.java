package sim;

public class SimulatedSeason {
//Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};
	
	private static int[][] records = new int[5][6];
	public static int[][] orderedStandings = new int[5][6];
	private static Game[] seasonGame = new Game[5];
	private static Game[] playoffGame = new Game[7];
	private static double[][] multipleSeasonRecords = new double[7][6];
	public static double[][] multOrderedStandings = new double[7][6];
	public static int[] teamOrder = new int [6];
	private static Team[] playoffTeams = new Team[4];
	private static Team[] finalsTeams = new Team[2];
	private static int[] playoffRecords = new int[4];
	private static int[] finalsRecords = new int[2];
	private static int[][] teamStats = new int[4][6];
	private static double[][] multiTeamStats = new double[4][6];
	public static double[][] multiOrderedTeamStats = new double[4][6];

	public static void massSeasonSim( Team team1, Team team2, Team team3, Team team4, Team team5, Team team6, int gamesPlayed ) {
		
		Team[] seasonTeams = new Team[]{team1,team2,team3,team4,team5,team6};
		
		// Simulates the games in multiple seasons
		for( int l = 0; l < gamesPlayed; l++) {
			records = new int[5][6];
			playoffRecords = new int[4];
			finalsRecords = new int[2];
			for( int k = 0; k < 5; k++) {
				for( int j = k + 1; j < 6; j++) {
					for( int i = 0; i < 5; i++) {
						seasonGame[i] = new Game (seasonTeams[k], seasonTeams[j]);	
						SimEngine.simulateGame(seasonGame[i]);
		
						if (seasonGame[i].isGameOT()) {
							if (seasonGame[i].didTeam1Win()) {
								records[1][k]++;
								records[4][k]+= 2;
								records[2][j]++;
								records[4][j]+= 1;
								teamStats[0][k] += seasonGame[i].winningTeamGoals();
								teamStats[0][j] += seasonGame[i].losingTeamGoals();
								teamStats[1][k] += seasonGame[i].winningTeamAssists();
								teamStats[1][j] += seasonGame[i].losingTeamAssists();
								teamStats[2][k] += seasonGame[i].losingTeamGoals();
								teamStats[2][j] += seasonGame[i].winningTeamGoals();
								teamStats[3][k] += seasonGame[i].winningTeamSaves();
								teamStats[3][j] += seasonGame[i].losingTeamSaves();
							}
							else {
								records[2][k]++;
								records[4][k]+= 1;
								records[1][j]++;
								records[4][j]+= 2;
								teamStats[0][k] += seasonGame[i].losingTeamGoals();
								teamStats[0][j] += seasonGame[i].winningTeamGoals();
								teamStats[1][k] += seasonGame[i].losingTeamAssists();
								teamStats[1][j] += seasonGame[i].winningTeamAssists();
								teamStats[2][k] += seasonGame[i].winningTeamGoals();
								teamStats[2][j] += seasonGame[i].losingTeamGoals();
								teamStats[3][k] += seasonGame[i].losingTeamSaves();
								teamStats[3][j] += seasonGame[i].winningTeamSaves();
							}
						}
						else {
							if (seasonGame[i].didTeam1Win()) {
								records[0][k]++;
								records[4][k]+= 3;
								records[3][j]++;
								teamStats[0][k] += seasonGame[i].winningTeamGoals();
								teamStats[0][j] += seasonGame[i].losingTeamGoals();
								teamStats[1][k] += seasonGame[i].winningTeamAssists();
								teamStats[1][j] += seasonGame[i].losingTeamAssists();
								teamStats[2][k] += seasonGame[i].losingTeamGoals();
								teamStats[2][j] += seasonGame[i].winningTeamGoals();
								teamStats[3][k] += seasonGame[i].winningTeamSaves();
								teamStats[3][j] += seasonGame[i].losingTeamSaves();
							}
							else {
								records[3][k]++; 
								records[0][j]++;
								records[4][j]+= 3;
								teamStats[0][k] += seasonGame[i].losingTeamGoals();
								teamStats[0][j] += seasonGame[i].winningTeamGoals();
								teamStats[1][k] += seasonGame[i].losingTeamAssists();
								teamStats[1][j] += seasonGame[i].winningTeamAssists();
								teamStats[2][k] += seasonGame[i].winningTeamGoals();
								teamStats[2][j] += seasonGame[i].losingTeamGoals();
								teamStats[3][k] += seasonGame[i].losingTeamSaves();
								teamStats[3][j] += seasonGame[i].winningTeamSaves();
							}
						}
					}
				}
			}
		
			for( int j = 0; j < 6; j++) {
				for( int i = 0; i < 5; i++) {
					multipleSeasonRecords[i][j]+= records[i][j];
				}
			}
			
			int seasonWinner = 0;
			for( int i = 0; i < 6; i++) {
				if ( records[4][i] > records[4][seasonWinner]) seasonWinner = i;
			}
			multipleSeasonRecords[5][seasonWinner]++;
			
			// Simulate Playoffs
						
			int[] playoffTeamIndex = new int[4];
			
			for( int j = 0; j < 4; j++) {
				int pointsflag = 0;
				for( int i = 0; i < 6; i++) {
					if( records[4][i] > records[4][pointsflag]) pointsflag = i;
				}
				playoffTeams[j] = seasonTeams[pointsflag];
				records[4][pointsflag] = 0;
				playoffTeamIndex[j] = pointsflag;
			}
			

			for( int i = 0; i < 7; i++) {
				playoffGame[i] = new Game ( playoffTeams[0], playoffTeams[3]);
				SimEngine.simulateGame(playoffGame[i]);
				
				if( playoffGame[i].didTeam1Win()) {
					playoffRecords[0]++;
				}
				else {
					playoffRecords[3]++;
				}
			}
			if( playoffRecords[0] > playoffRecords[3]) {
				finalsTeams[0] = playoffTeams[0];
			}
			else {
				finalsTeams[0] = playoffTeams[3];
				playoffTeamIndex[0] = playoffTeamIndex[3];
			}
			
			for( int i = 0; i < 7; i++) {
				playoffGame[i] = new Game ( playoffTeams[1], playoffTeams[2]);
				SimEngine.simulateGame(playoffGame[i]);
				
				if( playoffGame[i].didTeam1Win()) {
					playoffRecords[1]++;
				}
				else {
					playoffRecords[2]++;
				}
			}
			if( playoffRecords[1] > playoffRecords[2]) {
				finalsTeams[1] = playoffTeams[1];
			}
			else {
				finalsTeams[1] = playoffTeams[2];
				playoffTeamIndex[1] = playoffTeamIndex[2];
			}
			
			for( int i = 0; i < 7; i++) {
				playoffGame[i] = new Game ( finalsTeams[0], finalsTeams[1]);
				SimEngine.simulateGame(playoffGame[i]);
				
				if( playoffGame[i].didTeam1Win()) {
					finalsRecords[0]++;
				}
				else {
					finalsRecords[1]++;
				}
			}
			if( finalsRecords[0] > finalsRecords[1]) {
				multipleSeasonRecords[6][playoffTeamIndex[0]]++;
			}
			else {
				multipleSeasonRecords[6][playoffTeamIndex[1]]++;
			}
		}
		
		// Get averages
		for( int j = 0; j < 6; j++) {
			for( int i = 0; i < 5; i++) {
				multipleSeasonRecords[i][j] = multipleSeasonRecords[i][j] / gamesPlayed;
				multiTeamStats[0][j] = (double)teamStats[0][j] / (gamesPlayed * 25);
				multiTeamStats[1][j] = (double)teamStats[1][j] / (gamesPlayed * 25);
				multiTeamStats[2][j] = (double)teamStats[2][j] / (gamesPlayed * 25);
				multiTeamStats[3][j] = (double)teamStats[3][j] / (gamesPlayed * 25);
			}
		}
		
		for( int j = 0; j < 6; j++) {
			for( int i = 5; i < 7; i++) {
				multipleSeasonRecords[i][j] = (multipleSeasonRecords[i][j] * 100) / gamesPlayed;
			}
		}

		// Orders the rankings by most points to least points
		for( int j = 0; j < 6; j++) {
			int pointsflag = 0;
			for( int i = 0; i < 6; i++) {
				if( multipleSeasonRecords[4][i] > multipleSeasonRecords[4][pointsflag]) pointsflag = i;
			}
			for( int i = 0; i < 7; i++) {
				multOrderedStandings[i][j] = multipleSeasonRecords[i][pointsflag];
				multiOrderedTeamStats[0][j] = multiTeamStats[0][pointsflag];
				multiOrderedTeamStats[1][j] = multiTeamStats[1][pointsflag];
				multiOrderedTeamStats[2][j] = multiTeamStats[2][pointsflag];
				multiOrderedTeamStats[3][j] = multiTeamStats[3][pointsflag];
				multipleSeasonRecords[i][pointsflag] = 0;
			}
			teamOrder[j] = pointsflag;
		}
	}
}
