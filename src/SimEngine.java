
public class SimEngine {
	public static double maxOffense = 59.2;
	public static double maxDefense = 16.8;
	public static double maxBoth = 4.0;
	
	public static void getBoxScore(Team team1, Team team2) {
		/*
		System.out.print("Box scores:\t\t\tP1\tP2\tP3\tF\nTeam1: \t\t\t");
		*/
		int[] period1 = getNetScoreInPeriod(team1, team2);
		int[] period2 = getNetScoreInPeriod(team1, team2);
		int[] period3 = getNetScoreInPeriod(team1, team2);
		/*
		System.out.println(period1[0] + "\t" + period2[0] + "\t" + period3[0] + "\t" + (period1[0] + period2[0] + period3[0]));
		System.out.println("Team2: \t\t\t" + period1[1] + "\t" + period2[1] + "\t" + period3[1]+ "\t" + (period1[1] + period2[1] + period3[1]));
		*/
		
		int team1Score = (period1[0]+period2[0]+period3[0]);
		int team2Score = (period1[1]+period2[1]+period3[1]);
		System.out.println(team1Score + "\t" + team2Score + "\t" + (team1Score > team2Score ? "1" : "0"));
	}
	
	private static int[] getNetScoreInPeriod(Team team1, Team team2) {
		int team1Goals = getScoreInPeriod(team1);
		int team1Blocks = getBlocksInPeriod(team1);
		
		int team2Goals = getScoreInPeriod(team2);
		int team2Blocks = getBlocksInPeriod(team2);
		
		int team1Points = team1Goals + team2Blocks;
		if (team1Points < 0) team1Points = 0;
		
		int team2Points = team2Goals + team1Blocks;
		if (team2Points < 0) team2Points = 0;
		
		return new int[] {team1Points, team2Points};
	}
	
	private static int getScoreInPeriod(Team team) {
		double randomNumb = Math.random();
		int currentScore = 0;
		if (randomNumb <= team.oRatio) {
			currentScore += 1;
			currentScore += getScoreInPeriod(team, 1);
		}
		return currentScore;
	}
	
	private static int getScoreInPeriod(Team team, int score) {
		double probability = team.oRatio - ((0.01 + team.inverseBRatio) * team.oRatio * (score * score));
		if (Math.random() <= probability) {
			return 1 + getScoreInPeriod(team, score + 1);
		}
		return 0;
	}
	
	private static int getBlocksInPeriod(Team team) {
		double randomNumb = Math.random();
		int currentDefCont = 0;
		if (randomNumb <= team.dRatio) {
			currentDefCont -= 1;
			currentDefCont += getBlocksInPeriod(team, 1);
		}
		return currentDefCont;
	}
	
	private static int getBlocksInPeriod(Team team, int score) {
		double probability = team.dRatio - ((0.01 + team.inverseBRatio) * team.dRatio * (score * score));
		if (Math.random() <= probability) {
			return (-1) - getBlocksInPeriod(team, score + 1);
		}
		return 0;
	}
	
}
