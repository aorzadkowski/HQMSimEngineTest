package tests;
import hqmdatabase.Player;
import hqmdatabase.PlayerDatabase;
import hqmdatabase.Season;
import hqmdatabase.Position;
import hqmdatabase.Role;

public class PlayerDatabaseTest {
	public static void databaseTester() {
		PlayerDatabase db = new PlayerDatabase();
/*		
		db.addPlayer(new Player("Lucic", Season.LHLS13, Position.C, Role.SNIPER, 15, 16, 19, 19, 19, 19, 19, 15, 20));
		System.out.println("Added Lucic LHL S13. Size: " + db.getSize());
		db.addPlayer(new Player("Tony Flow", Season.LHLS13, Position.LW, Role.PLAYMAKER, 13, 15, 14, 15, 17, 15, 16, 16, 20));
		System.out.println("Added Tony Flow LHL S13. Size: " + db.getSize());
		
		System.out.println("Current players in db: ");
		for (String str : db.getNames()) {
			System.out.println("\t" + str);
		}
		
		System.out.println("Here's the old Tony Flow: " + db.retrievePlayer("Tony Flow", Season.LHLS13));
		
		System.out.println("Replacing Tony Flow's stats with all 20's.");
		db.replacePlayer(new Player("Tony Flow", Season.LHLS13, Position.LW, Role.PLAYMAKER, 20, 20, 20, 20, 20, 20, 20, 20, 20));
		
		System.out.println("Here's the new Tony Flow: " + db.retrievePlayer("Tony Flow", Season.LHLS13));
		
		System.out.println("Let's get rid of Lucic.");
		db.removePlayer("Lucic", Season.LHLS13);
		
		System.out.println("Current players in db: ");
		for (String str : db.getNames()) {
			System.out.println("\t" + str);
		}
*/
	}
}