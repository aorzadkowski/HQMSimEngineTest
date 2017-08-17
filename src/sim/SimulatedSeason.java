package sim;

public class SimulatedSeason {
//Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};
	
	private Team team1;
	private Team team2;
	private Team team3;
	private Team team4;
	private Team team5;
	private Team team6;
	private int seasonsPlayed;

	public static int[][] orderedStandings = new int[5][6];
	private static Game[] seasonGame = new Game[5];
	private static Game[] playoffGame = new Game[7];
	private static double[][] multiSeasonRecords = new double[7][6];
	public static double[][] multiOrderedStandings = new double[7][6];
	public static int[] teamOrder = new int [6];
	private static Team[] playoffTeams = new Team[4];
	private static Team[] finalsTeams = new Team[2];
	private static int[] playoffRecords = new int[4];
	private static int[] finalsRecords = new int[2];
	private static int[][] teamStats = new int[5][6];
	private static double[][] multiTeamStats = new double[5][6];
	public static double[][] multiOrderedTeamStats = new double[5][6];

	public SimulatedSeason( Team team1, Team team2, Team team3, Team team4, Team team5, Team team6, int seasonsPlayed ) {
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.team4 = team4;
		this.team5 = team5;
		this.team6 = team6;
		this.seasonsPlayed = seasonsPlayed;
	}
	
