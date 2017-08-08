package sim;

public class SimEngine {
	
	private static int[] team1Goals = new int[4];
	private static int[] team2Goals = new int[4];
	private static int[] team1Blocks = new int[4];
	private static int[] team2Blocks = new int[4];
	private static int[] team1Points = new int[4];
	private static int[] team2Points = new int[4];
	public static int[] team1Stats = new int[4];
	public static int[] team2Stats = new int[4];
	
	public static void simulateGame(Game game) {
		
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
		
		game.setScore(boxScore);
	}
	
	private static int[] getNetScoreInPeriod(Team team1, Team team2) {
		int team1Total = 0;
		int team2Total = 0;
		
		for ( int i = 0; i < 4; i++ ) {
			team1Goals[i] = getScoreInPeriod(team1, i);
			team1Blocks[i] = getBlocksInPeriod(team1, i);
		
			team2Goals[i] = getScoreInPeriod(team2, i);
			team2Blocks[i] = getBlocksInPeriod(team2, i);
		}
		
		for ( int i = 0; i < 4; i++ ) {
			team1Points[i] = team1Goals[i] + team2Blocks[3-i];
			if (team1Points[i] < 0) team1Points[i] = 0;
			team1Stats[i] = team1Points[i];
			team1Total += team1Points[i];
		
			team2Points[i] = team2Goals[i] + team1Blocks[3-i];
			if (team2Points[i] < 0) team2Points[i] = 0;
			team2Stats[i] = team2Points[i];
			team2Total += team2Points[i];
		}
		return new int[] {team1Total, team2Total};
	}
	
	private static int getScoreInPeriod(Team team, int player) {
		double randomNumb = Math.random();
		int currentScore = 0;
			if (randomNumb <= team.oRatio[player]) {
				currentScore += 1;
		//		currentScore += getScoreInPeriod(team, 1, player);
			}
		return currentScore;
	}
		
	private static int getScoreInPeriod(Team team, int score, int player) {
		//double probability = team.oRatio - ((0.01 + team.inverseBRatio) * team.oRatio * (score * score));
		double probability = team.oRatio[player] - ((0.01 + team.inverseBRatio[player]) * team.oRatio[player] * (score));
		if (Math.random() <= probability) {
			return 1 + getScoreInPeriod(team, score + 1, player);
		}
		return 0;
	}
	
	private static int getBlocksInPeriod(Team team, int player) {
		double randomNumb = Math.random();
		int currentDefCont = 0;
		if (randomNumb <= team.dRatio[player]) {
			currentDefCont -= 1;
		//	currentDefCont += getBlocksInPeriod(team, 1, player);
		}
		return currentDefCont;
	}
	
	private static int getBlocksInPeriod(Team team, int score, int player) {
		//double probability = team.dRatio - ((0.01 + team.inverseBRatio) * team.dRatio * (score * score));
		double probability = team.dRatio[player] - ((0.01 + team.inverseBRatio[player]) * team.dRatio[player] * (score));
		if (Math.random() <= probability) {
			return (-1) - getBlocksInPeriod(team, score + 1);
		}
		return 0;
	}
}
