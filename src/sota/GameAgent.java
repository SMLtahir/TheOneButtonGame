package sota;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Every object in the game - players, enemies, obstacles, environment objects, etc will extend from this class
 * @author Tahir
 *
 */
public abstract class GameAgent extends Canvas {
	protected int x, y;		//x, y coordinates
	protected int v_X, v_Y;		//x, y velocities
	protected TYPE type;
	
	public GameAgent(int x, int y, TYPE type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	// Getter and setter methods for all fields
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVx(int v_X) {
		this.v_X = v_X;
	}
	
	public void setVy(int v_Y) {
		this.v_Y = v_Y;
	}
	
	public void setType(TYPE type) {
		this.type = type;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getVx() {
		return this.v_X;
	}
	
	public int getVy() {
		return this.v_Y;
	}
	
	public TYPE getType() {
		return this.type;
	}
}