package sota;

public class Levels {

	private SOTA game;
	private Controller controller;
	private int level;
	
	public Levels(SOTA g, Controller c, int level) {
		this.game = g;
		this.controller = c;
		this.level = level;
	}
	
	public void loadLevel(int level) {
		// Clear all obstacles from previous level
		for(int i=0;i< controller.agents.size();) {
			GameAgent a = controller.agents.get(i);
			if(a.type == TYPE.Obstacle) {
				controller.removeAgent(a);
				continue;
			}
			i++;
		}
		
		// TODO Make a level generator so that these levels aren't hard-coded like below
		if(level == 1) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
		} else if(level == 2) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(SOTA.WIDTH/4, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(0, 150, TYPE.Obstacle, SOTA.WIDTH, 32));
		} else if(level == 3) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT/2-138, TYPE.Obstacle, 32, 120));
		} else if(level == 4) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(120, SOTA.HEIGHT-170, TYPE.Obstacle, 140, 32));
		} else if(level == 5) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT/2, TYPE.Obstacle, 32, 120));
			controller.addAgent(new Obstacle(3*SOTA.WIDTH/4, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(0, 100, TYPE.Obstacle, SOTA.WIDTH, 32));
		} else if(level == 6) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT/2-138, TYPE.Obstacle, 32, 120));
			controller.addAgent(new Obstacle(2*SOTA.WIDTH/3-50, SOTA.HEIGHT-170, TYPE.Obstacle, 250, 32));
		} else if(level == 7) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT/2-138, TYPE.Obstacle, 32, 120));
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT/2, TYPE.Obstacle, 32, 140));
		} else if(level == 8) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT-132, TYPE.Obstacle, 50, 32));
			controller.addAgent(new Obstacle(2*SOTA.WIDTH/3, SOTA.HEIGHT-132, TYPE.Obstacle, 50, 32));
			controller.addAgent(new Obstacle(SOTA.WIDTH-40, SOTA.HEIGHT-132, TYPE.Obstacle, 50, 32));
			controller.addAgent(new Obstacle(0, 150, TYPE.Obstacle, SOTA.WIDTH, 32));
		} else if(level == 9) {
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(SOTA.WIDTH/3, SOTA.HEIGHT/2-138, TYPE.Obstacle, 32, 120));
			controller.addAgent(new Obstacle(2*SOTA.WIDTH/3, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
			controller.addAgent(new Obstacle(2*SOTA.WIDTH/3, SOTA.HEIGHT/2-138, TYPE.Obstacle, 32, 120));
			controller.addAgent(new Obstacle(SOTA.WIDTH - 50, SOTA.HEIGHT-132, TYPE.Obstacle, 60, 32));
		} else {
			// Win on reaching level 10
			game.state = ScreenState.Win;
		}
		
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
