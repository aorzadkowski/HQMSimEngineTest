
public class Season {
//Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};
	
	private static int[][] records = new int[5][6];
	public static int[][] orderedStandings = new int[5][6];
	private static Game[] seasonGame = new Game[5];
	private static Game[] playoffGame = new Game[7];
	private static double[][] multipleSeasonRecords = new double[6][6];
	public static double[][] multOrderedStandings = new double[6][6];
	public static int[] teamOrder = new int [6];
	private static Team[] playoffTeams = new Team[4];
	private static Team[] finalsTeams = new Team[2];
	private static int[] playoffRecords = new int[4];
	private static int[] finalsRecords = new int[2];

	// For simulating a single season
	public static void seasonSim( Team team1, Team team2, Team team3, Team team4, Team team5, Team team6 ) {
		
		Team[] seasonTeams = new Team[]{team1,team2,team3,team4,team5,team6};
		
		// Simulates the games in the season
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
						}
						else {
							records[2][k]++;
							records[4][k]+= 1;
							records[1][j]++;
							records[4][j]+= 2;
						}
					}
					else {
						if (seasonGame[i].didTeam1Win()) {
							records[0][k]++;
							records[4][k]+= 3;
							records[3][j]++;
						}
						else {
							records[3][k]++; 
							records[0][j]++;
							records[4][j]+= 3;
						}
					}
				}
			}
		}
		
		
		// Orders the rankings by most points to least points
		for( int j = 0; j < 6; j++) {
			int pointsflag = 0;
			for( int i = 0; i < 6; i++) {
				if( records[4][i] > records[4][pointsflag]) pointsflag = i;
			}
			for( int i = 0; i < 5; i++) {
				orderedStandings[i][j] = records[i][pointsflag];
				records[i][pointsflag] = 0;
			}
			teamOrder[j] = pointsflag;
		}
	}
	
	
	// For simulating multiple season
	public static void massSeasonSim( Team team1, Team team2, Team team3, Team team4, Team team5, Team team6, int gamesPlayed ) {
		
		Team[] seasonTeams = new Team[]{team1,team2,team3,team4,team5,team6};
		
		// Simulates the games in multiple seasons
		for( int l = 0; l < gamesPlayed; l++) {
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
							}
							else {
								records[2][k]++;
								records[4][k]+= 1;
								records[1][j]++;
								records[4][j]+= 2;
							}
						}
						else {
							if (seasonGame[i].didTeam1Win()) {
								records[0][k]++;
								records[4][k]+= 3;
								records[3][j]++;
							}
							else {
								records[3][k]++; 
								records[0][j]++;
								records[4][j]+= 3;
							}
						}
					}
				}
			}
		
			for( int j = 0; j < 6; j++) {
				for( int i = 0; i < 5; i++) {
					multipleSeasonRecords[i][j]+= records[i][j];
					records[i][j] = 0;
				}
			}
			
			int seasonWinner = 0;
			for( int i = 0; i < 6; i++) {
				if ( multipleSeasonRecords[4][i] > multipleSeasonRecords[4][seasonWinner]) seasonWinner = i;
			}
			multipleSeasonRecords[5][seasonWinner]++;
			
			// Simulate Playoffs
						
			for( int j = 0; j < 4; j++) {
				int pointsflag = 0;
				for( int i = 0; i < 6; i++) {
					if( multipleSeasonRecords[4][i] > multipleSeasonRecords[4][pointsflag]) pointsflag = i;
				}
				for( int i = 0; i < 4; i++) {
					playoffTeams[i] = seasonTeams[pointsflag];
				}
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
				
			}
			else {
				
			}
		}
		
		// Get averages
		for( int j = 0; j < 6; j++) {
			for( int i = 0; i < 5; i++) {
				multipleSeasonRecords[i][j] = multipleSeasonRecords[i][j] / gamesPlayed;
			}
		}

		// Orders the rankings by most points to least points
		for( int j = 0; j < 6; j++) {
			int pointsflag = 0;
			for( int i = 0; i < 6; i++) {
				if( multipleSeasonRecords[4][i] > multipleSeasonRecords[4][pointsflag]) pointsflag = i;
			}
			for( int i = 0; i < 6; i++) {
				multOrderedStandings[i][j] = multipleSeasonRecords[i][pointsflag];
				multipleSeasonRecords[i][pointsflag] = 0;
			}
			teamOrder[j] = pointsflag;
		}
	}
}