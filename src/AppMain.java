
public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Testing random player generation:");
		PlayerFactory fact = new PlayerFactory();
		for (int i = 0; i < 100; i++) {
			System.out.println(fact.generateRandomPlayer());
		}
		System.out.println("Looked good.\n" );
		
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
		
		System.out.println("Teams are:");
		System.out.println("\t" + team1 + "\n\t" + team2);
		System.out.println("Team1 Final Score: \tTeam2 Final Score:");
		for (int i = 0; i < 100; i++) {
			System.out.print("Game " + (i+1)+ "\t");
			SimEngine.getBoxScore(team1, team2);
		}
	}

}