	public static void massSeasonSim( SimulatedSeason simSeason ) {
	//	Team[] seasonTeams = new Team[]{team1,team2,team3,team4,team5,team6};
		int[][] records = new int[5][6];

		
		// Simulates the games in multiple seasons
		for( int l = 0; l < seasonsPlayed; l++) {
			records = new int[5][6];								// Resets individual season records
			playoffRecords = new int[4];							// Resets individual season playoff records
			finalsRecords = new int[2];								// Resets individual season finals records
			
			for( int k = 0; k < 5; k++) {							// Cycles through Team 1
				for( int j = k + 1; j < 6; j++) {					// Cycles through Team 2
					for( int i = 0; i < 5; i++) {					// Cycles through the games between Team 1 and Team 2
						seasonGame[i] = new Game (seasonTeams[k], seasonTeams[j]);	// Sets up the games
						SimEngine.simulateGame(seasonGame[i]);		// Simulates the games
						statsUpdate( k, j, i, seasonGame[i].didTeam1Win(), seasonGame[i].isGameOT(), records);	// Updates the stats
					}
				}
			}
		
		
			for( int j = 0; j < 6; j++) {							// Cycles through the 6 teams
				for( int i = 0; i < 5; i++) {						// Cycles through the 5 record categories
					multiSeasonRecords[i][j]+= records[i][j];		// Copies over stats from a single-season variable to a multi-season variable
				}
			}
			
			int seasonWinner = 0;									// Resets season winner flag
			for( int i = 0; i < 6; i++) {							// Cycles through the 6 teams
				if ( records[4][i] > records[4][seasonWinner]) seasonWinner = i;	// Searches for the team with the most points, sets the flag
			}
			multiSeasonRecords[5][seasonWinner]++;					// Gives the highest point team a season win
			
			// Simulate Playoffs
						
			int[] playoffTeamIndex = new int[4];					// Resets playoff team array
			
			for( int j = 0; j < 4; j++) {							// Cycles through what will be the top 4 teams
				int pointsflag = 0;									// Resets season points flag
				for( int i = 0; i < 6; i++) {						// Cycles through all 6 teams
					if( records[4][i] > records[4][pointsflag]) pointsflag = i;	// Searches for the team with the most points, sets the flag
				}
				playoffTeams[j] = seasonTeams[pointsflag];			// Adds the highest remaining point team to the playoff array
				records[4][pointsflag] = 0;							// Sets that teams points to 0 for continuing the cycle
				playoffTeamIndex[j] = pointsflag;					// Sets which team is what seed in the playoff team array
			}
			

			for( int i = 0; i < 7; i++) {							// Cycles through a 7 game series
				playoffGame[i] = new Game ( playoffTeams[0], playoffTeams[3]); // Sets up the first matchup
				SimEngine.simulateGame(playoffGame[i]);				// Simulates the first matchup
				
				if( playoffGame[i].didTeam1Win()) {					// Checks to see    
					playoffRecords[0]++;							// which team
				}													// won and 
				else {												// increments the
					playoffRecords[3]++;							// playoff record
				}													// accordingly
			}
			
			if( playoffRecords[0] > playoffRecords[3]) {			// Checks to see
				finalsTeams[0] = playoffTeams[0];					// which team 
			}														// won the 
			else {													// most games
				finalsTeams[0] = playoffTeams[3];					// and sets up
				playoffTeamIndex[0] = playoffTeamIndex[3];			// the finals 
			}														// matchup
			
			for( int i = 0; i < 7; i++) {							// Cycles through a 7 game series
				playoffGame[i] = new Game ( playoffTeams[1], playoffTeams[2]); // Sets up the second matchup
				SimEngine.simulateGame(playoffGame[i]);				// Simulates the second matchup
				
				if( playoffGame[i].didTeam1Win()) {					// Checks to see
					playoffRecords[1]++;							// which team
				}													// won and
				else {												// increments the
					playoffRecords[2]++;							// playoff record
				}													// accordingly
			}
			
			if( playoffRecords[1] > playoffRecords[2]) {			// Checks to see
				finalsTeams[1] = playoffTeams[1];					// which team
			}														// won the											
			else {													// most games
				finalsTeams[1] = playoffTeams[2];					// and sets up
				playoffTeamIndex[1] = playoffTeamIndex[2];			// the finals
			}														// matchup
			
			for( int i = 0; i < 7; i++) {							// Cycles through a 7 game series
				playoffGame[i] = new Game ( finalsTeams[0], finalsTeams[1]);	// Sets up the finals matchup
				SimEngine.simulateGame(playoffGame[i]);				// Simulates the finals matchup
				
				if( playoffGame[i].didTeam1Win()) {					// Checks to see
					finalsRecords[0]++;								// which team
				}													// won and
				else {												// increments the
					finalsRecords[1]++;								// finals record
				}													// accordingly
			}
			
			if( finalsRecords[0] > finalsRecords[1]) {				// If Team 1 won the finals
				multiSeasonRecords[6][playoffTeamIndex[0]]++;		// Give Team 1 a playoff victory
			}
			else {
				multiSeasonRecords[6][playoffTeamIndex[1]]++;		// Or else give Team 2 a playoff victory
			}
		}
		
		// Get averages
		for( int j = 0; j < 6; j++) {								// Cycles through the 6 teams
			for( int i = 0; i < 5; i++) {							// Cycles through the 5 record categories
				multiSeasonRecords[i][j] = multiSeasonRecords[i][j] / seasonsPlayed;	// Averages the 5 records categories
			}
			multiTeamStats[0][j] = (double)teamStats[0][j] / (seasonsPlayed * 25);	// Averages Goals/Game
			multiTeamStats[1][j] = (double)teamStats[1][j] / (seasonsPlayed * 25);	// Averages Assists/Game
			multiTeamStats[2][j] = (double)teamStats[2][j] / (seasonsPlayed * 25);	// Averages Goals Against/Game
			multiTeamStats[3][j] = (double)teamStats[3][j] / (seasonsPlayed * 25);	// Averages Saves/Game
			multiTeamStats[4][j] = (double)teamStats[4][j] / (seasonsPlayed * 25);	// Averages Shots Against/Game
		}
		
		for( int j = 0; j < 6; j++) {								// Cycles through the 6 teams
			for( int i = 5; i < 7; i++) {							// Cycles through season and playoff championships
				multiSeasonRecords[i][j] = (multiSeasonRecords[i][j] * 100) / seasonsPlayed;	// Converts int to a % based on # seasons
			}
		}

		// Orders the rankings by most points to least points
		for( int j = 0; j < 6; j++) {								// Cycles through the 6 teams for ranking the teams in an array
			int pointsflag = 0;										// Resets the points flag to 0
			for( int i = 0; i < 6; i++) {							// Cycles through the 6 teams for finding highest point total
				if( multiSeasonRecords[4][i] > multiSeasonRecords[4][pointsflag]) pointsflag = i; // Searches for the team with the most points, sets the flag
			}
			for( int i = 0; i < 7; i++) {							// Cycles through the 7 record categories
				multiOrderedStandings[i][j] = multiSeasonRecords[i][pointsflag];	// Copies over stats from a multi-season variable to a ranked multi-season variable
				multiSeasonRecords[i][pointsflag] = 0;				// Sets that teams records to 0 for continuing the cycle 
			}
			multiOrderedTeamStats[0][j] = multiTeamStats[0][pointsflag];	// Ranks G/G
			multiOrderedTeamStats[1][j] = multiTeamStats[1][pointsflag];	// Ranks A/G		
			multiOrderedTeamStats[2][j] = multiTeamStats[2][pointsflag];	// Ranks GA/G
			multiOrderedTeamStats[3][j] = multiTeamStats[3][pointsflag];	// Ranks Sv/G
			multiOrderedTeamStats[4][j] = multiTeamStats[4][pointsflag];	// Ranks SA/G
			teamOrder[j] = pointsflag;								// Sets the team order to display team names with stats
		}
		
	}
	
