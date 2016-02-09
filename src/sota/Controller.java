package sota;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	// LinkedList used for easy addition and removal of agents
	LinkedList<GameAgent> agents = new LinkedList<GameAgent>();

	private SOTA game;
	
	public Controller(SOTA g) {
		this.game = g;
	}
	
	public void tick() {
		for(int i=0; i< agents.size(); i++) {
			GameAgent a = agents.get(i);
			a.tick();
		}
	}
	
	public void render(Graphics g) {
		// Loop through all agents
		for(int i=0; i< agents.size(); i++) {
			GameAgent a = agents.get(i);
			a.render(g);
		}
	}
	
	public void addAgent(GameAgent agent) {
		this.agents.add(agent);
	}
	
	public void removeAgent(GameAgent agent) {
		this.agents.remove(agent);
	}
	
	public void removeAllObstacles() {
		
		// Remove all objects in the game
		this.agents.clear();
		
		// Add Floor, ceiling and player back
		this.addAgent(new Floor(0, SOTA.HEIGHT-100, TYPE.Floor, SOTA.WIDTH, 100));
		this.addAgent(new Ceiling(0, 62, TYPE.Ceiling, SOTA.WIDTH, 40));
		
		this.addAgent(new UserAgent(this, 0, SOTA.HEIGHT-132, TYPE.Player));
	}

	public void AddNewLevelObstacles(int level) {

		Levels l = new Levels(game, this, level);
		l.loadLevel(level);
		
	}
}
