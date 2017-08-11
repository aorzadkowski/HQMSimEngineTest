package sim.gamefsm;

import java.util.ArrayList;

import hqmdatabase.Player;
import sim.Team;

public class GameState {
	Team team1;
	Team team2;
	
	Player playerInPossession;
	boolean team1InPossession;
	
	int team1Score;
	int team2Score;
	
	int period;
	int timeLeft; //in seconds.
	
	ArrayList<EventHandler> recentEvents;
	
	State[] possibleStates = { new Faceoff(), new Possession(), new FreePuck() };
	
	State currentState;
	
	public GameState(Team team1, Team team2) {
		this.team1 = applyConsistencyForTeam(team1);
		this.team2 = applyConsistencyForTeam(team2);
		
		playerInPossession = null;
		team1InPossession = true;
		
		team1Score = 0;
		team2Score = 0;
		
		period = 1;
		timeLeft = 300;
		
		recentEvents = new ArrayList<>();
		
		currentState = possibleStates[0];
		
		currentState.getNextState(this).getNextState(this);
	}
	
	private String getNormalizedTime(int seconds) {
		int minute = seconds / 60;
		int second = seconds % 60;
		
		return minute + ":" + (second < 10 ? "0" + second : second); 
	}
	
	Team applyConsistencyForTeam(Team team) {
		Team newTeam = new Team(team);
		
		for (int i = 0; i < newTeam.teamPlayers.length; i++) {
			newTeam.teamPlayers[i] = applyConsistencyForAllStats(newTeam.teamPlayers[i]);
		}
		
		return newTeam;
	}
	
	Player applyConsistencyForAllStats(Player player) {
		Player newPlayer = new Player(player);
		
		for (int i = 0; i < newPlayer.stats.length - 1; i++) {
			newPlayer.stats[i] = applyConsistency(newPlayer, i);
		}
		
		return newPlayer;
	}
	
	int applyConsistency (Player player, int skill) {
		if (skill < 0 || skill > 18) {
			System.err.println("Please enter a valid skill value: " + skill);
			return -1;
		}
		
		boolean decrease = false;
		
		decrease = Math.random() > ((double) player.getStat(19)) / 20; 
		
		if (decrease) {
			double random = Math.random();
			
			if (random > 0.6666) return player.getStat(skill) - 1;
			else if (random > 0.2222) return player.getStat(skill) - 2;
			else return player.getStat(skill) - 3;
		} else 
			return player.getStat(skill);
	}
}
