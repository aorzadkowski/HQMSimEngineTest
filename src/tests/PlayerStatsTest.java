package tests;

import hqmdatabase.Player;
import hqmdatabase.Season;
import main.AppMain;
import sim.Stats;

public class PlayerStatsTest {

	public static void testPlayerStats() {
		Player P1 = AppMain.mainDB.retrievePlayer("Dick Mcbutts", Season.LHLS13);
		Stats stat = new Stats(P1);
		System.out.println("Player Name: " + stat.getPlayerName());
		System.out.println("Player Shots: " + stat.getValue("Shots"));
		System.out.println("Player Goals: " + stat.getValue("Goals"));
		System.out.println("Player Assists: " + stat.getValue("Assists"));
		
		stat.addGoal();
		System.out.println("Player Name: " + stat.getPlayerName());
		System.out.println("Player Shots: " + stat.getValue("Shots"));
		System.out.println("Player Goals: " + stat.getValue("Goals"));
		System.out.println("Player Assists: " + stat.getValue("Assists"));
		
		stat.addShot();
		System.out.println("Player Name: " + stat.getPlayerName());
		System.out.println("Player Shots: " + stat.getValue("Shots"));
		System.out.println("Player Goals: " + stat.getValue("Goals"));
		System.out.println("Player Assists: " + stat.getValue("Assists"));
		
		stat.addAssist();
		System.out.println("Player Name: " + stat.getPlayerName());
		System.out.println("Player Shots: " + stat.getValue("Shots"));
		System.out.println("Player Goals: " + stat.getValue("Goals"));
		System.out.println("Player Assists: " + stat.getValue("Assists"));
		
		stat.addShotOnNet();
		System.out.println("Player Name: " + stat.getPlayerName());
		System.out.println("Player Shots: " + stat.getValue("Shots"));
		System.out.println("Player Goals: " + stat.getValue("Goals"));
		System.out.println("Player Assists: " + stat.getValue("Assists"));
	}
}
