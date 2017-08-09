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

public class AppMain {
	
	public static PlayerDatabase mainDB = new PlayerDatabase();

	public static void main(String[] args) {
		OptionsIO.readFile();
		
		ArgumentLineHandler.processArguments(args);
		
		// System.out.println(mainDB.getSize());
		
		/*
		Team team1 = new Team("Team one", 
					new Player("Lucic", Season.LHLS13, Position.C, Role.SNIPER, 15, 16, 19, 19, 19, 19, 19, 15, 20),
					new Player("Tony Flow", Season.LHLS13, Position.LW, Role.PLAYMAKER, 13, 15, 14, 15, 17, 15, 16, 16, 20),
					new Player("Mat", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 16, 17, 16, 16, 15, 15, 15, 15, 12),
					new Player("FatSquirrel", Season.LHLS13, Position.RD, Role.OFFENSIVE_DMAN, 13, 13, 11, 13, 14, 14, 12, 14, 16) );
		Team team2 = new Team("Team two", 
					new Player("tb", Season.LHLS13, Position.C, Role.SNIPER, 16, 15, 14, 15, 15, 16, 17, 14, 16),
					new Player("Kill Pessel", Season.LHLS13, Position.LW, Role.DANGLER, 12, 12, 13, 15, 14, 14, 14, 10, 17),
					new Player("KS Otto", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 16, 19, 15, 18, 19, 19, 16, 19, 20),
					new Player("Dman Jerry", Season.LHLS13, Position.RD, Role.DEFENSIVE_DMAN, 15, 15, 14, 15, 12, 15, 17, 17, 18));
		*/
		
		// \t added for season sim looks, can and probably should be removed (same for win) :v
		Team bos = new Team("Boston Bruins \t",
				new Player("Lucic", Season.LHLS13, Position.C, Role.SNIPER, 13, 14, 15, 18, 13, 15, 13, 17, 18, 17, 18, 18, 18, 11, 12, 13, 13, 18, 11, 16),
				new Player("Teemu", Season.LHLS13, Position.LW, Role.DANGLER, 12, 12, 12, 17, 11, 11, 8, 13, 15, 16, 17, 17, 14, 5, 6, 4, 10, 18, 6, 16),
				new Player("KS Otto", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 12, 10, 12, 18, 13, 15, 14, 18, 18, 19, 20, 20, 19, 19, 10, 14, 17, 19, 15, 19),
				new Player("Tidge", Season.LHLS13, Position.RD, Role.DEFENSIVE_DMAN, 5, 14, 15, 12, 12, 13, 14, 12, 10, 12, 10, 7, 12, 15, 15, 17, 15, 13, 15, 13),
				new Player("SelfPlug", Season.LHLS13, Position.G, Role.GOALIE, 16, 12, 13, 14, 7, 18, 13)
				);
		
		/*
		Team nyr = new Team("New York Rangers",
				new Player("Dick Mcbutts", Season.LHLS13, Position.C, Role.SNIPER, 13, 15, 18, 18, 18, 18, 19, 19, 17),
				new Player("tb", Season.LHLS13, Position.LW, Role.PLAYMAKER, 16, 15, 14, 15, 15, 16, 17, 17, 16),
				new Player("TaZeR", Season.LHLS13, Position.LD, Role.DEFENSIVE_DMAN, 14, 16, 15, 15, 14, 16, 18, 18, 15),
				new Player("Kapanen", Season.LHLS13, Position.RD, Role.OFFENSIVE_DMAN, 14, 14, 11, 14, 13, 15, 13, 13, 15)
				);
		
		Team chi = new Team("Chicago Blackhawks",
				new Player("Dyaloreax", Season.LHLS13, Position.C, Role.SNIPER, 10, 15, 20, 20, 18, 17, 19, 19, 20),
				new Player("Tallmidget", Season.LHLS13, Position.LW, Role.TWO_WAY_FORWARD, 17, 15, 13, 14, 16, 13, 16, 16, 16),
				new Player("BeeGeePi", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 17, 17, 15, 16, 16, 17, 18, 18, 20),
				new Player("FatSquirrel", Season.LHLS13, Position.RD, Role.OFFENSIVE_DMAN, 13, 13, 11, 13, 14, 14, 12, 12, 16)
				);
		
		Team tor = new Team("Toronto Maple Leafs",
				new Player("NHLKilla", Season.LHLS13, Position.C, Role.PLAYMAKER, 12, 13, 13, 14, 14, 14, 15, 15, 15),
				new Player("Gabe", Season.LHLS13, Position.LW, Role.DANGLER, 16, 15, 15, 16, 19, 18, 18, 18, 18),
				new Player("xParabolax", Season.LHLS13, Position.LD, Role.DEFENSIVE_DMAN, 14, 15, 12, 16, 16, 15, 14, 14, 17),
				new Player("Jabba", Season.LHLS13, Position.RD, Role.OFFENSIVE_DMAN,13, 14, 12, 15, 16, 14, 13, 13, 14)
				);

		Team to2 = new Team("Toronto Maple Leafs 2",
				new Player("NHLKilla", Season.LHLS13, Position.C, Role.PLAYMAKER, 12, 13, 13, 14, 14, 14, 15, 15, 15),
				new Player("Gabe", Season.LHLS13, Position.LW, Role.DANGLER, 16, 15, 15, 16, 19, 18, 18, 18, 18),
				new Player("xParabolax", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 14, 15, 12, 16, 16, 15, 14, 14, 17),
				new Player("Jabba", Season.LHLS13, Position.RD, Role.OFFENSIVE_DMAN,13, 14, 12, 15, 16, 14, 13, 13, 14)
				);
		*/
		Team nsh = new Team("Nashville Predators",
				new Player("CrabInATree", Season.LHLS13, Position.C, Role.PLAYMAKER, 19, 13, 14, 17, 18, 19, 18, 17, 17, 17, 16, 16, 17, 14, 13, 16, 16, 18, 17, 18),
				new Player("Tony Flow", Season.LHLS13, Position.LW, Role.TWO_WAY_FORWARD, 12, 12, 12, 15, 14, 15, 14, 14, 14, 17, 13, 12, 14, 15, 12, 15, 13, 16, 16, 15),
				new Player("Zam", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 7, 14, 15, 15, 15, 14, 16, 16, 14, 16, 17, 15, 15, 13, 9, 15, 14, 17, 14, 13),
				new Player("Dman Jerry", Season.LHLS13, Position.RD, Role.DEFENSIVE_DMAN, 5, 14, 15, 12, 14, 14, 15, 14, 10, 12, 10, 7, 12, 16, 16, 17, 15, 13, 16, 12),
				new Player("Kiwi", Season.LHLS13, Position.G, Role.GOALIE, 18, 17, 16, 15, 17, 13, 16)
				);
		
		/*
		Team win = new Team("Winnipeg Jets \t",
				new Player("guy la floor", Season.LHLS13, Position.C, Role.TWO_WAY_FORWARD, 14, 15, 15, 17, 17, 17, 16, 16, 20),
				new Player("Drag", Season.LHLS13, Position.LW, Role.DANGLER, 13, 14, 14, 16, 16, 16, 16, 16, 17),
				new Player("Dalfan", Season.LHLS13, Position.LD, Role.OFFENSIVE_DMAN, 16, 18, 16, 17, 17, 16, 18, 18, 16),
				new Player("Icey", Season.LHLS13, Position.RD, Role.OFFENSIVE_DMAN, 15, 16, 14, 15, 14, 16, 16, 16, 17)
				);
		*/
		
		// Team[] lhlTeams = {bos, nyr, chi, tor, nsh, win};

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
		
		/*
		SimulatedSeason.massSeasonSim( lhlTeams[0], lhlTeams[1], lhlTeams[2], lhlTeams[3], lhlTeams[4], lhlTeams[5], gameCount );
		
		System.out.println("Here are the averaged results from simulating S13 LHL " + gameCount + " times! \n");
		
		for( int i = 0; i < 6; i++) {
			System.out.println( lhlTeams[SimulatedSeason.teamOrder[i]].teamName + "\t "
					
					 + "(" + Season.multOrderedStandings[0][i] + "-"
								+ Season.multOrderedStandings[1][i] + "-" + Season.multOrderedStandings[2][i]+ "-"
								+ Season.multOrderedStandings[3][i] + ") \t" 
								
					 + SimulatedSeason.multOrderedStandings[4][i] + "\t\t% Season Championships: " + SimulatedSeason.multOrderedStandings[5][i]
					 + "\t\t% Playoff Championships: " + SimulatedSeason.multOrderedStandings[6][i]);
		}
		*/
	}
}