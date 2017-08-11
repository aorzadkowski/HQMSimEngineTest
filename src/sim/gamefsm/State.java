package sim.gamefsm;

public abstract class State {
	public abstract State getNextState(GameState gs);
	
	// I had other methods in here, but they were moved. for now, this class will remain abstract.
	

}