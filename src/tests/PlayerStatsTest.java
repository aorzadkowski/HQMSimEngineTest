package tests;

import java.util.ArrayList;

import hqmdatabase.Player;
import hqmdatabase.PlayerDatabase;
import hqmdatabase.Season;
import main.AppMain;
import sim.Stats;

public class PlayerStatsTest {

	public static void testPlayerStats() {
		ArrayList<Player> playerList = new ArrayList<>();
		
		PlayerDatabase testingDB = AppMain.mainDB;

		System.out.println("Players in testing are: ");
		//Get 10 random players.
		for (int i = 0; i < 10; i++) {
			Player randomPlayer = getRandomPlayer(testingDB);
			
			playerList.add(randomPlayer);
			testingDB.removePlayer(randomPlayer);
			
			System.out.println("\t" + randomPlayer.getName());
		}
		
		ArrayList<Stats> playerStats = new ArrayList<>();
		
		for (int i = 0; i < playerList.size(); i++) {
			playerStats.add(new Stats(playerList.get(i)));
		}
		
		System.out.println("\nNow going to add a random number of shots to each player.");
				
		for (int i = 0; i < playerList.size(); i++) {
			playerStats.get(i).addGamesPlayed();
			for (int j = 0; j < (int) (Math.random() * 10) + 5; j++) {
				playerStats.get(i).addShot();
			}
		}
		
		printStatsOfPlayers(playerStats);
		
	}
	
	private static void printStatsOfPlayers(ArrayList<Stats> playerStats) {
		System.out.println("Here are the stats for each player: \n\tName\t\t\tSeason\tPos\tPts\tG\tA\tP/G\t+/-\tShots\tSvs\tSv%\tSv/G\tGA\tGAA\tGP\tGP@G");
		
		for (int i = 0; i < playerStats.size(); i++) {
			
			String[] stats = playerStats.get(i).toCSV().split(",");
			
			for (int j = 0; j < stats.length; j++) {
				System.out.print("\t" + stats[j]);
			}
			System.out.println();
		}
	}
	
	private static Player getRandomPlayer(PlayerDatabase playerDB) {
		String[] playerNames = playerDB.getNames().toArray(new String[0]);
		
		String player = playerNames[(int) (Math.random() * playerNames.length)];
		
		String[] playerNameSplit = player.split(",");
		
		return playerDB.retrievePlayer(playerNameSplit[0], Season.parseSeason(playerNameSplit[1]));
	}
}
