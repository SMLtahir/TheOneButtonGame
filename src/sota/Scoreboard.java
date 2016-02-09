package sota;

import java.awt.Color;
import java.awt.Graphics;

public class Scoreboard {

	public static int LIFE = 100;
	private int LIFE_FULL_COLOR = 255;
	private int LIFE_ZERO_COLOR = 0;
	public static int LEVEL = 1;
	public static int SCORE = 0;

	public void tick() {
		LEVEL = SOTA.clampToMinMax(LEVEL, 0, 10);
		LIFE = SOTA.clampToMinMax(LIFE, 0, 100);
		SCORE = SOTA.clampToMinMax(SCORE, 0, Integer.MAX_VALUE);
		LIFE_FULL_COLOR = LIFE*2;
		LIFE_ZERO_COLOR = 255 - LIFE*2;
		LIFE_FULL_COLOR = SOTA.clampToMinMax(LIFE_FULL_COLOR, 0, 255);
		LIFE_ZERO_COLOR = SOTA.clampToMinMax(LIFE_ZERO_COLOR, 0, 255);
		SCORE++;	// Keep score continuously ticking with time
	}

	public void render(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(20, 20, 200, 32);
		g.setColor(new Color(LIFE_ZERO_COLOR, LIFE_FULL_COLOR, 0));
		g.fillRect(20, 20, LIFE*2, 32);
		g.setColor(Color.WHITE);
		// Divisions for the LIFE bar
		g.drawRect(20, 20, 20, 32);
		g.drawRect(40, 20, 20, 32);
		g.drawRect(60, 20, 20, 32);
		g.drawRect(80, 20, 20, 32);
		g.drawRect(100, 20, 20, 32);
		g.drawRect(120, 20, 20, 32);
		g.drawRect(140, 20, 20, 32);
		g.drawRect(160, 20, 20, 32);
		g.drawRect(180, 20, 20, 32);
		g.drawRect(200, 20, 20, 32);
		
		g.drawString("Level: "+ LEVEL, SOTA.WIDTH/2, 40);
		g.drawString("Score: "+ SCORE, SOTA.WIDTH/2+ 100, 40);
	}
}