	private static void statsUpdate( int team1, int team2, int game,  boolean team1Win, boolean OT, int[][] records ) {
		if (OT) {													// Checks if game went to OT
			if (team1Win) {											// Checks which team won
				records[1][team1]++;								// Increments
				records[4][team1]+= 2;								// the records
				records[2][team2]++;								// and each
				records[4][team2]+= 1;								// team points
				teamStats[0][team1] += seasonGame[game].winningTeamGoals();	// Stats for G/G
				teamStats[0][team2] += seasonGame[game].losingTeamGoals();	// Stats for G/G
				teamStats[1][team1] += seasonGame[game].winningTeamAssists();	// Stats for A/G
				teamStats[1][team2] += seasonGame[game].losingTeamAssists();	// Stats for A/G
				teamStats[2][team1] += seasonGame[game].losingTeamGoals();	// Stats for GA/G
				teamStats[2][team2] += seasonGame[game].winningTeamGoals();	// Stats for GA/G
				teamStats[3][team1] += seasonGame[game].winningTeamSaves();	// Stats for Sv/G
				teamStats[3][team2] += seasonGame[game].losingTeamSaves();	// Stats for Sv/G
				teamStats[4][team1] += seasonGame[game].losingTeamShots();	// Stats for SA/G
				teamStats[4][team2] += seasonGame[game].winningTeamShots();	// Stats for SA/G
			}
			else {
				records[2][team1]++;
				records[4][team1]+= 1;
				records[1][team2]++;
				records[4][team2]+= 2;
				teamStats[0][team1] += seasonGame[game].losingTeamGoals();
				teamStats[0][team2] += seasonGame[game].winningTeamGoals();
				teamStats[1][team1] += seasonGame[game].losingTeamAssists();
				teamStats[1][team2] += seasonGame[game].winningTeamAssists();
				teamStats[2][team1] += seasonGame[game].winningTeamGoals();
				teamStats[2][team2] += seasonGame[game].losingTeamGoals();
				teamStats[3][team1] += seasonGame[game].losingTeamSaves();
				teamStats[3][team2] += seasonGame[game].winningTeamSaves();
				teamStats[4][team1] += seasonGame[game].winningTeamShots();
				teamStats[4][team2] += seasonGame[game].losingTeamShots();
			}
		}
		else {
			if (team1Win) {
				records[0][team1]++;
				records[4][team1]+= 3;
				records[3][team2]++;
				teamStats[0][team1] += seasonGame[game].winningTeamGoals();
				teamStats[0][team2] += seasonGame[game].losingTeamGoals();
				teamStats[1][team1] += seasonGame[game].winningTeamAssists();
				teamStats[1][team2] += seasonGame[game].losingTeamAssists();
				teamStats[2][team1] += seasonGame[game].losingTeamGoals();
				teamStats[2][team2] += seasonGame[game].winningTeamGoals();
				teamStats[3][team1] += seasonGame[game].winningTeamSaves();
				teamStats[3][team2] += seasonGame[game].losingTeamSaves();
				teamStats[4][team1] += seasonGame[game].losingTeamShots();
				teamStats[4][team2] += seasonGame[game].winningTeamShots();
			}
			else {
				records[3][team1]++; 
				records[0][team2]++;
				records[4][team2]+= 3;
				teamStats[0][team1] += seasonGame[game].losingTeamGoals();
				teamStats[0][team2] += seasonGame[game].winningTeamGoals();
				teamStats[1][team1] += seasonGame[game].losingTeamAssists();
				teamStats[1][team2] += seasonGame[game].winningTeamAssists();
				teamStats[2][team1] += seasonGame[game].winningTeamGoals();
				teamStats[2][team2] += seasonGame[game].losingTeamGoals();
				teamStats[3][team1] += seasonGame[game].losingTeamSaves();
				teamStats[3][team2] += seasonGame[game].winningTeamSaves();
				teamStats[4][team1] += seasonGame[game].winningTeamShots();
				teamStats[4][team2] += seasonGame[game].losingTeamShots();
			}
	}
	}
	
	public int getSeasonsPlayed() {
		return seasonsPlayed;
	}
	
	public SimulatedSeason( int seasonsPlayed ) {
		this.seasonsPlayed = seasonsPlayed;
	}
}