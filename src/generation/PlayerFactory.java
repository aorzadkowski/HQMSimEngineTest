package generation;
import hqmdatabase.Player;
import hqmdatabase.Position;
import hqmdatabase.Role;
import hqmdatabase.Season;

public class PlayerFactory {
	public PlayerFactory() {
		//System.out.println("Let's go");
	}
	
	public Player generateRandomPlayer() {
		return generatePlayer(NameGenerator.getName(), Season.NO_SEASON, Position.C, pickRoleEqualChance(), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20), randomNumberBetweenRange(12, 20)
				, randomNumberBetweenRange(12, 20));
	}
	
	public Player generatePlayer(String name, Season season, Position position, Role role, int faceoffs, int longshots, int pointshots, int wristshots, int boardpassing, 
			int openicepassing, int breakoutpassing, int boardreceiving, int openicereceiving, int handeye, int stickhandling, int dangling,
			int possession, int shotblocking, int hitting, int positioning, int pokecheck, int offAwareness, int defAwareness, int consistency) {
		
		return new Player(name, season, position, role, faceoffs, longshots, pointshots, wristshots, boardpassing, 
				openicepassing, breakoutpassing, boardreceiving, openicereceiving, handeye, stickhandling, dangling,
				possession, shotblocking, hitting, positioning, pokecheck, offAwareness, defAwareness, consistency);
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
