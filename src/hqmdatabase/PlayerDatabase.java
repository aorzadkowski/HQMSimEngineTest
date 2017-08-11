package hqmdatabase;
import java.util.ArrayList;

public class PlayerDatabase {
	private ArrayList<Player> data;
	
	private int size;
	
	public PlayerDatabase() {
		data = new ArrayList<>();
		size = 0;
	}
	
	public boolean addPlayer(Player newPlayer) {
		for (Player player : data) {
			if (newPlayer.getName().equals(player.getName()) && newPlayer.getSeason().equals(player.getSeason())) return false;
		}
		
		data.add(newPlayer);
		
		size++;
		
		return true;
	}
	
	public boolean contains(String name, Season season) {
		for (Player player : data) {
			if (name.equals(player.getName()) && season == player.getSeason()) return true;
		}
		
		return false;
	}
	
	public boolean contains(Player player) {
		return data.contains(player);
	}
	
	public int getSize() {
		return size;
	}
	
	public Player retrievePlayer(String name, Season season) {
		for (Player player : data) {
			if (name.equals(player.getName()) && season == player.getSeason()) return player;
		}
		
		return new Player("INVALID PLAYER: " + name, season, Position.NO_POS, Role.NO_ROLE, new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
	}
	
	public Player replacePlayer(Player newPlayer) {
		Player oldPlayer = new Player(retrievePlayer(newPlayer.getName(), newPlayer.getSeason()));
		
		if (!removePlayer(oldPlayer)) System.out.println("Player could not be found!");
		
		addPlayer(newPlayer);
		
		return oldPlayer;
	}
	
	public boolean removePlayer (Player player) {
		return removePlayer(player.getName(), player.getSeason());
	}
	
	public boolean removePlayer (String name, Season season) {
		for (Player player : data) {
			if (name.equals(player.getName()) && season == player.getSeason()) 
				return data.remove(player);
		}
		
		size--;
		
		return false;
	}
	
	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<>();
		
		for (Player player : data) {
			names.add(player.getName() + " " + player.getSeason());
		}
		
		return names;
	}
}