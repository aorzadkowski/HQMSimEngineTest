
public class AppMain {

	public static void main(String[] args) {
		System.out.println("Let's test some real teams.");
		
		Team team1 = new Team("Team one", 
					new Player("Lucic", Role.SNIPER, 15, 16, 19, 19, 19, 19, 19, 15, 20),
					new Player("Tony Flow", Role.PLAYMAKER, 13, 15, 14, 15, 17, 15, 16, 16, 20),
					new Player("Mat", Role.OFFENSIVE_DMAN, 16, 17, 16, 16, 15, 15, 15, 15, 12),
					new Player("FatSquirrel", Role.OFFENSIVE_DMAN, 13, 13, 11, 13, 14, 14, 12, 14, 16) );
		Team team2 = new Team("Team two", 
					new Player("tb", Role.SNIPER, 16, 15, 14, 15, 15, 16, 17, 14, 16),
					new Player("Kill Pessel", Role.DANGLER, 12, 12, 13, 15, 14, 14, 14, 10, 17),
					new Player("KS Otto", Role.OFFENSIVE_DMAN, 16, 19, 15, 18, 19, 19, 16, 19, 20),
					new Player("Dman Jerry", Role.DEFENSIVE_DMAN, 15, 15, 14, 15, 12, 15, 17, 17, 18));
		
		Team bos = new Team("Boston Bruins",
				new Player("Lucic", Role.SNIPER, 15, 16, 19, 19, 19, 19, 19, 19, 20),
				new Player("Teemu Salami", Role.DANGLER, 12, 13, 15, 17, 16, 17, 17, 17, 18),
				new Player("KS Otto", Role.OFFENSIVE_DMAN, 16, 19, 15, 18, 19, 19, 16, 16, 20),
				new Player("Tidge", Role.DEFENSIVE_DMAN, 14, 15, 15, 15, 12, 15, 17, 17, 16)
				);
		
		Team nyr = new Team("New York Rangers",
				new Player("Dick Mcbutts", Role.SNIPER, 13, 15, 18, 18, 18, 18, 19, 19, 17),
				new Player("tb", Role.PLAYMAKER, 16, 15, 14, 15, 15, 16, 17, 17, 16),
				new Player("TaZeR", Role.DEFENSIVE_DMAN, 14, 16, 15, 15, 14, 16, 18, 18, 15),
				new Player("Kapanen", Role.OFFENSIVE_DMAN, 14, 14, 11, 14, 13, 15, 13, 13, 15)
				);
		
		Team chi = new Team("Chicago Blackhawks",
				new Player("Dyaloreax", Role.SNIPER, 10, 15, 20, 20, 18, 17, 19, 19, 20),
				new Player("Tallmidget", Role.TWO_WAY_FORWARD, 17, 15, 13, 14, 16, 13, 16, 16, 16),
				new Player("BeeGeePi", Role.OFFENSIVE_DMAN, 17, 17, 15, 16, 16, 17, 18, 18, 20),
				new Player("FatSquirrel", Role.OFFENSIVE_DMAN, 13, 13, 11, 13, 14, 14, 12, 12, 16)
				);
		
		Team tor = new Team("Toronto Maple Leafs",
				new Player("NHLKilla", Role.PLAYMAKER, 12, 13, 13, 14, 14, 14, 15, 15, 15),
				new Player("Gabe", Role.DANGLER, 16, 15, 15, 16, 19, 18, 18, 18, 18),
				new Player("xParabolax", Role.OFFENSIVE_DMAN, 14, 15, 12, 16, 16, 15, 14, 14, 17),
				new Player("Jabba", Role.OFFENSIVE_DMAN,13, 14, 12, 15, 16, 14, 13, 13, 14)
				);

		Team to2 = new Team("Toronto Maple Leafs 2",
				new Player("NHLKilla", Role.PLAYMAKER, 12, 13, 13, 14, 14, 14, 15, 15, 15),
				new Player("Gabe", Role.PLAYMAKER, 16, 15, 15, 16, 19, 18, 18, 18, 18),
				new Player("xParabolax", Role.OFFENSIVE_DMAN, 14, 15, 12, 16, 16, 15, 14, 14, 17),
				new Player("Jabba", Role.OFFENSIVE_DMAN,13, 14, 12, 15, 16, 14, 13, 13, 14)
				);
		
		Team nsh = new Team("Nashville Predators",
				new Player("CrabInATree", Role.PLAYMAKER, 19, 19, 17, 16, 18, 16, 19, 19, 20),
				new Player("Tony Flow", Role.TWO_WAY_FORWARD, 13, 15, 14, 15, 17, 15, 16, 16, 20),
				new Player("Zam", Role.OFFENSIVE_DMAN, 17, 18, 17, 16, 17, 17, 17, 17, 19),
				new Player("Dman Jerry", Role.DEFENSIVE_DMAN, 15, 15, 14, 15, 12, 15, 17, 17, 18)
				);
		
		Team win = new Team("Winnipeg Jets",
				new Player("guy la floor", Role.TWO_WAY_FORWARD, 14, 15, 15, 17, 17, 17, 16, 16, 20),
				new Player("Drag", Role.DANGLER, 13, 14, 14, 16, 16, 16, 16, 16, 17),
				new Player("Dalfan", Role.OFFENSIVE_DMAN, 16, 18, 16, 17, 17, 16, 18, 18, 16),
				new Player("Icey", Role.OFFENSIVE_DMAN, 15, 16, 14, 15, 14, 16, 16, 16, 17)
				);
		
		Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};
		
