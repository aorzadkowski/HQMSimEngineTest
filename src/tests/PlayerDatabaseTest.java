package tests;
import hqmdatabase.Player;
import hqmdatabase.PlayerDatabase;
import hqmdatabase.Role;

public class PlayerDatabaseTest {
	public static void databaseTester() {
		PlayerDatabase db = new PlayerDatabase();
		
		db.addPlayer(new Player("Lucic", "LHL S13", Role.SNIPER, 15, 16, 19, 19, 19, 19, 19, 15, 20));
		System.out.println("Added Lucic LHL S13. Size: " + db.getSize());
		db.addPlayer(new Player("Tony Flow", "LHL S13", Role.PLAYMAKER, 13, 15, 14, 15, 17, 15, 16, 16, 20));
		System.out.println("Added Tony Flow LHL S13. Size: " + db.getSize());
		
		System.out.println("Current players in db: ");
		for (String str : db.getNames()) {
			System.out.println("\t" + str);
		}
		
		System.out.println("Replacing Tony Flow's stats with all 20's.");
		db.replacePlayer(new Player("Tony Flow", "LHL S13", Role.PLAYMAKER, 20, 20, 20, 20, 20, 20, 20, 20, 20));
		
		System.out.println("Here's the new Tony Flow: " + db.retrievePlayer("Tony Flow", "LHL S13"));
	}
}
