
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
		
		int gameCount = 1000;
		
		//System.out.println("Team1\tTeam2");
		for (int i = 0; i < gameCount; i++) {
			//System.out.println("Game " + (i+1));
			SimEngine.getBoxScore(nsh, bos);
		}
	}

}
