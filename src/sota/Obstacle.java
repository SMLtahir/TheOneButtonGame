package sota;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Obstacle extends GameAgent{

	private static final long serialVersionUID = 1L;
	private int WIDTH;
	private int HEIGHT;
	
	public Obstacle(int x, int y, TYPE type, int width, int height) {

		super(x, y, type);
		this.WIDTH = width;
		this.HEIGHT = height;
	}

	@Override
	public void tick() {

		// Not needed for static obstacles
		// x += v_X;
		// y += v_Y;
		
		// TODO Make dynamic obstacles with non-zero velocities
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.RED);
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(new Color(120,0,0));
		g.drawRect(x, y, WIDTH, HEIGHT);	// Slight 3D effect
		
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
}