		int gameCount = 1000000;
		System.out.println("Gonna test " + gameCount + " games.");
		
		Team testingTeam1 = tor;
		Team testingTeam2 = to2;
		
		Game testGame = new Game (testingTeam1, testingTeam2);
		
		int team1Wins = 0;
		int team2Wins = 0;
		
		int totalGoals = 0;
		int winningTeamGoals = 0;
		int losingTeamGoals = 0;
		
		int marginOfVictoryGoals = 0;
		
		int totalOTGames = 0;
		int totalSOGames = 0;
		int maxGoalsInGame = -1;
		int mostOT = -1;
		int mercyGames = 0;
		
		for (int i = 0; i < gameCount; i++) {
			testGame = new Game(testingTeam1, testingTeam2);  // Is this line redundant with line 68?
			SimEngine.simulateGame(testGame);
			
			if (testGame.didTeam1Win()) team1Wins++; else team2Wins++;
			
			winningTeamGoals += testGame.winningTeamGoals();
			losingTeamGoals += testGame.losingTeamGoals();
			totalGoals += testGame.totalGoals();
			
			marginOfVictoryGoals += testGame.marginOfVictory();
			
			if (testGame.isGameOT()) totalOTGames++;
			if (testGame.losingTeamGoals() == 0) totalSOGames++;
			if (testGame.winningTeamGoals() > maxGoalsInGame) maxGoalsInGame = testGame.winningTeamGoals();
			if (testGame.getOTCount() > mostOT) mostOT = testGame.getOTCount();
			if (testGame.winningTeamGoals() - testGame.losingTeamGoals() > 5) mercyGames++;
		}
		Team team1Out = testGame.getTeam1();
		Team team2Out = testGame.getTeam2();
		
		System.out.println("Tonight's teams were " + team1Out.teamName + " and " + team2Out.teamName);
		System.out.println("\n" + team1Out.teamName + " stats: \n\tOff. Ratio:\t" + team1Out.oRatio 
														+ "\n\tDef. Ratio:\t" + team1Out.dRatio 
														+ "\n\tInvB. Ratio:\t" + team1Out.inverseBRatio
														+ "\n\tWin %:\t" + (((double)team1Wins) * 100 / gameCount));
		System.out.println("\n" + team2Out.teamName + " stats: \n\tOff. Ratio:\t" + team2Out.oRatio 
														+ "\n\tDef. Ratio:\t" + team2Out.dRatio 
														+ "\n\tInvB. Ratio:\t" + team2Out.inverseBRatio
														+ "\n\tWin %:\t" + (((double)team2Wins) * 100 / gameCount) + "\n");
		System.out.println("Average Total Goals: \t" + (((double) totalGoals) / gameCount));
		System.out.println("Average Winning Team's Goals:\t" +(((double) winningTeamGoals) / gameCount));
		System.out.println("Average Margin of Victory:\t" + (((double) marginOfVictoryGoals) / gameCount));
		System.out.println("Average Losing Team's Goals:\t" + (((double) losingTeamGoals) / gameCount));
		System.out.println("% of SO Games:\t" + (((double) totalSOGames) * 100 / gameCount));
		System.out.println("% of Games with a mercy score:\t" + (((double) mercyGames) * 100 / gameCount));
		System.out.println("% of OT Games:\t" + (((double) totalOTGames) * 100 / gameCount));
		System.out.println("Most OT periods:\t" + mostOT);
		System.out.println("Most Goals Scored in a Game: \t" + maxGoalsInGame);
	}

}
