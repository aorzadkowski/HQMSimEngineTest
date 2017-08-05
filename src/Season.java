
public class Season {
//Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};
	
	public static int[][] records = new int[5][6];
	public static int[][] orderedStandings = new int[6][6];
	public static Game[] seasonGame = new Game[5];
	
	// Bos v NRY
	
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
				orderedStandings[i+1][j] = records[i][pointsflag];
				records[i][pointsflag] = 0;
			}
			orderedStandings[0][j] = pointsflag;
		}
	}
}
