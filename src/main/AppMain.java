package main;
import hqmdatabase.Player;
import hqmdatabase.PlayerDatabase;
import hqmdatabase.Position;
import hqmdatabase.Role;
import hqmdatabase.Season;
import sim.Game;
import sim.SimEngine;
import sim.SimulatedSeason;
import sim.Team;
import sim.gamefsm.GameState;

public class AppMain {
	
	public static PlayerDatabase mainDB = new PlayerDatabase();

	public static void main(String[] args) {
		OptionsIO.readFile();
		
		ArgumentLineHandler.processArguments(args);
		
		//System.out.println(mainDB.getSize());
		
		Team bos = new Team("Boston Bruins",
                mainDB.retrievePlayer("Lucic",Season.LHLS13),
                mainDB.retrievePlayer("Teemu Salami",Season.LHLS13),
                mainDB.retrievePlayer("KS Otto",Season.LHLS13),
                mainDB.retrievePlayer("Tidge",Season.LHLS13),
                mainDB.retrievePlayer("SelfPlug",Season.LHLS13)
                );
		
		Team nyr = new Team("New York Rangers",
				mainDB.retrievePlayer("Dick Mcbutts", Season.LHLS13),
				mainDB.retrievePlayer("tb", Season.LHLS13),
				mainDB.retrievePlayer("TaZeR", Season.LHLS13),
				mainDB.retrievePlayer("Kapanen", Season.LHLS13),
				mainDB.retrievePlayer("meat",Season.LHLS13)
				);
		
		Team chi = new Team("Chicago Blackhawks",
				mainDB.retrievePlayer("Dyaloreax", Season.LHLS13),
				mainDB.retrievePlayer("Tallmidget", Season.LHLS13),
				mainDB.retrievePlayer("BeeGeePi", Season.LHLS13),
				mainDB.retrievePlayer("FatSquirrel", Season.LHLS13),
				mainDB.retrievePlayer("Narguila",Season.LHLS13)
				);
		
		Team tor = new Team("Toronto Maple Leafs",
				mainDB.retrievePlayer("NHLKilla", Season.LHLS13),
				mainDB.retrievePlayer("Gabe", Season.LHLS13),
				mainDB.retrievePlayer("xParabolax", Season.LHLS13),
				mainDB.retrievePlayer("Jabba", Season.LHLS13),
				mainDB.retrievePlayer("kBomb",Season.LHLS13)
				);

		Team nsh = new Team("Nashville Predators",
				mainDB.retrievePlayer("CrabInATree", Season.LHLS13),
				mainDB.retrievePlayer("Tony Flow", Season.LHLS13),
				mainDB.retrievePlayer("Zam", Season.LHLS13),
				mainDB.retrievePlayer("Dman Jerry", Season.LHLS13),
				mainDB.retrievePlayer("Kiwi", Season.LHLS13)
				);
		
		Team win = new Team("Winnipeg Jets ",
				mainDB.retrievePlayer("guy la floor", Season.LHLS13),
				mainDB.retrievePlayer("Drag", Season.LHLS13),
				mainDB.retrievePlayer("Dalfan", Season.LHLS13),
				mainDB.retrievePlayer("Icey", Season.LHLS13),
				mainDB.retrievePlayer("Ace",Season.LHLS13)
				);
		
		Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};
		// GameState gs = new GameState(bos, bos);

		int gameCount = Options.numberOfGames;
		
		
		System.out.println("Gonna test " + gameCount + " games.");
		
		Team testingTeam1 = bos;
		Team testingTeam2 = nsh;
		
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
		
/*
		
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
		System.out.println("\n" + team1Out.teamName + " stats: \n\tWin %:\t" + (((double)team1Wins) * 100 / gameCount));
		System.out.println("\n" + team2Out.teamName + " stats: \n\tWin %:\t" + (((double)team2Wins) * 100 / gameCount) + "\n");
		System.out.println("Average Total Goals: \t" + (((double) totalGoals) / gameCount));
		System.out.println("Average Winning Team's Goals:\t" +(((double) winningTeamGoals) / gameCount));
		System.out.println("Average Margin of Victory:\t" + (((double) marginOfVictoryGoals) / gameCount));
		System.out.println("Average Losing Team's Goals:\t" + (((double) losingTeamGoals) / gameCount));
		System.out.println("% of SO Games:\t" + (((double) totalSOGames) * 100 / gameCount));
		System.out.println("% of Games with a mercy score:\t" + (((double) mercyGames) * 100 / gameCount));
		System.out.println("% of OT Games:\t" + (((double) totalOTGames) * 100 / gameCount));
		System.out.println("Most OT periods:\t" + mostOT);
		System.out.println("Most Goals Scored in a Game: \t" + maxGoalsInGame + "\n");

		for ( int i = 0; i < 4; i++ ) {
			System.out.println(team1Out.teamPlayers[i].getName() + "\t\t\t" + team2Out.teamPlayers[i].getName());
			System.out.println("Goals:   " + (SimEngine.team1MultiStats[0][i] / gameCount ) + "\t\tGoals:   " + (SimEngine.team2MultiStats[0][i] / gameCount ));
			System.out.println("Assists: " + (SimEngine.team1MultiStats[1][i] / gameCount ) + "\t\tAssists: " + (SimEngine.team2MultiStats[1][i] / gameCount ) + "\n");
			}
			System.out.println(team1Out.teamPlayers[4].getName() + "\t\t" + team2Out.teamPlayers[4].getName());
			System.out.println("Saves:    " + (SimEngine.team1MultiStats[0][4] / gameCount ) + "\t\tSaves:    " + (SimEngine.team2MultiStats[0][4] / gameCount ));
			System.out.println("Attempts: " + (SimEngine.team1MultiStats[1][4] / gameCount ) + "\t\tAttempts: " + (SimEngine.team2MultiStats[1][4] / gameCount ));
			System.out.println("Assists:  " + (SimEngine.goalieAssists[0] / gameCount ) + "\t\tAssists:  " + (SimEngine.goalieAssists[1] / gameCount ));
	*/	
		
		SimulatedSeason.massSeasonSim( lhlTeams[0], lhlTeams[1], lhlTeams[2], lhlTeams[3], lhlTeams[4], lhlTeams[5], gameCount );
		
		System.out.println("Here are the averaged results from simulating S13 LHL " + gameCount + " times! \n");
		
		for( int i = 0; i < 6; i++) {
			System.out.println( lhlTeams[SimulatedSeason.teamOrder[i]].teamName + "\t "
					+ "(" + SimulatedSeason.multOrderedStandings[0][i] + "-" + SimulatedSeason.multOrderedStandings[1][i]
					+ "-" + SimulatedSeason.multOrderedStandings[2][i]+ "-"	+ SimulatedSeason.multOrderedStandings[3][i] + ") \t" 
					+ SimulatedSeason.multOrderedStandings[4][i] + "\t\t% Season Championships: " + SimulatedSeason.multOrderedStandings[5][i]
					+ "\t\t% Playoff Championships: " + SimulatedSeason.multOrderedStandings[6][i]);
		}
	}
}
