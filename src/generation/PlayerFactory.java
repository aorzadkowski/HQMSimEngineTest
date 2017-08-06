package generation;
import hqmdatabase.Player;
import hqmdatabase.Role;

public class PlayerFactory {
	public PlayerFactory() {
		//System.out.println("Let's go");
	}
	
	public Player generateRandomPlayer() {
		return generatePlayer(NameGenerator.getName(), "RND", pickRoleEqualChance(), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20));
	}
	
	public Player generatePlayer(String name, String season, Role role, int faceoffs_longShots, int passing, 
								int receiving, int shooting_wristShots, int stickhandling, 
								int handEye_shotBlocking, int offAwareness, int defAwareness,
								int reliability) {
		
		return new Player(name, season, role, faceoffs_longShots, passing, receiving, shooting_wristShots, stickhandling,
				handEye_shotBlocking, offAwareness, defAwareness, reliability);
	}
	
	public Player generateRandomDMan() {
		Player newPlayer = generateRandomPlayer();
		newPlayer.setRole(pickDMan());
		return newPlayer;
	}
	
	public Player generateRandomOMan() {
		Player newPlayer = generateRandomPlayer();
		newPlayer.setRole(pickOMan());
		return newPlayer;
	}
	
	private Role pickDMan() {
		return Math.random() > 0.5 ? Role.OFFENSIVE_DMAN : Role.DEFENSIVE_DMAN;
	}
	
	private Role pickOMan() {
		double random = Math.random();
		
		if (random <= 0.25) return Role.SNIPER;
		else if (random > 0.25 && random <= 0.5) return Role.PLAYMAKER;
		else if (random > 0.5 && random <= 0.75) return Role.DANGLER;
		else return Role.TWO_WAY_FORWARD;
	}
	
	private Role pickRoleEqualChance() {
		Role[] roles = {pickOMan(), pickOMan(), pickOMan(), pickOMan(), pickDMan(), pickDMan(), pickDMan(), pickDMan()};
		
		return roles[(int) (Math.random() * roles.length)];
	}
	
	private int randomNumberBetweenRange(int low, int high) {
		return (int) (Math.random() * (high - low + 1) + low);
	}
}
