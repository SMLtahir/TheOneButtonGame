package sota;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class UserAgent extends GameAgent{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	public static int velX = 1;
	
	public UserAgent(Controller cont, int x, int y, TYPE type) {

		super(x, y, type);
		this.controller = cont;
		v_Y = 1;
	}

	@Override
	public void tick() {

		x += velX;
		y += v_Y;
		
		// Clamp y
		y = SOTA.clampToMinMax(y, 100, SOTA.HEIGHT - 132);
		
		checkForCollision();
		checkForLevelUp();
	}

	private void checkForLevelUp() {

		if(x >= SOTA.WIDTH) {
			Scoreboard.LEVEL++;
			controller.removeAllObstacles();
			controller.AddNewLevelObstacles(Scoreboard.LEVEL);
			respawn();
		}
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.CYAN);
		g.fillRect(x, y, 32, 32);
		g.setColor(new Color(0,0,120));
		g.drawRect(x, y, 32, 32);
		// TODO Find a better way for below
		// To give the userAgent a better look (striped)
		/*for(int i=2;i<=32;i+=2) {
			g.drawRect(x, y, i, 32);
		}*/
	}
	
	private void checkForCollision() {

		for(int i=0; i< controller.agents.size(); i++) {
			GameAgent a = controller.agents.get(i);
			if(a.type == TYPE.Obstacle) {
				if(getBounds().intersects(a.getBounds())) {
					// The player has collided with an obstacle
					Scoreboard.LIFE -= 10;
					Scoreboard.SCORE -= 100;
					respawn();
				}
			}
		}
	}

	public void respawn() {
		// Respawn
		this.setX(0);
		this.setY(SOTA.HEIGHT-132);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
}
